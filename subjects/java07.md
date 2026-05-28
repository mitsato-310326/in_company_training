# Java 07 — 例外処理

## 共通ルール

- `Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java07.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — try-catch で例外を捕まえる

| | |
|---|---|
| 提出ディレクトリ | `ex00/` |
| 提出ファイル | `TryCatch.java` |

文字列 `s` を整数に変換し、変換できれば整数を文字列で返し、
できなければ `"Error: invalid input"` を返す静的メソッド `parse` を実装してください。
`Integer.parseInt(s)` が投げる `NumberFormatException` を `try-catch` で捕まえてください。

**プロトタイプ**

```java
public class TryCatch {
    public static String parse(String s) {
        // ここを実装する
    }
}
```

---

### Exercise 01 — 独自例外を定義して throw する

| | |
|---|---|
| 提出ディレクトリ | `ex01/` |
| 提出ファイル | `InvalidAgeException.java`、`AgeValidator.java` |

**InvalidAgeException** — `Exception` を継承し、`String message` を受け取るコンストラクタを持つ独自例外クラスを実装してください。

**AgeValidator** — `validate(int age)` は `age` が `0〜150` の範囲内なら `age` をそのまま返し、範囲外なら `InvalidAgeException("Age must be between 0 and 150")` を throw してください。

**プロトタイプ**

```java
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) { ... }
}

public class AgeValidator {
    public static int validate(int age) throws InvalidAgeException {
        // ここを実装する
    }
}
```

---

### Exercise 02 — throws で例外を伝播させる

| | |
|---|---|
| 提出ディレクトリ | `ex02/` |
| 提出ファイル | `ThrowingParser.java` |

文字列を整数に変換し、その値を返す静的メソッド `parse` を実装してください。
例外を内部で catch せず、そのまま呼び出し元に伝播させてください。

**プロトタイプ**

```java
public class ThrowingParser {
    public static int parse(String s) throws NumberFormatException {
        // ここを実装する
    }
}
```
