[app](../../index.md) / [com.example.data.repository](../index.md) / [RemoteArtistRepositoryImp](index.md) / [addArtist](./add-artist.md)

# addArtist

`suspend fun addArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>`

Overrides [RemoteArtistRepository.addArtist](../-remote-artist-repository/add-artist.md)

アーティスト登録

### Parameters

`artist` - 登録したいアーティスト

`email` - ユーザのemail

**Return**
登録正常完了判定結果

