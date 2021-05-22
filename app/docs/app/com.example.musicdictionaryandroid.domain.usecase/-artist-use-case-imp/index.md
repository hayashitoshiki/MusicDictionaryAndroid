[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCaseImp](./index.md)

# ArtistUseCaseImp

`class ArtistUseCaseImp : `[`ArtistUseCase`](../-artist-use-case/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ArtistUseCaseImp(remoteArtistRepository: `[`RemoteArtistRepository`](../../com.example.data.repository/-remote-artist-repository/index.md)`, localArtistRepository: `[`LocalArtistRepository`](../../com.example.data.repository/-local-artist-repository/index.md)`, localUserRepository: `[`LocalUserRepository`](../../com.example.data.repository/-local-user-repository/index.md)`, externalScope: CoroutineScope, defaultDispatcher: CoroutineDispatcher = Dispatchers.Default)` |

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `suspend fun addArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>`<br>アーティスト登録 |
| [deleteArtist](delete-artist.md) | `suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>アーティスト削除 |
| [getArtistList](get-artist-list.md) | `fun getArtistList(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>` |
| [getArtistsBy](get-artists-by.md) | `suspend fun getArtistsBy(artist: `[`ArtistConditions`](../../com.example.domain.model.value/-artist-conditions/index.md)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.domain.model.entity/-artist-contents/index.md)`>>`<br>検索条件に一致するアーティスト取得 |
| [getArtistsByEmail](get-artists-by-email.md) | `suspend fun getArtistsByEmail(): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>`<br>ユーザの登録したアーティスト取得 ユーザーの登録したアーティストをAPIサーバーから取得する。 もしつながらない場合は、ローカルDBから取得する |
| [getArtistsByRecommend](get-artists-by-recommend.md) | `suspend fun getArtistsByRecommend(): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.domain.model.entity/-artist-contents/index.md)`>>`<br>おすすめアーティスト検索 |
| [getArtistsBySoaring](get-artists-by-soaring.md) | `suspend fun getArtistsBySoaring(): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.domain.model.entity/-artist-contents/index.md)`>>`<br>急上昇アーティスト取得 |
| [updateArtist](update-artist.md) | `suspend fun updateArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>`<br>アーティスト更新 |
