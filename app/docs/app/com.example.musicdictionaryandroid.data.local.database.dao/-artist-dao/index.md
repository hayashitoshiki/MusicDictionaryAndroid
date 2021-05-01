[app](../../index.md) / [com.example.musicdictionaryandroid.data.local.database.dao](../index.md) / [ArtistDao](./index.md)

# ArtistDao

`interface ArtistDao`

アーティストテーブル呼び出しクエリ管理

### Functions

| Name | Summary |
|---|---|
| [deleteAll](delete-all.md) | `abstract suspend fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [deleteByName](delete-by-name.md) | `abstract suspend fun deleteByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAll](get-all.md) | `abstract suspend fun getAll(): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`ArtistEntity`](../../com.example.musicdictionaryandroid.data.local.database.entity/-artist-entity/index.md)`>` |
| [getArtistByName](get-artist-by-name.md) | `abstract fun getArtistByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArtistEntity`](../../com.example.musicdictionaryandroid.data.local.database.entity/-artist-entity/index.md) |
| [getArtistList](get-artist-list.md) | `abstract fun getArtistList(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistEntity`](../../com.example.musicdictionaryandroid.data.local.database.entity/-artist-entity/index.md)`>>` |
| [insert](insert.md) | `abstract suspend fun insert(artistEntity: `[`ArtistEntity`](../../com.example.musicdictionaryandroid.data.local.database.entity/-artist-entity/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [update](update.md) | `abstract suspend fun update(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, gender: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, voice: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, lyrics: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, genre1: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, genre2: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
