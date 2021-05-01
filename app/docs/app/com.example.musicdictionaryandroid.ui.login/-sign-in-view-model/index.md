[app](../../index.md) / [com.example.musicdictionaryandroid.ui.login](../index.md) / [SignInViewModel](./index.md)

# SignInViewModel

`class SignInViewModel : ViewModel`

ログイン画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SignInViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-user-use-case/index.md)`, externalScope: CoroutineScope)`<br>ログイン画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [emailErrorText](email-error-text.md) | `val emailErrorText: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| [emailText](email-text.md) | `val emailText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [isProgressBar](is-progress-bar.md) | `val isProgressBar: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [passwordErrorText](password-error-text.md) | `val passwordErrorText: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| [passwordText](password-text.md) | `val passwordText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>` |

### Functions

| Name | Summary |
|---|---|
| [focusChangeEmail](focus-change-email.md) | `fun focusChangeEmail(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [focusChangePassword](focus-change-password.md) | `fun focusChangePassword(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [signIn](sign-in.md) | `fun signIn(): Job` |
