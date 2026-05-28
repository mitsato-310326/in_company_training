# Java 01 — 計算と戻り値

## 共通ルール

- `main` メソッド・`Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java01.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — 合計を出力する

| | |
|---|---|
| 提出ディレクトリ | `ex00/` |
| 提出ファイル | `SumOutput.java` |

2 つの整数の合計を出力する静的メソッド `printsum` を実装してください。

**プロトタイプ**

```java
public class SumOutput {
    public static void printsum(int a, int b) {
        // ここを実装する
    }
}
```

**出力例**

```
printsum(3, 5)   → 8
printsum(-1, 1)  → 0
```

---

### Exercise 01 — 合計を返す

| | |
|---|---|
| 提出ディレクトリ | `ex01/` |
| 提出ファイル | `SumReturn.java` |

2 つの整数の合計を **返す** 静的メソッド `returnsum` を実装してください。

**プロトタイプ**

```java
public class SumReturn {
    public static int returnsum(int a, int b) {
        // ここを実装する
    }
}
```

---

### Exercise 02 — 割り算（小数）

| | |
|---|---|
| 提出ディレクトリ | `ex02/` |
| 提出ファイル | `DivideReturn.java` |

整数 `a` を整数 `b` で割った結果を `double` で **返す** 静的メソッド `returndivide` を実装してください。

**プロトタイプ**

```java
public class DivideReturn {
    public static double returndivide(int a, int b) {
        // ここを実装する
    }
}
```
