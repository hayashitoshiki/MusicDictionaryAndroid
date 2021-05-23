[app](../../index.md) / [com.example.data.repository](../index.md) / [RemoteArtistRepositoryImp](index.md) / [getArtistsByEmail](./get-artists-by-email.md)

# getArtistsByEmail

`suspend fun getArtistsByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>`

Overrides [RemoteArtistRepository.getArtistsByEmail](../-remote-artist-repository/get-artists-by-email.md)

ユーザの登録したアーティスト取得

### Parameters

`email` - ユーザのemail

**Return**
登録済みアーティスト一覧

