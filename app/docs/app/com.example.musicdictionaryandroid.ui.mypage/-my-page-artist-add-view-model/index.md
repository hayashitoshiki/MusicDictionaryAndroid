[app](../../index.md) / [com.example.musicdictionaryandroid.ui.mypage](../index.md) / [MyPageArtistAddViewModel](./index.md)

# MyPageArtistAddViewModel

`class MyPageArtistAddViewModel : ViewModel`

アーティスト情報登録・追加画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MyPageArtistAddViewModel(artist: `[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`?, artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-artist-use-case/index.md)`, messageUtil: `[`MessageUtil`](../../com.example.musicdictionaryandroid.ui.util/-message-util/index.md)`)`<br>アーティスト情報登録・追加画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [editMode](edit-mode.md) | `val editMode: LiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [gender](gender.md) | `val gender: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [genre1](genre1.md) | `val genre1: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [genre1List](genre1-list.md) | `val genre1List: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [genre2](genre2.md) | `val genre2: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [genre2List](genre2-list.md) | `val genre2List: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [length](length.md) | `val length: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [lyrics](lyrics.md) | `val lyrics: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [nameText](name-text.md) | `val nameText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>>` |
| [submitText](submit-text.md) | `val submitText: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [titleText](title-text.md) | `val titleText: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [voice](voice.md) | `val voice: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [changeGenre1](change-genre1.md) | `fun changeGenre1(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeGender](checked-change-gender.md) | `fun checkedChangeGender(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeLength](checked-change-length.md) | `fun checkedChangeLength(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeLyric](checked-change-lyric.md) | `fun checkedChangeLyric(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeVoice](checked-change-voice.md) | `fun checkedChangeVoice(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [submit](submit.md) | `fun submit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [ADD_MODE](-a-d-d_-m-o-d-e.md) | `const val ADD_MODE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [CHANGE_MODE](-c-h-a-n-g-e_-m-o-d-e.md) | `const val CHANGE_MODE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
