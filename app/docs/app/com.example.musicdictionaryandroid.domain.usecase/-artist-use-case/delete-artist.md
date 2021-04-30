[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCase](index.md) / [deleteArtist](./delete-artist.md)

# deleteArtist

`abstract suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>`

アーティスト削除

1. APIのアーティスト削除（削除失敗したら終了）
2. 削除成功したらローカルDB削除

### Parameters

`name` - 削除したいアーティストの名前

**Return**
削除正常完了判定結果

