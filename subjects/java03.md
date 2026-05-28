# Java 03 — コマンドライン引数

## 共通ルール

- `Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java03.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — 最初の引数を出力する

| | |
|---|---|
| 提出ディレクトリ | `ex00/` |
| 提出ファイル | `EchoArg.java` |

`args[0]` を1行出力する `main` メソッドを実装してください。

**プロトタイプ**

```java
public class EchoArg {
    public static void main(String[] args) {
        // ここを実装する
    }
}
```

**出力例**

```
$ java EchoArg hello
hello

$ java EchoArg abc xyz
abc
```

---

### Exercise 01 — 全引数を出力する

| | |
|---|---|
| 提出ディレクトリ | `ex01/` |
| 提出ファイル | `PrintArgs.java` |

`args` の各要素を1行ずつ出力する `main` メソッドを実装してください。

**プロトタイプ**

```java
public class PrintArgs {
    public static void main(String[] args) {
        // ここを実装する
    }
}
```

**出力例**

```
$ java PrintArgs a b c
a
b
c
```

---

### Exercise 02 — 引数の合計を出力する

| | |
|---|---|
| 提出ディレクトリ | `ex02/` |
| 提出ファイル | `SumArgs.java` |

`args` の各要素を整数として解釈し、その合計を出力する `main` メソッドを実装してください。

**プロトタイプ**

```java
public class SumArgs {
    public static void main(String[] args) {
        // ここを実装する
    }
}
```

**出力例**

```
$ java SumArgs 3 5
8

$ java SumArgs 10 -3 2
9
```
