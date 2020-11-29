[app](../../index.md) / [com.example.musicdictionaryandroid.model.usecase](../index.md) / [ArtistUseCaseImp](./index.md)

# ArtistUseCaseImp

`class ArtistUseCaseImp : `[`ArtistUseCase`](../-artist-use-case/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ArtistUseCaseImp(apiRepository: `[`ApiServerRepository`](../../com.example.musicdictionaryandroid.model.repository/-api-server-repository/index.md)`, dataBaseRepository: `[`DataBaseRepository`](../../com.example.musicdictionaryandroid.model.repository/-data-base-repository/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `suspend fun addArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`?>`<br>アーティスト登録 |
| [deleteArtist](delete-artist.md) | `suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?>`<br>アーティスト削除 |
| [getArtistsBy](get-artists-by.md) | `suspend fun getArtistsBy(artists: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>`<br>検索条件に一致するアーティスト取得 |
| [getArtistsByEmail](get-artists-by-email.md) | `suspend fun getArtistsByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>`<br>ユーザの登録したアーティスト取得 ユーザーの登録したアーティストをAPIサーバーから取得する。 もしつながらない場合は、ローカルDBから取得する |
| [getArtistsByRecommend](get-artists-by-recommend.md) | `suspend fun getArtistsByRecommend(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>`<br>おすすめアーティスト検索 |
| [getArtistsBySoaring](get-artists-by-soaring.md) | `suspend fun getArtistsBySoaring(): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>`<br>急上昇アーティスト取得 |
| [updateArtist](update-artist.md) | `suspend fun updateArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`?>`<br>アーティスト更新 |
