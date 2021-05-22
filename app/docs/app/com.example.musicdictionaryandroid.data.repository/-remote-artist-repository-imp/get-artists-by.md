[app](../../index.md) / [com.example.data.repository](../index.md) / [RemoteArtistRepositoryImp](index.md) / [getArtistsBy](./get-artists-by.md)

# getArtistsBy

`suspend fun getArtistsBy(artist: `[`ArtistConditions`](../../com.example.domain.model.value/-artist-conditions/index.md)`): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.domain.model.entity/-artist-contents/index.md)`>>`

Overrides [RemoteArtistRepository.getArtistsBy](../-remote-artist-repository/get-artists-by.md)

検索条件に一致するアーティスト取得

### Parameters

`artist` - アーティスト検索条件

**Return**
アーティストの検索結果一覧

