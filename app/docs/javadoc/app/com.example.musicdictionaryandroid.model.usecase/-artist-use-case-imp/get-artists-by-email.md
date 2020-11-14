[app](../../index.md) / [com.example.musicdictionaryandroid.model.usecase](../index.md) / [ArtistUseCaseImp](index.md) / [getArtistsByEmail](./get-artists-by-email.md)

# getArtistsByEmail

`suspend fun getArtistsByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`ArrayList`](https://developer.android.com/reference/java/util/ArrayList.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>`

Overrides [ArtistUseCase.getArtistsByEmail](../-artist-use-case/get-artists-by-email.md)

ユーザの登録したアーティスト取得
ユーザーの登録したアーティストをAPIサーバーから取得する。
もしつながらない場合は、ローカルDBから取得する

### Parameters

`email` - ユーザーのemail

**Return**
登録済みアーティスト一覧

