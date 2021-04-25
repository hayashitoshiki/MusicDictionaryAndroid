[app](../../index.md) / [com.example.musicdictionaryandroid.ui.mypage](../index.md) / [MyPageArtistViewModel](./index.md)

# MyPageArtistViewModel

`class MyPageArtistViewModel : ViewModel`

登録済みアーティスト一覧画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MyPageArtistViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-user-use-case/index.md)`, artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`)`<br>登録済みアーティスト一覧画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [email](email.md) | `var email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>>` |

### Functions

| Name | Summary |
|---|---|
| [deleteArtist](delete-artist.md) | `fun deleteArtist(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): Job`<br>アーティスト削除 |
| [getArtistsByEmail](get-artists-by-email.md) | `fun getArtistsByEmail(): Job`<br>登録済みアーティスト取得 |
