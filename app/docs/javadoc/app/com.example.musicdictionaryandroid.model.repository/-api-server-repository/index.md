[app](../../index.md) / [com.example.musicdictionaryandroid.model.repository](../index.md) / [ApiServerRepository](./index.md)

# ApiServerRepository

`interface ApiServerRepository`

API呼び出し関連のRepository

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `abstract fun addArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>`<br>アーティスト登録 |
| [changeUser](change-user.md) | `abstract fun changeUser(user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>ユーザー情報変更 |
| [createUser](create-user.md) | `abstract fun createUser(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>ユーザー登録 |
| [deleteArtist](delete-artist.md) | `abstract fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>アーティスト削除 |
| [getArtistsBy](get-artists-by.md) | `abstract fun getArtistsBy(artists: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>検索条件に一致するアーティスト取得 |
| [getArtistsByEmail](get-artists-by-email.md) | `abstract fun getArtistsByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>ユーザの登録したアーティスト取得 |
| [getArtistsByRecommend](get-artists-by-recommend.md) | `abstract fun getArtistsByRecommend(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>おすすめアーティスト検索 |
| [getArtistsBySoaring](get-artists-by-soaring.md) | `abstract fun getArtistsBySoaring(): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>急上昇アーティスト取得 |
| [getUserByEmail](get-user-by-email.md) | `abstract fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`>`<br>登録したユーザーの情報取得 |
| [updateArtist](update-artist.md) | `abstract fun updateArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>`<br>アーティスト更新 |

### Inheritors

| Name | Summary |
|---|---|
| [ApiServerRepositoryImp](../-api-server-repository-imp/index.md) | `class ApiServerRepositoryImp : `[`ApiServerRepository`](./index.md) |
