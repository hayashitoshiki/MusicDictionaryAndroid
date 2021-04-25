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
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>>` |

### Functions

| Name | Summary |
|---|---|
| [getSoaring](get-soaring.md) | `fun getSoaring(): Job`<br>アーティスト検索 |
