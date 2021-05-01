[app](../../index.md) / [com.example.musicdictionaryandroid.data.repository](../index.md) / [LocalArtistRepository](./index.md)

# LocalArtistRepository

`interface LocalArtistRepository`

ローカル保持のアーティスト情報に関するアクセスRepository

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `abstract suspend fun addArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト追加 |
| [deleteAll](delete-all.md) | `abstract suspend fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>全部アーティスト削除 |
| [deleteArtist](delete-artist.md) | `abstract suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト削除 |
| [findByName](find-by-name.md) | `abstract suspend fun findByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)<br>アーティスト取得 |
| [getArtistAll](get-artist-all.md) | `abstract suspend fun getArtistAll(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>`<br>全アーティスト取得 |
| [getArtistList](get-artist-list.md) | `abstract fun getArtistList(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>`<br>アーティストリスト取得 |
| [updateAll](update-all.md) | `abstract suspend fun updateAll(artists: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>全アーティスト更新 |
| [updateArtist](update-artist.md) | `abstract suspend fun updateArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト更新 |

### Inheritors

| Name | Summary |
|---|---|
| [LocalArtistRepositoryImp](../-local-artist-repository-imp/index.md) | `class LocalArtistRepositoryImp : `[`LocalArtistRepository`](./index.md) |
