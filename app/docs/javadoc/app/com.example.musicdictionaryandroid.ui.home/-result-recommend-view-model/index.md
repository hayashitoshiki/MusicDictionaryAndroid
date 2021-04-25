[app](../../index.md) / [com.example.musicdictionaryandroid.ui.home](../index.md) / [ResultRecommendViewModel](./index.md)

# ResultRecommendViewModel

`class ResultRecommendViewModel : ViewModel`

おすすめアーティスト検索結果画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResultRecommendViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-user-use-case/index.md)`, artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`)`<br>おすすめアーティスト検索結果画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>>` |

### Functions

| Name | Summary |
|---|---|
| [getRecommend](get-recommend.md) | `fun getRecommend(): Job`<br>アーティスト検索 |
