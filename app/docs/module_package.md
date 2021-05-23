# Module app
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

# Package com.example.musicdictionaryandroid.database
DB管理

# Package com.example.data.local.database.dao
DBのクエリ関連

# Package com.example.musicdictionaryandroid.database.entity
DBのテーブル関連

# Package com.example.musicdictionaryandroid.data.remote.network
ネットワーク関連

# Package com.example.musicdictionaryandroid.data.remote.network.dto
ネットワーク受け渡しオブジェクト関連

# Package com.example.musicdictionaryandroid.data.remote.network.service
ネットワークアクセスURL関連

# Package com.example.data.repository
Data層データ受け渡し関連

# Package com.example.domain.model.entity
ドメインエンティティ関連

# Package com.example.musicdictionaryandroid.data.local.preferences
SharedPreferences関連

# Package com.example.domain.model.value
値オブジェクト関連

# Package com.example.musicdictionaryandroid.domain.usecase
ビジネスロジック関連

# Package com.example.musicdictionaryandroid.ui
共通画面関連

# Package com.example.musicdictionaryandroid.ui.home
ホームタブ画面関連

# Package com.example.musicdictionaryandroid.ui.mypage
設定タブ画面関連

# Package com.example.musicdictionaryandroid.ui.login
ログイン系画面関連

# Package com.example.musicdictionaryandroid.ui.util
Presentation層共通処理関連

# Package com.example.musicdictionaryandroid.ui.util.transition
アニメーション関連
