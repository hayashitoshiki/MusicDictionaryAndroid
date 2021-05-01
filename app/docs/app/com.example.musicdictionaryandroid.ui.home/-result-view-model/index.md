[app](../../index.md) / [com.example.musicdictionaryandroid.ui.home](../index.md) / [ResultViewModel](./index.md)

# ResultViewModel

`class ResultViewModel : ViewModel`

検索結果画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResultViewModel(artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`)`<br>検索結果画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [isNoDataText](is-no-data-text.md) | `val isNoDataText: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistSearchContents`](../../com.example.musicdictionaryandroid.domain.model.value/-artist-search-contents/index.md)`<*>>>>` |

### Functions

| Name | Summary |
|---|---|
| [getArtists](get-artists.md) | `fun getArtists(artist: `[`ArtistConditions`](../../com.example.musicdictionaryandroid.domain.model.value/-artist-conditions/index.md)`): Job` |
