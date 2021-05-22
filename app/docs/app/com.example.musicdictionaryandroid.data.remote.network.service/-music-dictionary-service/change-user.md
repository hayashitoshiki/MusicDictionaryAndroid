[app](../../index.md) / [com.example.musicdictionaryandroid.data.remote.network.service](../index.md) / [MusicDictionaryService](index.md) / [changeUser](./change-user.md)

# changeUser

`@GET("user/update.json") abstract suspend fun changeUser(@Query("user") user: `[`User`](../../com.example.domain.model.entity/-user/index.md)`, @Query("email") email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`StatusResponseDto`](../../com.example.musicdictionaryandroid.data.remote.network.dto/-status-response-dto/index.md)