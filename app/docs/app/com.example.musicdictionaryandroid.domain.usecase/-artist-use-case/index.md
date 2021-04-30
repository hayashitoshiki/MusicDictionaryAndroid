[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCase](./index.md)

# ArtistUseCase

`interface ArtistUseCase`

アーティストに関するビジネスロジック

### Functions

| Name | Summary |
|---|---|
| [addArtist](add-artist.md) | `abstract suspend fun addArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>`<br>アーティスト登録 |
| [deleteArtist](delete-artist.md) | `abstract suspend fun deleteArtist(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>`<br>アーティスト削除 |
| [getArtistList](get-artist-list.md) | `abstract fun getArtistList(): Flow<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>` |
| [getArtistsBy](get-artists-by.md) | `abstract suspend fun getArtistsBy(artist: `[`ArtistConditions`](../../com.example.musicdictionaryandroid.domain.model.value/-artist-conditions/index.md)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist-contents/index.md)`>>`<br>検索条件に一致するアーティスト取得 |
| [getArtistsByEmail](get-artists-by-email.md) | `abstract suspend fun getArtistsByEmail(): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>>`<br>ユーザの登録したアーティスト取得 ユーザーの登録したアーティストをAPIサーバーから取得する。 もしつながらない場合は、ローカルDBから取得する |
| [getArtistsByRecommend](get-artists-by-recommend.md) | `abstract suspend fun getArtistsByRecommend(): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist-contents/index.md)`>>`<br>おすすめアーティスト検索 |
| [getArtistsBySoaring](get-artists-by-soaring.md) | `abstract suspend fun getArtistsBySoaring(): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistContents`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist-contents/index.md)`>>`<br>急上昇アーティスト取得 |
| [updateArtist](update-artist.md) | `abstract suspend fun updateArtist(artist: `[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`Artist`](../../com.example.musicdictionaryandroid.domain.model.entity/-artist/index.md)`>`<br>アーティスト更新 |

### Inheritors

| Name | Summary |
|---|---|
| [ArtistUseCaseImp](../-artist-use-case-imp/index.md) | `class ArtistUseCaseImp : `[`ArtistUseCase`](./index.md) |
