[app](../../index.md) / [com.example.musicdictionaryandroid.model.net](../index.md) / [ApiService](./index.md)

# ApiService

`interface ApiService`

各URLの管理

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `abstract fun addArtist(stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>` |
| [changeUser](change-user.md) | `abstract fun changeUser(user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>` |
| [createUser](create-user.md) | `abstract fun createUser(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>` |
| [deleteArtist](delete-artist.md) | `abstract fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`>` |
| [findByEmail](find-by-email.md) | `abstract fun findByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>` |
| [getRecommend](get-recommend.md) | `abstract fun getRecommend(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>` |
| [getSoaring](get-soaring.md) | `abstract fun getSoaring(): Call<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>` |
| [getUserByEmail](get-user-by-email.md) | `abstract fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`>` |
| [search](search.md) | `abstract fun search(stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): Call<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>>` |
| [updateArtist](update-artist.md) | `abstract fun updateArtist(stringParams: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Call<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>` |
