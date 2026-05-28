import subprocess
import os
import sys
import shutil
import tempfile

TESTERS_DIR = os.path.join(os.path.dirname(__file__), 'testers')


def run_python_file(path, input_data, timeout=5):
    cmd = [sys.executable, path]
    try:
        p = subprocess.run(cmd, input=input_data, text=True, capture_output=True, timeout=timeout)
        return p.returncode, p.stdout, p.stderr
    except subprocess.TimeoutExpired:
        return -1, "", "TIMEOUT"


def run_in_docker(path, input_data):
    runner = os.path.join(os.path.dirname(__file__), 'sandbox', 'run_submission.sh')
    if not os.path.exists(runner):
        return -1, '', 'Docker runner not found'
    if not shutil.which('docker'):
        return -1, '', 'docker not installed or not in PATH'
    cmd = [runner, path]
    try:
        p = subprocess.run(cmd, input=input_data, text=True, capture_output=True, timeout=15)
        return p.returncode, p.stdout, p.stderr
    except subprocess.TimeoutExpired:
        return -1, '', 'TIMEOUT'


def run_in_docker_java(path, input_data):
    runner = os.path.join(os.path.dirname(__file__), 'sandbox', 'run_submission_java.sh')
    if not os.path.exists(runner):
        return -1, '', 'Docker Java runner not found'
    if not shutil.which('docker'):
        return -1, '', 'docker not installed or not in PATH'
    cmd = [runner, path]
    try:
        p = subprocess.run(cmd, input=input_data, text=True, capture_output=True, timeout=20)
        return p.returncode, p.stdout, p.stderr
    except subprocess.TimeoutExpired:
        return -1, '', 'TIMEOUT'


def run_tester_java(submission_dir):
    runner = os.path.join(os.path.dirname(__file__), 'sandbox', 'run_tester_java.sh')
    if not os.path.exists(runner):
        return -1, '', 'Tester runner not found'
    if not shutil.which('docker'):
        return -1, '', 'docker not installed or not in PATH'
    cmd = [runner, submission_dir]
    try:
        p = subprocess.run(cmd, text=True, capture_output=True, timeout=30)
        return p.returncode, p.stdout, p.stderr
    except subprocess.TimeoutExpired:
        return -1, '', 'TIMEOUT'


def find_java_file(root_dir, filename):
    """ZIP展開ディレクトリを再帰探索してファイルパスを返す。同名ファイルが複数ある場合は辞書順で最初のものを返す。"""
    for dirpath, dirnames, filenames in os.walk(root_dir):
        dirnames.sort()  # ex00 → ex01 の順で辞書順探索
        if filename in filenames:
            return os.path.join(dirpath, filename)
    return None


def get_gradeable_files(root_dir):
    """root_dir を再帰探索し、テスターが存在する .java ファイルを (クラス名, パス) のリストで返す。
    同名クラスが複数ある場合は辞書順で最初のディレクトリのものだけを返す。"""
    seen = set()
    result = []
    for dirpath, dirnames, filenames in os.walk(root_dir):
        dirnames.sort()
        for fname in sorted(filenames):
            if not fname.endswith('.java'):
                continue
            key = os.path.splitext(fname)[0]
            if key in seen:
                continue
            if not os.path.isdir(os.path.join(TESTERS_DIR, key)):
                continue
            seen.add(key)
            result.append((key, os.path.join(dirpath, fname)))
    return result


def grade_java_with_tester(student_path):
    """対応する testers/{ClassName}/ が存在すれば Tester.java で採点し (ok, detail) を返す。存在しなければ None を返す。"""
    class_name = os.path.splitext(os.path.basename(student_path))[0]
    tester_dir = os.path.join(TESTERS_DIR, class_name)
    if not os.path.isdir(tester_dir):
        return None

    temp_dir = tempfile.mkdtemp(prefix='tester_')
    try:
        # 提出ファイルと同ディレクトリにある .java を全てコピー（複数ファイル課題対応）
        student_dir = os.path.dirname(student_path)
        for fname in os.listdir(student_dir):
            if fname.endswith('.java'):
                shutil.copy2(os.path.join(student_dir, fname), temp_dir)

        # テスターディレクトリのファイルをコピー（Tester.java + 提供クラス）
        for fname in os.listdir(tester_dir):
            shutil.copy2(os.path.join(tester_dir, fname), os.path.join(temp_dir, fname))

        code, out, err = run_tester_java(temp_dir)

        if code == 2:
            return False, '__COMPILE__\n' + (out + err).strip()
        if code == -1 and 'TIMEOUT' in err:
            return False, 'Timeout'
        if code == 0:
            return True, out.strip() if out.strip() else 'OK'
        detail = (out + err).strip()
        return False, detail if detail else 'KO'
    finally:
        shutil.rmtree(temp_dir, ignore_errors=True)


def grade_file(path, sample_input, sample_output):
    use_docker = os.getenv('USE_DOCKER', '0') in ('1', 'true', 'yes')
    ext = os.path.splitext(path)[1].lower()
    if ext == '.java':
        if not use_docker:
            return False, 'Java grading requires USE_DOCKER=true'
        # テスターがあれば優先使用
        result = grade_java_with_tester(path)
        if result is not None:
            return result
        # フォールバック: stdin/stdout 方式
        code, out, err = run_in_docker_java(path, sample_input)
    else:
        if use_docker:
            code, out, err = run_in_docker(path, sample_input)
        else:
            code, out, err = run_python_file(path, sample_input)

    def norm(s):
        return s.strip().replace('\r\n', '\n')

    if code == -1 and err == 'TIMEOUT':
        return False, 'Timeout'
    if err:
        return False, f'Runtime error:\n{err.strip()}'
    if norm(out) == norm(sample_output):
        return True, 'Accepted'
    return False, f'Wrong Answer\nExpected:\n{sample_output}\nGot:\n{out}'
