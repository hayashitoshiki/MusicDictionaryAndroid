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
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>?>>` |

### Functions

| Name | Summary |
|---|---|
| [getArtists](get-artists.md) | `fun getArtists(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): Job`<br>アーティスト検索 |
