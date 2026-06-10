# Discord 課題提出・採点 Bot (雛形)

このリポジトリは、Discord 上で提出スレッドを作り、ファイル添付で即時採点する最小実装の雛形です。

注意: 提出コードを直接実行します。現時点ではサンドボックスが無いため、本番利用は危険です。Docker 等で隔離することを推奨します。

セットアップ

```bash
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
cp .env.example .env
# 編集して DISCORD_TOKEN と GUILD_ID を設定
python bot.py
```

使い方

- 初期状態で全員が見える最初の課題チャネル（java00）でユーザーが `/create-thread` を実行して、自分専用の提出スレッドを作成します
- 作成したスレッド内に `ch0.java`, `ch1.java` のようなファイル名で提出すると、即時採点されます- 1 つの章に複数ファイルがある場合は ZIP 提出にも対応可能です。章内の全課題ファイルを含む ZIP を提出すると、全て正解で次のチャネルが開放されます。- 正解した場合は、次の課題チャネル（java01, java02, ...）への閲覧権限が自動で付与されます

課題の運用フロー

1. `challenges.yaml` で `ch0`, `ch1`, ... のキーを定義し、各課題の入出力例を設定
2. `next_channel_id` で次の課題チャネルのIDを指定
3. ユーザーが正解すると、自動的に次のチャネルが見えるようになる
4. ユーザーはその次のチャネルで再び `/create-thread` して、次の課題に進む

サンプル設定

```yaml
ch0:
  title: "課題0"
  description: "..."
  next_channel_id: 1508620452325490749  # java01のID
  sample_input: "3 5\n"
  sample_output: "8\n"
```

次のステップ

- Docker でのサンドボックス化
- ファイル種別の増やし込み（C, Java など）
- データベースによる提出履歴管理

Docker サンドボックス

1. Docker が動作するマシンで次を実行してイメージをビルドします:

```bash
docker build -t submission-runner:latest docker
```

2. ボットで Docker 実行を有効にするには、`.env` に `USE_DOCKER=true` を追加します。

3. 提出ファイルは `sandbox/run_submission.sh` 経由で隔離実行され、以下の制限が入ります:
- ネットワーク無効
- メモリ 128MB
- CPU 0.5 コア
- 実行タイムアウト 5 秒

注意: これは簡易的な隔離です。より安全な本番環境ではさらに厳格な policy と監査が必要です。

Java課題の設定

- Java提出は `.java` ファイル（例: `Main.java`）を提出する想定です。公開クラス名とファイル名が一致している必要があります。
- Java 用イメージをビルドするには次を実行します:

```bash
docker build -t submission-runner-java:latest docker/java
```

- Java 提出は `sandbox/run_submission_java.sh` を経由してコンパイル・実行されます。`Main.java` のようにクラス名とファイル名が一致していることを確認してください。

注意: 現バージョンでは、Java 実行のために `USE_DOCKER=true` と Java イメージのビルドが必要です。
