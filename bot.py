import os
import yaml
import tempfile
import shutil
import zipfile
import datetime
from dotenv import load_dotenv
import discord
from discord.ext import commands
from grader import grade_file, find_java_file

# (user_id, chapter_id) -> {'failures': int, 'until': datetime | None}
_cooldowns: dict = {}

load_dotenv()
TOKEN = os.getenv('DISCORD_TOKEN')
GUILD_ID = int(os.getenv('GUILD_ID', '0'))
SUBMIT_PARENT_CHANNEL_ID = int(os.getenv('SUBMIT_PARENT_CHANNEL_ID', '0'))
ADMIN_ROLE_NAME = os.getenv('ADMIN_ROLE_NAME', 'Admin')

intents = discord.Intents.default()
intents.guilds = True

bot = commands.Bot(command_prefix='!', intents=intents)

CHAPTERS = {}
BASE_PATH = os.path.join(os.path.dirname(__file__), 'submissions')
_synced = False


@bot.event
async def on_ready():
    global CHALLENGES, CHAPTERS, _synced
    with open(os.path.join(os.path.dirname(__file__), 'challenges.yaml'), 'r', encoding='utf-8') as f:
        raw = yaml.safe_load(f) or {}
    CHAPTERS = raw.get('chapters', {})
    print(f'Logged in as {bot.user} (id: {bot.user.id})')
    print(f'Configured GUILD_ID={GUILD_ID}')
    
    # Only sync once
    if not _synced:
        guild = bot.get_guild(GUILD_ID) if GUILD_ID != 0 else None
        print(f'Bot guild lookup returned: {guild}')
        
        # Debug: show registered commands
        all_commands = list(bot.tree.walk_commands())
        print(f'📦 Registered commands in bot.tree: {len(all_commands)}')
        for cmd in all_commands:
            print(f'  - {cmd.name}')
        
        try:
            if guild is not None:
                print(f'⚠️ Syncing globally instead of guild (to fix command registration)')
                synced = await bot.tree.sync()
                print(f'✅ Commands synced globally. Total: {len(synced)} command(s)')
                for cmd in synced:
                    print(f'  Synced: {cmd.name}')
            else:
                print(f'⚠️ GUILD_ID={GUILD_ID} not found. Syncing globally instead.')
                synced = await bot.tree.sync()
                print(f'✅ Commands synced globally. Total: {len(synced)} command(s)')
                for cmd in synced:
                    print(f'  Synced: {cmd.name}')
            _synced = True
        except Exception as e:
            print(f'❌ Sync failed: {type(e).__name__}: {e}')
            import traceback
            traceback.print_exc()
    else:
        print('(Already synced, skipping sync)')


@bot.tree.command(name='create-thread', description='Create your private submission thread in this channel')
async def create_thread(interaction: discord.Interaction):
    await interaction.response.defer(ephemeral=True)
    channel = interaction.channel
    member = interaction.user
    if channel is None:
        await interaction.followup.send('このコマンドはサーバー内のチャンネルで実行してください。', ephemeral=True)
        return

    thread_name = f'{member.name}-submit'
    try:
        if hasattr(channel, 'create_thread'):
            new_thread = await channel.create_thread(
                name=thread_name,
                type=discord.ChannelType.private_thread,
                auto_archive_duration=1440,
                invitable=False,
                reason='Create submission thread'
            )
            # Add admin role members so admins can see the private thread
            admin_role = discord.utils.get(interaction.guild.roles, name=ADMIN_ROLE_NAME)
            if admin_role and admin_role.members:
                await new_thread.add_users(admin_role.members)

            java00_meta = CHAPTERS.get('java00', {})
            java00_channel = None
            if java00_meta.get('channel_id'):
                java00_channel = interaction.guild.get_channel(java00_meta['channel_id'])
            if java00_channel is None and java00_meta.get('channel_name'):
                java00_channel = discord.utils.get(interaction.guild.channels, name=java00_meta['channel_name'])

            if java00_channel:
                await grant_channel_access(member, java00_channel)
                java00_ref = f'\n最初の課題は {java00_channel.mention} を確認してください。'
            else:
                java00_ref = ''

            await new_thread.send(
                f'{member.mention} 提出用スレッドを作成しました。ここに `/grademe` コマンドで ZIP ファイルを提出してください。{java00_ref}'
            )
            await interaction.followup.send(f'{member.mention} 提出用スレッドを作成しました: {new_thread.mention}', ephemeral=True)
        else:
            await interaction.followup.send('現在のチャンネルではスレッドを作成できません。', ephemeral=True)
    except Exception as e:
        await interaction.followup.send(f'スレッド作成に失敗しました: {e}', ephemeral=True)



def _check_cooldown(user_id: int, chapter_id: str):
    """クールダウン中なら残り秒数を返す。問題なければ None。"""
    entry = _cooldowns.get((user_id, chapter_id))
    if entry and entry['until'] and datetime.datetime.now() < entry['until']:
        remaining = (entry['until'] - datetime.datetime.now()).seconds + 1
        return remaining
    return None


def _record_failure(user_id: int, chapter_id: str):
    key = (user_id, chapter_id)
    entry = _cooldowns.setdefault(key, {'failures': 0, 'until': None})
    entry['failures'] += 1
    minutes = 1 if entry['failures'] == 1 else 5
    entry['until'] = datetime.datetime.now() + datetime.timedelta(minutes=minutes)
    return minutes


async def grant_channel_access(member: discord.Member, channel: discord.abc.GuildChannel):
    """指定チャンネルへの閲覧権限をメンバーに付与する。"""
    resolved = channel.guild.get_member(member.id) or member
    await channel.set_permissions(resolved, view_channel=True, send_messages=True, read_message_history=True)


async def grant_next_channel_access(member: discord.Member, guild: discord.Guild, meta: dict):
    next_channel = None
    next_channel_id = meta.get('next_channel_id')
    next_channel_name = meta.get('next_channel_name')
    if next_channel_id:
        next_channel = guild.get_channel(next_channel_id)
    if next_channel is None and next_channel_name:
        next_channel = discord.utils.get(guild.channels, name=next_channel_name)
    if next_channel is None:
        return None
    await grant_channel_access(member, next_channel)
    return next_channel


ZIP_SIZE_LIMIT = 512 * 1024  # 512 KB


@bot.tree.command(name='grademe', description='ZIP ファイルを提出して採点します')
async def grademe(interaction: discord.Interaction, zip_file: discord.Attachment):
    if not zip_file.filename.lower().endswith('.zip'):
        await interaction.response.send_message('ZIP ファイルを添付してください。', ephemeral=True)
        return

    if zip_file.size > ZIP_SIZE_LIMIT:
        await interaction.response.send_message(
            f'ファイルサイズが大きすぎます（上限 {ZIP_SIZE_LIMIT // 1024} KB）。',
            ephemeral=True
        )
        return

    chapter_id = os.path.splitext(zip_file.filename)[0].lower()
    if chapter_id not in CHAPTERS:
        await interaction.response.send_message(
            f'`{zip_file.filename}` に対応する課題が見つかりません。`Java00.zip` のようにファイル名をつけてください。',
            ephemeral=True
        )
        return

    remaining = _check_cooldown(interaction.user.id, chapter_id)
    if remaining is not None:
        minutes, seconds = divmod(remaining, 60)
        await interaction.response.send_message(
            f'再提出は {minutes}分{seconds}秒後からです。',
            ephemeral=True
        )
        return

    await interaction.response.defer()
    chapter_meta = CHAPTERS[chapter_id]
    required_keys = chapter_meta.get('required_keys', [])
    required_count = chapter_meta.get('required_count', len(required_keys))

    temp_dir = tempfile.mkdtemp(prefix='submission_')
    try:
        zip_path = os.path.join(temp_dir, zip_file.filename)
        await zip_file.save(zip_path)

        extract_dir = os.path.join(temp_dir, 'extracted')
        os.makedirs(extract_dir)
        with zipfile.ZipFile(zip_path, 'r') as archive:
            names = archive.namelist()
            bad = [n for n in names if not n.endswith('/') and not n.lower().endswith('.java')]
            if bad:
                await interaction.followup.send(
                    f'`.java` 以外のファイルが含まれています: {", ".join(bad[:5])}'
                )
                return
            archive.extractall(extract_dir)

        G = '\x1b[1;32m'  # bold green
        R = '\x1b[31m'  # red
        X = '\x1b[0m'   # reset

        result_lines = []
        footer_lines = []
        passed_keys = set()

        for key in required_keys:
            java_path = find_java_file(extract_dir, f'{key}.java')
            if java_path is None:
                result_lines.append(f'{R}{key}.java: ファイルが見つかりません{X}')
                continue
            ok, detail = grade_file(java_path, '', '')
            if ok:
                result_lines.append(f'{key}.java: {G}OK{X}')
                passed_keys.add(key)
            elif detail.startswith('__COMPILE__\n'):
                result_lines.append(f'{key}.java: {R}コンパイルエラー{X}')
            elif detail == '__DOCKER_DOWN__':
                result_lines.append(f'{key}.java: {R}採点サーバー停止中{X}')
            elif detail == 'Timeout':
                result_lines.append(f'{key}.java: {R}タイムアウト{X}')
            else:
                indented = '\n'.join(f'  {l}' for l in detail.splitlines())
                result_lines.append(f'{key}.java: {R}KO{X}\n{indented}')

        any_failed = len(passed_keys) < required_count
        if any_failed:
            wait = _record_failure(interaction.user.id, chapter_id)
            footer_lines.append(f'（不合格のため {wait} 分間再提出できません）')

        # ansi ブロックでチャンク分割（上限 1900 文字 + ブロック記法の余白）
        LIMIT = 1900
        chunks = []
        buf, buf_len = [], 0
        for line in result_lines:
            line_len = len(line) + 1
            if buf and buf_len + line_len > LIMIT:
                chunks.append('```ansi\n' + '\n'.join(buf) + '\n```')
                buf, buf_len = [], 0
            buf.append(line)
            buf_len += line_len
        if buf:
            block = '```ansi\n' + '\n'.join(buf) + '\n```'
            if footer_lines:
                block += '\n' + '\n'.join(footer_lines)
            chunks.append(block)
        elif footer_lines:
            chunks.append('\n'.join(footer_lines))

        for chunk in chunks:
            await interaction.followup.send(chunk)

        if len(passed_keys) >= required_count:
            next_channel = await grant_next_channel_access(interaction.user, interaction.guild, chapter_meta)
            total = len(required_keys)
            label = f'{len(passed_keys)}/{total} 問正解'
            if next_channel:
                await interaction.followup.send(
                    f'{chapter_id} {label}！{next_channel.mention} を閲覧できるようになりました。'
                )
            else:
                await interaction.followup.send(
                    f'{chapter_id} {label}！ただし、次の課題チャンネルの設定が見つかりませんでした。管理者にお問い合わせください。'
                )
    except Exception as e:
        try:
            await interaction.followup.send('採点中に予期しないエラーが発生しました。管理者にお問い合わせください。')
        except Exception:
            pass
        raise
    finally:
        shutil.rmtree(temp_dir, ignore_errors=True)


if __name__ == '__main__':
    bot.run(TOKEN)
