[app](../../index.md) / [com.example.data.repository](../index.md) / [LocalArtistRepository](./index.md)

# LocalArtistRepository

`interface LocalArtistRepository`

ローカル保持のアーティスト情報に関するアクセスRepository

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `abstract suspend fun addArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト追加 |
| [deleteAll](delete-all.md) | `abstract suspend fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>全部アーティスト削除 |
| [deleteArtist](delete-artist.md) | `abstract suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト削除 |
| [getArtistAll](get-artist-all.md) | `abstract fun getArtistAll(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>`<br>全登録済みアーティス取得 |
| [getArtistByName](get-artist-by-name.md) | `abstract suspend fun getArtistByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)<br>登録済みアーティスト取得 |
| [updateAll](update-all.md) | `abstract suspend fun updateAll(artists: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>全アーティスト更新 |
| [updateArtist](update-artist.md) | `abstract suspend fun updateArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト更新 |

### Inheritors

| Name | Summary |
|---|---|
| [LocalArtistRepositoryImp](../-local-artist-repository-imp/index.md) | `class LocalArtistRepositoryImp : `[`LocalArtistRepository`](./index.md) |
