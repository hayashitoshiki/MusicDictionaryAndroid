[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [ArtistUseCaseImp](index.md) / [getArtistsByEmail](./get-artists-by-email.md)

# getArtistsByEmail

`suspend fun getArtistsByEmail(): `[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>`

Overrides [ArtistUseCase.getArtistsByEmail](../-artist-use-case/get-artists-by-email.md)

ユーザの登録したアーティスト取得
ユーザーの登録したアーティストをAPIサーバーから取得する。
もしつながらない場合は、ローカルDBから取得する

**Return**
登録済みアーティスト一覧

