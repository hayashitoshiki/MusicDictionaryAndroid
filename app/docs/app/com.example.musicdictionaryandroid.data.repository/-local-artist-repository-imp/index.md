[app](../../index.md) / [com.example.musicdictionaryandroid.data.repository](../index.md) / [LocalArtistRepositoryImp](./index.md)

# LocalArtistRepositoryImp

`class LocalArtistRepositoryImp : `[`LocalArtistRepository`](../-local-artist-repository/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocalArtistRepositoryImp(dao: `[`ArtistDao`](../../com.example.musicdictionaryandroid.data.local.database.dao/-artist-dao/index.md)`, ioDispatcher: CoroutineDispatcher = Dispatchers.IO)` |

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `suspend fun addArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`): <ERROR CLASS>`<br>アーティスト追加 |
| [deleteAll](delete-all.md) | `suspend fun deleteAll(): <ERROR CLASS>`<br>全部アーティスト削除 |
| [deleteArtist](delete-artist.md) | `suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): <ERROR CLASS>`<br>アーティスト削除 |
| [getArtistAll](get-artist-all.md) | `fun getArtistAll(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>`<br>全登録済みアーティス取得 |
| [getArtistByName](get-artist-by-name.md) | `suspend fun getArtistByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)<br>登録済みアーティスト取得 |
| [updateAll](update-all.md) | `suspend fun updateAll(artists: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>): <ERROR CLASS>`<br>全アーティスト更新 |
| [updateArtist](update-artist.md) | `suspend fun updateArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`): <ERROR CLASS>`<br>アーティスト更新 |
