[app](../../index.md) / [com.example.musicdictionaryandroid.data.repository](../index.md) / [RemoteArtistRepositoryImp](./index.md)

# RemoteArtistRepositoryImp

`class RemoteArtistRepositoryImp : `[`RemoteArtistRepository`](../-remote-artist-repository/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RemoteArtistRepositoryImp(provider: `[`Provider`](../../com.example.musicdictionaryandroid.data.remote.network/-provider/index.md)` = ProviderImp, ioDispatcher: CoroutineDispatcher = Dispatchers.IO)` |

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `suspend fun addArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>`<br>アーティスト登録 |
| [deleteArtist](delete-artist.md) | `suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>アーティスト削除 |
| [getArtistsBy](get-artists-by.md) | `suspend fun getArtistsBy(artist: `[`ArtistConditions`](../../com.example.musicdictionaryandroid.domain.model.value/-artist-conditions/index.md)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist-contents/index.md)`>>`<br>検索条件に一致するアーティスト取得 |
| [getArtistsByEmail](get-artists-by-email.md) | `suspend fun getArtistsByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>`<br>ユーザの登録したアーティスト取得 |
| [getArtistsByRecommend](get-artists-by-recommend.md) | `suspend fun getArtistsByRecommend(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist-contents/index.md)`>>`<br>おすすめアーティスト検索 |
| [getArtistsBySoaring](get-artists-by-soaring.md) | `suspend fun getArtistsBySoaring(): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist-contents/index.md)`>>`<br>急上昇アーティスト取得 |
| [updateArtist](update-artist.md) | `suspend fun updateArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>`<br>アーティスト更新 |
