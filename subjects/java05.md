# Java 05 — 抽象クラス・継承・ポリモーフィズム

## 共通ルール

- `main` メソッド・`Scanner` は **禁止** です
- 提出ファイルは ZIP にまとめ、ファイル名を `Java05.zip` にしてください
- ZIP 内のディレクトリ構造は各課題の「提出ディレクトリ」に従ってください
- コンパイルエラーが出る提出は認められません
- サーバー提供と記載されたファイルは **提出不要** です

---

### Exercise 00 — Goomba と KoopaTroopa

| | |
|---|---|
| 提出ディレクトリ | `Java05/ex00/` |
| 提出ファイル | `Goomba.java`、`KoopaTroopa.java` |

2 種類のゲームキャラクターを独立したクラスとして実装せよ。

**Goomba**
- `status` は `"walking"`、`alive` を `true` で初期化
- `stomp()` → `status = "dead"`、`alive = false`
- `getStatus()` / `isAlive()`

**KoopaTroopa**
- `status` は `"walking"`、`alive` を `true` で初期化
- `stomp()` `alive`は更新されず、関数が呼ばれるごとに`status`が`"stop"`と`"spin"`に交互に切り替わる。
- `getStatus()` / `isAlive()`

```
    Goomba はクリボーの英名、
    KoopaTroopa はノコノコのです。
```

---

### Exercise 01 — Enemy 抽象クラスとポリモーフィズム

| | |
|---|---|
| 提出ディレクトリ | `Java05/ex01/` |
| 提出ファイル | `Enemy.java`、`Goomba.java`、`KoopaTroopa.java`、`Enemies.java` |
| テンプレート | `Enemy.java`（配布済み） |

配布された `Enemy.java` をベースに、`Goomba` と `KoopaTroopa` を継承させよ。
`Enemies` クラスの `makeEnemyArray(int[] ids)` は `ids` の各要素に対応したオブジェクトの配列を返すこと。

- `0` → `Goomba`
- `1` → `KoopaTroopa`

```
    引数として
     {0, 1, 0, 0, 1}
    が与えられた場合
     {Goomba, KoopaTroopa, Goomba, Goomba, KoopaTroopa}
    を返す様設計してください。
```

```java
public class Enemies {
    public static Enemy[] makeEnemyArray(int[] ids) { ... }
}
```
