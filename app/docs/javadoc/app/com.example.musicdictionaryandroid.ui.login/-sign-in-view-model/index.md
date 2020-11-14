[app](../../index.md) / [com.example.musicdictionaryandroid.ui.login](../index.md) / [SignInViewModel](./index.md)

# SignInViewModel

`class SignInViewModel : ViewModel`

ログイン画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SignInViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.model.usecase/-user-use-case/index.md)`)`<br>ログイン画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [emailText](email-text.md) | `val emailText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [isEnableSubmitButton](is-enable-submit-button.md) | `val isEnableSubmitButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [passwordText](password-text.md) | `val passwordText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>>` |

### Functions

| Name | Summary |
|---|---|
| [signIn](sign-in.md) | `fun signIn(): Job`<br>ログイン処理 |
