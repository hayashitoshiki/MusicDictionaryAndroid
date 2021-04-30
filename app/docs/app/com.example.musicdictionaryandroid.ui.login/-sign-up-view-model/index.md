[app](../../index.md) / [com.example.musicdictionaryandroid.ui.login](../index.md) / [SignUpViewModel](./index.md)

# SignUpViewModel

`class SignUpViewModel : ViewModel`

新規登録画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SignUpViewModel(userInfoChangeListUtil: `[`UserInfoChangeListUtil`](../../com.example.musicdictionaryandroid.ui.util/-user-info-change-list-util/index.md)`, userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-user-use-case/index.md)`, externalScope: CoroutineScope)`<br>新規登録画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [areaSelectedPosition](area-selected-position.md) | `val areaSelectedPosition: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [birthdaySelectedPosition](birthday-selected-position.md) | `val birthdaySelectedPosition: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [emailErrorText](email-error-text.md) | `val emailErrorText: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| [emailText](email-text.md) | `val emailText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [genderInt](gender-int.md) | `val genderInt: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [nameErrorText](name-error-text.md) | `val nameErrorText: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| [nameText](name-text.md) | `val nameText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [password1Text](password1-text.md) | `val password1Text: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [password2Text](password2-text.md) | `val password2Text: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [passwordError1Text](password-error1-text.md) | `val passwordError1Text: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| [passwordError2Text](password-error2-text.md) | `val passwordError2Text: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>>` |

### Functions

| Name | Summary |
|---|---|
| [checkedChangeGender](checked-change-gender.md) | `fun checkedChangeGender(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [focusChangeEmail](focus-change-email.md) | `fun focusChangeEmail(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [focusChangeName](focus-change-name.md) | `fun focusChangeName(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [focusChangePassword1](focus-change-password1.md) | `fun focusChangePassword1(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [focusChangePassword2](focus-change-password2.md) | `fun focusChangePassword2(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [signUp](sign-up.md) | `fun signUp(): Job` |
