[app](./index.md)

音楽辞書アプリ for Android

### アーキテクチャ

MVVM + Clean Architecture + DDD

## 大枠のパッケージ

#### Data層

| パッケージ名|詳細|
|:-----------|:------------|
|Repository|ローカル or リモートへのCRUD定義|
|Local　　  |ローカルデータへのアクセス定義|
|Remote     |リモートデータへのアクセス定義|

主にデータベースやAPIへのデータのCRUDを行う。

#### Domain層

| パッケージ名|詳細|
|:-----------|:------------|
|UseCase  |ビジネスロジック定義|
|Model　　|ドメインモデル定義|

主に各機能の処理を行う。
Data・Domain・Presentationの全ての層でドメインモデルで定義された値を用いて処理を行う。

#### UI(Presentation)層

| パッケージ名|詳細|
|:-----------|:------------|
|Util|Presentation層内の共通処理定義|
|その他|各タブの画面定義|

各画面の仕様詳細を定義する。

### Packages

| Name | Summary |
|---|---|
| [com.example.musicdictionaryandroid.database](com.example.musicdictionaryandroid.database/index.md) | DB管理 |
| [com.example.data.local.database.dao](com.example.data.local.database.dao/index.md) | DBのクエリ関連 |
| [com.example.musicdictionaryandroid.database.entity](com.example.musicdictionaryandroid.database.entity/index.md) | DBのテーブル関連 |
| [com.example.musicdictionaryandroid.data.local.preferences](com.example.musicdictionaryandroid.data.local.preferences/index.md) | SharedPreferences関連 |
| [com.example.musicdictionaryandroid.data.remote.firebase](com.example.musicdictionaryandroid.data.remote.firebase/index.md) |  |
| [com.example.musicdictionaryandroid.data.remote.network](com.example.musicdictionaryandroid.data.remote.network/index.md) | ネットワーク関連 |
| [com.example.musicdictionaryandroid.data.remote.network.dto](com.example.musicdictionaryandroid.data.remote.network.dto/index.md) | ネットワーク受け渡しオブジェクト関連 |
| [com.example.musicdictionaryandroid.data.remote.network.service](com.example.musicdictionaryandroid.data.remote.network.service/index.md) | ネットワークアクセスURL関連 |
| [com.example.data.repository](com.example.data.repository/index.md) | Data層データ受け渡し関連 |
| [com.example.domain.model.entity](com.example.domain.model.entity/index.md) | ドメインエンティティ関連 |
| [com.example.domain.model.value](com.example.domain.model.value/index.md) | 値オブジェクト関連 |
| [com.example.musicdictionaryandroid.domain.usecase](com.example.musicdictionaryandroid.domain.usecase/index.md) | ビジネスロジック関連 |
| [com.example.musicdictionaryandroid.ui](com.example.musicdictionaryandroid.ui/index.md) | 共通画面関連 |
| [com.example.musicdictionaryandroid.ui.home](com.example.musicdictionaryandroid.ui.home/index.md) | ホームタブ画面関連 |
| [com.example.musicdictionaryandroid.ui.login](com.example.musicdictionaryandroid.ui.login/index.md) | ログイン系画面関連 |
| [com.example.musicdictionaryandroid.ui.mypage](com.example.musicdictionaryandroid.ui.mypage/index.md) | 設定タブ画面関連 |
| [com.example.musicdictionaryandroid.ui.util](com.example.musicdictionaryandroid.ui.util/index.md) | Presentation層共通処理関連 |
| [com.example.musicdictionaryandroid.ui.util.transition](com.example.musicdictionaryandroid.ui.util.transition/index.md) | アニメーション関連 |

### Index

[All Types](alltypes/index.md)