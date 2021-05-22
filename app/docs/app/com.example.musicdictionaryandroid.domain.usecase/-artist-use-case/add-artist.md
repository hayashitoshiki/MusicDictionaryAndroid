[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCase](index.md) / [addArtist](./add-artist.md)

# addArtist

`abstract suspend fun addArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>`

アーティスト登録

1. APIへ登録する（登録失敗したら終了）
2. 登録できたらローカルDBへ登録　

### Parameters

`artist` - 登録したいアーティスト

**Return**
登録正常完了判定結果

