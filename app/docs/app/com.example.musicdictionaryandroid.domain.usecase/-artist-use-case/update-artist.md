[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCase](index.md) / [updateArtist](./update-artist.md)

# updateArtist

`abstract suspend fun updateArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>`

アーティスト更新

1. APIのアーティスト更新（更新失敗したら終了）
2. 更新成功したらローカルDB更新

### Parameters

`artist` - 更新したいアーティストの新しいデータ

**Return**
更新正常完了判定結果

