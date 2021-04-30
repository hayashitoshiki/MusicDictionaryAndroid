[app](../../index.md) / [com.example.musicdictionaryandroid.data.remote.network.service](../index.md) / [MusicDictionaryService](index.md) / [updateArtist](./update-artist.md)

# updateArtist

`@GET("/artist/update.json") abstract suspend fun updateArtist(@QueryMap stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, @Query("email") email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`ArtistResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-artist-response-dto/index.md)