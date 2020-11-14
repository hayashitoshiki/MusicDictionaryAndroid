[app](../../index.md) / [com.example.musicdictionaryandroid.model.usecase](../index.md) / [ArtistUseCase](index.md) / [addArtist](./add-artist.md)

# addArtist

`abstract suspend fun addArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?>`

アーティスト登録

1. APIへ登録する（登録失敗したら終了）
2. 登録できたらローカルDBへ登録　

### Parameters

`artist` - 登録したいアーティスト

`email` - ユーザのemail

**Return**
登録正常完了判定結果

