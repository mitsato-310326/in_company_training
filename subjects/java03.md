# Java 03 — コマンドライン引数

## 共通ルール

- `main` メソッド・`Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java03.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — 最初の引数を出力する

| | |
|---|---|
| 提出ディレクトリ | `Java03/ex00/` |
| 提出ファイル | `EchoArg.java` |

文字列配列 `args` の最初の要素を出力する静的メソッド `echoarg` を実装してください。

**プロトタイプ**

```java
public class EchoArg {
    public static void echoarg(String[] args) {
        // ここを実装する
    }
}
```

**出力例**

```
echoarg(["hello"])      → hello
echoarg(["abc", "xyz"]) → abc
```

---

### Exercise 01 — 全引数を出力する

| | |
|---|---|
| 提出ディレクトリ | `Java03/ex01/` |
| 提出ファイル | `PrintArgs.java` |

文字列配列 `args` の各要素を1行ずつ出力する静的メソッド `printargs` を実装してください。

**プロトタイプ**

```java
public class PrintArgs {
    public static void printargs(String[] args) {
        // ここを実装する
    }
}
```

**出力例**

```
printargs(["a", "b", "c"]) →
a
b
c
```

---

### Exercise 02 — 引数の合計を返す

| | |
|---|---|
| 提出ディレクトリ | `Java03/ex02/` |
| 提出ファイル | `SumArgs.java` |

文字列配列 `args` の各要素を整数として解釈し、その合計を **返す** 静的メソッド `sumargs` を実装してください。

**プロトタイプ**

```java
public class SumArgs {
    public static int sumargs(String[] args) {
        // ここを実装する
    }
}
```
