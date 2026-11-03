# パンくずリスト サンプルコード

## データ構造

RDBMSはPostgreSQLを使用する。

### 標準機能のみで実装する場合

- 各レコードが親IDを持つようなテーブルで表現する。

| id [PK] | name     | parend_id |
| ------- | -------- | --------- |
| 1       | ホーム   | null      |
| 2       | 商品管理 | 1         |
| 3       | 商品一覧 | 2         |
| 4       | 商品詳細 | 3         |

### 拡張機能を使用する場合

- 拡張機能である`Itree`型を使用する。
  - インストール方法: `CREATE EXTENSION ltree`
- `Itree`は階層的なツリー構造データを表現するためのデータ型。

| id [PK] | name     | path                     |
| ------- | -------- | ------------------------ |
| 1       | ホーム   | home                     |
| 2       | 商品管理 | home.product             |
| 3       | 商品一覧 | home.product.list        |
| 4       | 商品詳細 | home.product.list.detail |

## 動作確認

- ビルド + デプロイ
  - `./gradlew bootRun`
- 以下にアクセス
  - `http://localhost:8080/api/breadcrumbs/4`
