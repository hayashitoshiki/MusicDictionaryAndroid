[app](../../index.md) / [com.example.musicdictionaryandroid.ui.home](../index.md) / [ResultSoaringViewModel](./index.md)

# ResultSoaringViewModel

`class ResultSoaringViewModel : ViewModel`

急上昇アーティスト一覧画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResultSoaringViewModel(artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`)`<br>急上昇アーティスト一覧画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistSearchContents`](../../com.example.domain.model.value/-artist-search-contents/index.md)`<*>>>>` |

### Functions

| Name | Summary |
|---|---|
| [getSoaring](get-soaring.md) | `fun getSoaring(): Job` |
