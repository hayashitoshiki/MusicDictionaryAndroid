[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCase](index.md) / [deleteArtist](./delete-artist.md)

# deleteArtist

`abstract suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?>`

アーティスト削除

1. APIのアーティスト削除（削除失敗したら終了）
2. 削除成功したらローカルDB削除

### Parameters

`name` - 削除したいアーティストの名前

`email` - ユーザのemail

**Return**
削除正常完了判定結果

