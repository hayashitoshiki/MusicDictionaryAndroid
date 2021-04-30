[app](../../index.md) / [com.example.musicdictionaryandroid.ui.home](../index.md) / [HomeViewModel](./index.md)

# HomeViewModel

`class HomeViewModel : ViewModel`

HOME画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HomeViewModel(localUserRepository: `[`LocalUserRepository`](../../com.example.musicdictionaryandroid.data.repository/-local-user-repository/index.md)`)`<br>HOME画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [isEnableCategoryButton](is-enable-category-button.md) | `val isEnableCategoryButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableDetailsButton](is-enable-details-button.md) | `val isEnableDetailsButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableRecommendButton](is-enable-recommend-button.md) | `val isEnableRecommendButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableSearchBar](is-enable-search-bar.md) | `val isEnableSearchBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableSoaringButton](is-enable-soaring-button.md) | `val isEnableSoaringButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [searchText](search-text.md) | `val searchText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [changeSubmitButton](change-submit-button.md) | `fun changeSubmitButton(count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getArtist](get-artist.md) | `fun getArtist(): `[`ArtistConditions`](../../com.example.musicdictionaryandroid.domain.model.value/-artist-conditions/index.md) |
