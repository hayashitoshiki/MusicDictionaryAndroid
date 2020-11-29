[app](../../index.md) / [com.example.musicdictionaryandroid.model.usecase](../index.md) / [ArtistUseCaseImp](index.md) / [updateArtist](./update-artist.md)

# updateArtist

`suspend fun updateArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`?>`

Overrides [ArtistUseCase.updateArtist](../-artist-use-case/update-artist.md)

アーティスト更新

1. APIのアーティスト更新（更新失敗したら終了）
2. 更新成功したらローカルDB更新

### Parameters

`artist` - 更新したいアーティストの新しいデータ

`beforeName` - 更新したいアーティストの元の名前

`email` - ユーザのemail

**Return**
更新正常完了判定結果

