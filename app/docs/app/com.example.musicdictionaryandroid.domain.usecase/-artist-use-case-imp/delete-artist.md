[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCaseImp](index.md) / [deleteArtist](./delete-artist.md)

# deleteArtist

`suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`

Overrides [ArtistUseCase.deleteArtist](../-artist-use-case/delete-artist.md)

アーティスト削除

1. APIのアーティスト削除（削除失敗したら終了）
2. 削除成功したらローカルDB削除

### Parameters

`name` - 削除したいアーティストの名前

**Return**
削除正常完了判定結果

