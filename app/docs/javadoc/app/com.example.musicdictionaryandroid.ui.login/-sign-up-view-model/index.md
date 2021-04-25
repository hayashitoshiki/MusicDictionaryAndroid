[app](../../index.md) / [com.example.musicdictionaryandroid.ui.login](../index.md) / [SignUpViewModel](./index.md)

# SignUpViewModel

`class SignUpViewModel : ViewModel`

新規登録画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SignUpViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-user-use-case/index.md)`)`<br>新規登録画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [areaSelectedPosition](area-selected-position.md) | `val areaSelectedPosition: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [birthdaySelectedPosition](birthday-selected-position.md) | `val birthdaySelectedPosition: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [emailText](email-text.md) | `val emailText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [genderInt](gender-int.md) | `val genderInt: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [nameText](name-text.md) | `val nameText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [passwordText](password-text.md) | `val passwordText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>>` |

### Functions

| Name | Summary |
|---|---|
| [checkedChangeGender](checked-change-gender.md) | `fun checkedChangeGender(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [signUp](sign-up.md) | `fun signUp(): Job`<br>新規作成 |
