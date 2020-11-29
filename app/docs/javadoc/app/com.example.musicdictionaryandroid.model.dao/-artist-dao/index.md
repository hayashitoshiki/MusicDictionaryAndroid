[app](../../index.md) / [com.example.musicdictionaryandroid.model.dao](../index.md) / [ArtistDao](./index.md)

# ArtistDao

`interface ArtistDao`

DB呼び出しクエリ管理

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(artist: `[`Artist`](../../com.example.musicdictionaryandroid.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [deleteAll](delete-all.md) | `abstract fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getAll](get-all.md) | `abstract fun getAll(): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.model.entity/-artist/index.md)`>` |
| [getArtistByName](get-artist-by-name.md) | `abstract fun getArtistByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Artist`](../../com.example.musicdictionaryandroid.model.entity/-artist/index.md) |
| [insert](insert.md) | `abstract suspend fun insert(artist: `[`Artist`](../../com.example.musicdictionaryandroid.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [update](update.md) | `abstract fun update(artist: `[`Artist`](../../com.example.musicdictionaryandroid.model.entity/-artist/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
