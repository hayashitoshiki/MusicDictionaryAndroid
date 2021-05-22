[app](../../index.md) / [com.example.musicdictionaryandroid.ui.mypage](../index.md) / [MyPageArtistViewModel](./index.md)

# MyPageArtistViewModel

`class MyPageArtistViewModel : ViewModel`

登録済みアーティスト一覧画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MyPageArtistViewModel(artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`)`<br>登録済みアーティスト一覧画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [artistList](artist-list.md) | `val artistList: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>` |
| [isNoDataText](is-no-data-text.md) | `val isNoDataText: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |

### Functions

| Name | Summary |
|---|---|
| [deleteArtist](delete-artist.md) | `fun deleteArtist(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`): Job` |
