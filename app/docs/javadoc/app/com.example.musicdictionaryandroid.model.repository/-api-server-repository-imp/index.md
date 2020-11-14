[app](../../index.md) / [com.example.musicdictionaryandroid.model.repository](../index.md) / [ApiServerRepositoryImp](./index.md)

# ApiServerRepositoryImp

`class ApiServerRepositoryImp : `[`ApiServerRepository`](../-api-server-repository/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ApiServerRepositoryImp()` |

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `fun addArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>アーティスト登録 |
| [changeUser](change-user.md) | `fun changeUser(user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>ユーザー情報変更 |
| [createUser](create-user.md) | `fun createUser(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>ユーザー登録 |
| [deleteArtist](delete-artist.md) | `fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>アーティスト削除 |
| [getArtistsBy](get-artists-by.md) | `fun getArtistsBy(artists: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>検索条件に一致するアーティスト取得 |
| [getArtistsByEmail](get-artists-by-email.md) | `fun getArtistsByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>ユーザの登録したアーティスト取得 |
| [getArtistsByRecommend](get-artists-by-recommend.md) | `fun getArtistsByRecommend(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>おすすめアーティスト検索 |
| [getArtistsBySoaring](get-artists-by-soaring.md) | `fun getArtistsBySoaring(): Response<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>`<br>急上昇アーティスト取得 |
| [getUserByEmail](get-user-by-email.md) | `fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`>`<br>登録したユーザーの情報取得 |
| [updateArtist](update-artist.md) | `fun updateArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, beforeName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Response<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>`<br>アーティスト更新 |
