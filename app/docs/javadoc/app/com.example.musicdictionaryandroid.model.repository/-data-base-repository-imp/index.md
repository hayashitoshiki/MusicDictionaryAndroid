[app](../../index.md) / [com.example.musicdictionaryandroid.model.repository](../index.md) / [DataBaseRepositoryImp](./index.md)

# DataBaseRepositoryImp

`class DataBaseRepositoryImp : `[`DataBaseRepository`](../-data-base-repository/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DataBaseRepositoryImp()` |

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `fun addArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト追加 |
| [deleteAll](delete-all.md) | `fun deleteAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>全部アーティスト削除 |
| [deleteArtist](delete-artist.md) | `fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト削除 |
| [findByName](find-by-name.md) | `fun findByName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Artist`](../../com.example.musicdictionaryandroid.model.entity/-artist/index.md)<br>アーティスト取得 |
| [getArtistAll](get-artist-all.md) | `fun getArtistAll(): `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>`<br>全アーティスト取得 |
| [updateAll](update-all.md) | `fun updateAll(artists: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>全アーティスト更新 |
| [updateArtist](update-artist.md) | `fun updateArtist(beforeName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>アーティスト更新 |
