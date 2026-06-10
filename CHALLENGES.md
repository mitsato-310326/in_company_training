# Java 課題一覧（管理者リファレンス）

> 学生への課題説明は PDF で配布します。このファイルはサーバー管理者向けの仕様書です。

## 採点の仕組み

- 学生は **関数本体のみ** 実装して提出します（main・Scanner 不要）
- サーバー側の `testers/{ClassName}/Tester.java` が関数を直接呼び出して採点します
- `javac *.java && java Tester` を Docker 内で実行し、exit code 0 = 全テスト合格
- ZIP のファイル名でチャプターを決定します（`Java00.zip` → java00 の採点）
- ZIP 内の全 required_keys が OK → 次のチャンネルが開放されます

---

## 提出 ZIP の構造

```
Java00.zip → Java00/ex00/HelloWorld.java
              Java00/ex01/PrintString.java
              Java00/ex02/PrintInt.java

Java01.zip → Java01/ex00/SumOutput.java
              Java01/ex01/SumReturn.java
              Java01/ex02/DivideReturn.java

Java02.zip → Java02/ex00/EvenOdd.java
              Java02/ex01/FizzBuzz.java
              Java02/ex02/PrimeCheck.java
              Java02/ex03/ReverseArray.java

Java03.zip → Java03/ex00/EchoArg.java
              Java03/ex01/PrintArgs.java
              Java03/ex02/SumArgs.java

Java04.zip → Java04/ex00/UseProfile.java   ※ Profile.java は提出不要（サーバー提供）
              Java04/ex01/Resume.java

Java05.zip → Java05/ex00/Goomba.java
              Java05/ex00/KoopaTroopa.java
              Java05/ex01/Enemy.java          ← 抽象クラス（学生が定義）
              Java05/ex01/Goomba.java         ← extends Enemy
              Java05/ex01/KoopaTroopa.java    ← extends Enemy
              Java05/ex01/Enemies.java

Java06.zip → Java06/ex00/Walk.java           ← インターフェース（学生が定義）
              Java06/ex00/Donkey.java
              Java06/ex00/Dog.java
              Java06/ex00/Cat.java
              Java06/ex00/Animals.java
              Java06/ex01/Bremen.java         ← インターフェース（学生が定義）
              Java06/ex01/Donkey.java         ← Walk と Bremen を実装
              Java06/ex01/Dog.java
              Java06/ex01/Cat.java
              Java06/ex01/Chicken.java
              Java06/ex01/Band.java           ※ Walk.java は提出不要（サーバー提供）

Java07.zip → Java07/ex00/TryCatch.java
              Java07/ex01/InvalidAgeException.java
              Java07/ex01/AgeValidator.java
              Java07/ex02/ThrowingParser.java
```

---

## Chapter 00 — 基礎・メソッド

### ex00: Hello World
**ファイル:** `HelloWorld.java`
```java
public class HelloWorld {
    public static void helloworld() { ... }
}
```
`helloworld()` が `"Hello, World!"` を1行出力すること。

---

### ex01: 文字列出力
**ファイル:** `PrintString.java`
```java
public class PrintString {
    public static void printstring(String s) { ... }
}
```
`printstring(s)` が `s` を1行出力すること。

---

### ex02: 整数出力
**ファイル:** `PrintInt.java`
```java
public class PrintInt {
    public static void printint(int n) { ... }
}
```
`printint(n)` が `n` を1行出力すること。

---

## Chapter 01 — 計算・戻り値

### ex00: 合計を出力
**ファイル:** `SumOutput.java`
```java
public class SumOutput {
    public static void printsum(int a, int b) { ... }
}
```
`printsum(a, b)` が `a+b` を出力すること。（戻り値なし）

---

### ex01: 合計を返す
**ファイル:** `SumReturn.java`
```java
public class SumReturn {
    public static int returnsum(int a, int b) { ... }
}
```
`returnsum(a, b)` が `a+b` を return すること。

---

### ex02: 割り算（double）
**ファイル:** `DivideReturn.java`
```java
public class DivideReturn {
    public static double returndivide(int a, int b) { ... }
}
```
`returndivide(a, b)` が `(double)a / b` を return すること。

---

## Chapter 02 — 制御フロー

### ex00: 偶数・奇数判定
**ファイル:** `EvenOdd.java`
```java
public class EvenOdd {
    public static void evenodd(int n) { ... }
}
```
偶数なら `"even"`、奇数なら `"odd"` を1行出力すること。（戻り値なし）

---

### ex01: FizzBuzz
**ファイル:** `FizzBuzz.java`
```java
public class FizzBuzz {
    public static void fizzbuzz(int n) { ... }
}
```
1〜n を出力。3の倍数→Fizz、5の倍数→Buzz、両方→FizzBuzz。

---

### ex02: 素数判定
**ファイル:** `PrimeCheck.java`
```java
public class PrimeCheck {
    public static boolean primecheck(int n) { ... }
}
```
素数なら `true`、そうでなければ `false` を return。

---

### ex03: 配列逆順出力
**ファイル:** `ReverseArray.java`
```java
public class ReverseArray {
    public static void reversearray(int[] arr) { ... }
}
```
逆順に `", "` 区切りで1行出力すること。（戻り値不要）

---

## Chapter 03 — コマンドライン引数

### ex00: 最初の引数を出力
**ファイル:** `EchoArg.java`
```java
public class EchoArg {
    public static void echoarg(String[] args) { ... }
}
```
`args[0]` を1行出力すること。

---

### ex01: 全引数を出力
**ファイル:** `PrintArgs.java`
```java
public class PrintArgs {
    public static void printargs(String[] args) { ... }
}
```
`args` の各要素を1行ずつ出力すること。

---

### ex02: 引数の合計を返す
**ファイル:** `SumArgs.java`
```java
public class SumArgs {
    public static int sumargs(String[] args) { ... }
}
```
`args` の各要素を整数として解釈し、合計を return すること。

---

## Chapter 04 — クラス・getter/setter

### ex00: setter/getter の呼び出し
**ファイル:** `UseProfile.java`（**Profile.java は提出不要・サーバー提供**）
```java
public class UseProfile {
    public static Profile create(String name, int age) { ... }
}
```
`Profile` クラスのインスタンスを作り、setter で値をセットして Profile オブジェクトを return。

---

### ex01: 履歴書クラスの作成（コンストラクタのオーバーロード）
**ファイル:** `Resume.java`
```java
public class Resume {
    // name: String, age: int, job: String
    public Resume() { ... }  // name="Unknown", age=0, job="Unknown"
    public Resume(String name, int age, String job) { ... }
    public void setName(String name) { ... }
    public String getName() { ... }
    public void setAge(int age) { ... }
    public int getAge() { ... }
    public void setJob(String job) { ... }
    public String getJob() { ... }
}
```

---

## Chapter 05 — 抽象クラス・継承・ポリモーフィズム

### ex00: Goomba と KoopaTroopa の作成
**ファイル:** `Goomba.java` + `KoopaTroopa.java`
```java
public class Goomba {
    public Goomba() { ... }       // status = "walking", alive = true
    public void stomp() { ... }   // status → "dead", alive → false
    public String getStatus() { ... }
    public boolean isAlive() { ... }
}

public class KoopaTroopa {
    private boolean reversed = false;
    public KoopaTroopa() { ... }  // status = "walking"
    public void stomp() { ... }
    // 1回目: reversed = true, status = "stop"
    // 2回目以降: "stop" ↔ "spin" の交互変化。isAlive() は常に true
    public String getStatus() { ... }
    public boolean isAlive() { ... }
}
```

---

### ex01: Enemy 抽象クラスとポリモーフィズム
**ファイル:** `Enemy.java` + `Goomba.java` + `KoopaTroopa.java` + `Enemies.java`
```java
// Enemy.java（学生が定義）
public abstract class Enemy {
    protected String status;
    protected boolean alive = true;
    public abstract void stomp();
    public String getStatus()  { return status; }
    public boolean isAlive()   { return alive; }
}

// Goomba.java / KoopaTroopa.java（Enemy を継承、ex00 と同じ動作）
public class Goomba      extends Enemy { ... }
public class KoopaTroopa extends Enemy { ... }

// Enemies.java
public class Enemies {
    public static Enemy[] makeEnemyArray(int[] ids) { ... }
    // ids の各要素に対応したオブジェクトを返す: 0 → Goomba, 1 → KoopaTroopa
}
```

---

## Chapter 06 — インターフェース（ブレーメンの音楽隊）

### ex00: Walk インターフェースと動物たち
**ファイル:** `Walk.java` + `Donkey.java` + `Dog.java` + `Cat.java` + `Animals.java`
```java
// Walk.java（学生が定義）
public interface Walk {
    void walk();
}

// Donkey.java / Dog.java / Cat.java（Walk を実装）
public class Donkey implements Walk { public void walk() { ... } }
public class Dog    implements Walk { public void walk() { ... } }
public class Cat    implements Walk { public void walk() { ... } }

// Animals.java
public class Animals {
    public static Walk[] make_animals_array(int[] ids) { ... }
    // ids の各要素に対応したオブジェクトを返す: 0→Donkey, 1→Dog, 2→Cat
}
```

---

### ex02: Bremen インターフェースとバンドの結成
**ファイル:** `Bremen.java` + `Donkey.java` + `Dog.java` + `Cat.java` + `Chicken.java` + `Band.java`（**Walk.java は提出不要・サーバー提供**）
```java
// Bremen.java（学生が定義）
public interface Bremen {
    void perform();
}

// Donkey.java（Walk と Bremen の両方を実装）
public class Donkey implements Walk, Bremen {
    public void walk()    { ... }
    public void perform() { ... }  // 出力: "IA"
}
// Dog.java:     perform() → "Bow-wow"
// Cat.java:     perform() → "Meow"
// Chicken.java: Bremen のみ実装、perform() → "Cock-a-doodle-doo"

// Band.java
public class Band {
    public static Bremen[] formBand(int[] ids) { ... }
    // ids の各要素に対応したオブジェクトを返す: 0→Donkey, 1→Dog, 2→Cat, 3→Chicken
}
```

---

## Chapter 07 — 例外処理

### ex00: try-catch
**ファイル:** `TryCatch.java`
```java
public class TryCatch {
    public static String parse(String s) { ... }
}
```
`Integer.parseInt` で変換できれば整数を文字列で return、失敗すれば `"Error: invalid input"` を return。

---

### ex01: throw と独自例外
**ファイル:** `InvalidAgeException.java` + `AgeValidator.java`
```java
// InvalidAgeException.java（独自例外）
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) { super(message); }
}

// AgeValidator.java
public class AgeValidator {
    public static int validate(int age) throws InvalidAgeException { ... }
}
```
`age` が 0〜150 の範囲外なら `InvalidAgeException` を throw、範囲内なら `age` を return。

---

### ex02: throws 宣言
**ファイル:** `ThrowingParser.java`
```java
public class ThrowingParser {
    public static int parse(String s) throws NumberFormatException { ... }
}
```
`Integer.parseInt(s)` の結果をそのまま return すること。例外は内部で catch せず、呼び出し元に伝播させること。

---

## テスターファイル一覧

| 課題キー       | テスター                        | サーバー提供クラス       |
|---|---|---|
| HelloWorld     | testers/HelloWorld/Tester.java  | なし |
| PrintString    | testers/PrintString/Tester.java | なし |
| PrintInt       | testers/PrintInt/Tester.java    | なし |
| SumOutput      | testers/SumOutput/Tester.java   | なし |
| SumReturn      | testers/SumReturn/Tester.java   | なし |
| DivideReturn   | testers/DivideReturn/Tester.java| なし |
| EvenOdd        | testers/EvenOdd/Tester.java     | なし |
| FizzBuzz       | testers/FizzBuzz/Tester.java    | なし |
| PrimeCheck     | testers/PrimeCheck/Tester.java  | なし |
| ReverseArray   | testers/ReverseArray/Tester.java| なし |
| EchoArg        | testers/EchoArg/Tester.java     | なし |
| PrintArgs      | testers/PrintArgs/Tester.java   | なし |
| SumArgs        | testers/SumArgs/Tester.java     | なし |
| UseProfile     | testers/UseProfile/Tester.java  | **Profile.java** |
| Resume         | testers/Resume/Tester.java      | なし |
| Goomba         | testers/Goomba/Tester.java      | なし |
| KoopaTroopa    | testers/KoopaTroopa/Tester.java | なし |
| Enemy          | testers/Enemy/Tester.java       | なし（学生が Enemy.java・Enemies.java を定義）|
| Animals        | testers/Animals/Tester.java     | なし（学生が Walk.java・Donkey.java・Dog.java・Cat.java を定義）|
| Band           | testers/Band/Tester.java        | **Walk.java** |
| TryCatch       | testers/TryCatch/Tester.java    | なし |
| AgeValidator   | testers/AgeValidator/Tester.java| なし（InvalidAgeException は同 ZIP に含める）|
| ThrowingParser | testers/ThrowingParser/Tester.java | なし |
