[app](../../index.md) / [com.example.musicdictionaryandroid.data.remote.network.service](../index.md) / [MusicDictionaryService](./index.md)

# MusicDictionaryService

`interface MusicDictionaryService`

MusicDictionaryApiの各URLの管理

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `abstract suspend fun addArtist(stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArtistResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artist-response-dto/index.md) |
| [changeUser](change-user.md) | `abstract suspend fun changeUser(user: `[`User`](../../com.example.domain.model.entity/-user/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`StatusResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-status-response-dto/index.md) |
| [createUser](create-user.md) | `abstract suspend fun createUser(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`StatusResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-status-response-dto/index.md) |
| [deleteArtist](delete-artist.md) | `abstract suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`StatusResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-status-response-dto/index.md) |
| [findByEmail](find-by-email.md) | `abstract suspend fun findByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArtistsResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artists-response-dto/index.md) |
| [getRecommend](get-recommend.md) | `abstract suspend fun getRecommend(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArtistsResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artists-response-dto/index.md) |
| [getSoaring](get-soaring.md) | `abstract suspend fun getSoaring(): `[`ArtistsResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artists-response-dto/index.md) |
| [getUserByEmail](get-user-by-email.md) | `abstract suspend fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`UserResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-user-response-dto/index.md) |
| [search](search.md) | `abstract suspend fun search(stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`ArtistsResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artists-response-dto/index.md) |
| [updateArtist](update-artist.md) | `abstract suspend fun updateArtist(stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArtistResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artist-response-dto/index.md) |
