[app](../../index.md) / [com.example.data.repository](../index.md) / [RemoteArtistRepositoryImp](index.md) / [updateArtist](./update-artist.md)

# updateArtist

`suspend fun updateArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>`

Overrides [RemoteArtistRepository.updateArtist](../-remote-artist-repository/update-artist.md)

アーティスト更新

### Parameters

`artist` - 更新したいアーティストの新しいデータ

`email` - ユーザのemail

**Return**
更新正常完了判定結果

