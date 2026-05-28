# Java 02 — 制御フロー

## 共通ルール

- `main` メソッド・`Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java02.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — 偶数・奇数判定

| | |
|---|---|
| 提出ディレクトリ | `Java02/ex00/` |
| 提出ファイル | `EvenOdd.java` |

整数 `n` が偶数なら `"even"`、奇数なら `"odd"` を出力する静的メソッド `evenodd` を実装してください。

**プロトタイプ**

```java
public class EvenOdd {
    public static void evenodd(int n) {
        // ここを実装する
    }
}
```

**出力例**

```
evenodd(4)  → even
evenodd(3)  → odd
evenodd(0)  → even
```

---

### Exercise 01 — FizzBuzz

| | |
|---|---|
| 提出ディレクトリ | `Java02/ex01/` |
| 提出ファイル | `FizzBuzz.java` |

1 から `n` までの数を1行ずつ出力する静的メソッド `fizzbuzz` を実装してください。
ただし以下のルールに従ってください。

- 3 の倍数のとき → `Fizz` を出力
- 5 の倍数のとき → `Buzz` を出力
- 3 と 5 両方の倍数のとき → `FizzBuzz` を出力

**プロトタイプ**

```java
public class FizzBuzz {
    public static void fizzbuzz(int n) {
        // ここを実装する
    }
}
```

**出力例（n = 5 のとき）**

```
1
2
Fizz
4
Buzz
```

---

### Exercise 02 — 素数判定

| | |
|---|---|
| 提出ディレクトリ | `Java02/ex02/` |
| 提出ファイル | `PrimeCheck.java` |

整数 `n` が素数かどうかを返す静的メソッド `primecheck` を実装してください。
素数なら `true`、そうでなければ `false` を返してください。

> **ヒント:** 1 は素数ではありません。2 は素数です。

**プロトタイプ**

```java
public class PrimeCheck {
    public static boolean primecheck(int n) {
        // ここを実装する
    }
}
```

---

### Exercise 03 — 配列の逆順出力

| | |
|---|---|
| 提出ディレクトリ | `Java02/ex03/` |
| 提出ファイル | `ReverseArray.java` |

整数配列 `arr` を逆順に `", "` 区切りで1行出力する静的メソッド `reversearray` を実装してください。
元の配列は変更しないでください。

**プロトタイプ**

```java
public class ReverseArray {
    public static void reversearray(int[] arr) {
        // ここを実装する
    }
}
```

**出力例**

```
reversearray([1, 2, 3, 4, 5]) → 5, 4, 3, 2, 1
reversearray([3, 1, 2])       → 2, 1, 3
reversearray([10, 20])        → 20, 10
```
