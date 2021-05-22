[app](../../index.md) / [com.example.musicdictionaryandroid.ui.home](../index.md) / [ResultRecommendViewModel](./index.md)

# ResultRecommendViewModel

`class ResultRecommendViewModel : ViewModel`

おすすめアーティスト検索結果画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResultRecommendViewModel(artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`)`<br>おすすめアーティスト検索結果画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ArtistSearchContents`](../../com.example.domain.model.value/-artist-search-contents/index.md)`<*>>>>` |

### Functions

| Name | Summary |
|---|---|
| [getRecommend](get-recommend.md) | `fun getRecommend(): Job` |
