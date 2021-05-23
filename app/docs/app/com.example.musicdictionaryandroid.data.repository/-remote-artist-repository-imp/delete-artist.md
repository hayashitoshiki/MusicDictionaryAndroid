[app](../../index.md) / [com.example.data.repository](../index.md) / [RemoteArtistRepositoryImp](index.md) / [deleteArtist](./delete-artist.md)

# deleteArtist

`suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

Overrides [RemoteArtistRepository.deleteArtist](../-remote-artist-repository/delete-artist.md)

アーティスト削除

### Parameters

`name` - 削除したいアーティストの名前

`email` - ユーザのemail

**Return**
削除正常完了判定結果

