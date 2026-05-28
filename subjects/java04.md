# Java 04 — クラスとカプセル化

## 共通ルール

- `main` メソッド・`Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java04.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — Profile クラスを使う

| | |
|---|---|
| 提出ディレクトリ | `Java04/ex00/` |
| 提出ファイル | `UseProfile.java` |
| サーバー提供 | `Profile.java`（提出不要） |

以下の `Profile` クラスがサーバーから提供されます。

```java
public class Profile {
    private String name;
    private int age;

    public void setName(String name) { ... }
    public String getName()          { ... }
    public void setAge(int age)      { ... }
    public int getAge()              { ... }
}
```

`Profile` のインスタンスを作り、`setter` で値をセットして、その `Profile` オブジェクトを **返す** 静的メソッド `create` を実装してください。

**プロトタイプ**

```java
public class UseProfile {
    public static Profile create(String name, int age) {
        // ここを実装する
    }
}
```

---

### Exercise 01 — 履歴書クラスを作る

| | |
|---|---|
| 提出ディレクトリ | `Java04/ex01/` |
| 提出ファイル | `Resume.java` |

`name`（文字列）・`age`（整数）・`job`（文字列）の 3 つのフィールドを持つ `Resume` クラスを実装してください。
各フィールドは `private` にし、`setter` と `getter` を用意してください。
さらに、引数なしのデフォルトコンストラクタと、3 フィールドを受け取るコンストラクタを定義してください。

**プロトタイプ**

```java
public class Resume {
    public Resume()                                 { ... }
    public Resume(String name, int age, String job) { ... }
    public void setName(String name) { ... }
    public String getName()          { ... }
    public void setAge(int age)      { ... }
    public int getAge()              { ... }
    public void setJob(String job)   { ... }
    public String getJob()           { ... }
}
```

デフォルトコンストラクタでは `name = "Unknown"`、`age = 0`、`job = "Unknown"` とすること。
