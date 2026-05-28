# Java 06 — インターフェース（ブレーメンの音楽隊）

## 共通ルール

- `Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java06.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — Walk インターフェースと動物たち

| | |
|---|---|
| 提出ディレクトリ | `ex00/` |
| 提出ファイル | `Walk.java`、`Donkey.java`、`Dog.java`、`Cat.java`、`Animals.java` |
| テンプレート | `Walk.java`（配布済み） |

配布された `Walk.java` を参照し、`Donkey`・`Dog`・`Cat` に実装せよ。

```java
public class Animals {
    public static Walk[] make_animals_array(int[] ids) { ... }
}
```

`ids` の各要素に対応したオブジェクトの配列を返すこと。

- `0` → `Donkey`
- `1` → `Dog`
- `2` → `Cat`

---

### Exercise 01 — Bremen インターフェースとバンドの結成

| | |
|---|---|
| 提出ディレクトリ | `ex01/` |
| 提出ファイル | `Bremen.java`、`Donkey.java`、`Dog.java`、`Cat.java`、`Chicken.java`、`Band.java` |
| サーバー提供 | `Walk.java`（提出不要） |
| テンプレート | `Bremen.java`（配布済み） |

配布された `Bremen.java` を参照し、各クラスに実装せよ。
`Donkey`・`Dog`・`Cat` は `Walk` と `Bremen` の両方を実装すること。
`Chicken` は `Bremen` のみ実装すること。

各クラスの `perform()` 出力:

| クラス | 出力 |
|---|---|
| `Donkey` | `IA` |
| `Dog` | `Bow-wow` |
| `Cat` | `Meow` |
| `Chicken` | `Cock-a-doodle-doo` |

```java
public class Band {
    public static Bremen[] formBand(int[] ids) { ... }
}
```

`ids` の各要素に対応したオブジェクトの配列を返すこと。

- `0` → `Donkey`
- `1` → `Dog`
- `2` → `Cat`
- `3` → `Chicken`
