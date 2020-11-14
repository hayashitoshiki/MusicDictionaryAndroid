[app](../../index.md) / [com.example.musicdictionaryandroid.ui.home](../index.md) / [HomeViewModel](./index.md)

# HomeViewModel

`class HomeViewModel : ViewModel`

HOME画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HomeViewModel(preferenceRepository: `[`PreferenceRepositoryImp`](../../com.example.musicdictionaryandroid.model.repository/-preference-repository-imp/index.md)`)`<br>HOME画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [artistsFrom](artists-from.md) | `val artistsFrom: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md) |
| [isCategoryButton](is-category-button.md) | `val isCategoryButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isDetailsButton](is-details-button.md) | `val isDetailsButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableCategoryButton](is-enable-category-button.md) | `val isEnableCategoryButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableDetailsButton](is-enable-details-button.md) | `val isEnableDetailsButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableRecommendButton](is-enable-recommend-button.md) | `val isEnableRecommendButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableSoaringButton](is-enable-soaring-button.md) | `val isEnableSoaringButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isRecommendButton](is-recommend-button.md) | `val isRecommendButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isSearchBar](is-search-bar.md) | `val isSearchBar: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isSoaringButton](is-soaring-button.md) | `val isSoaringButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isSubmitButton](is-submit-button.md) | `val isSubmitButton: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [searchText](search-text.md) | `val searchText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [changeSubmitButton](change-submit-button.md) | `fun changeSubmitButton(count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>検索ボタン活性・非活性制御 |
