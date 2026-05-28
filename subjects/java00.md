# Java 00 — 基礎・メソッド

## 共通ルール

- `main` メソッド・`Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java00.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — Hello World

| | |
|---|---|
| 提出ディレクトリ | `Java00/ex00/` |
| 提出ファイル | `HelloWorld.java` |

`"Hello, World!"` を1行出力する静的メソッド `helloworld` を実装してください。

**プロトタイプ**

```java
public class HelloWorld {
    public static void helloworld() {
        // ここを実装する
    }
}
```

**出力例**

```
Hello, World!
```

---

### Exercise 01 — 文字列を出力する

| | |
|---|---|
| 提出ディレクトリ | `Java00/ex01/` |
| 提出ファイル | `PrintString.java` |

受け取った文字列 `s` を1行出力する静的メソッド `printstring` を実装してください。

**プロトタイプ**

```java
public class PrintString {
    public static void printstring(String s) {
        // ここを実装する
    }
}
```

**出力例**

```
printstring("Hello") → Hello
printstring("Java")  → Java
```

---

### Exercise 02 — 整数を出力する

| | |
|---|---|
| 提出ディレクトリ | `Java00/ex02/` |
| 提出ファイル | `PrintInt.java` |

受け取った整数 `n` を1行出力する静的メソッド `printint` を実装してください。

**プロトタイプ**

```java
public class PrintInt {
    public static void printint(int n) {
        // ここを実装する
    }
}
```

**出力例**

```
printint(42)  → 42
printint(-1)  → -1
printint(0)   → 0
```
