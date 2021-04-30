[app](../../index.md) / [com.example.musicdictionaryandroid.ui.login](../index.md) / [StartViewModel](./index.md)

# StartViewModel

`class StartViewModel : ViewModel`

ログイン・新規登録画面 BaseActivity_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StartViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.domain.usecase/-user-use-case/index.md)`)`<br>ログイン・新規登録画面 BaseActivity_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [isEnableRadioButton](is-enable-radio-button.md) | `val isEnableRadioButton: LiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [status](status.md) | `val status: LiveData<`[`Status`](../../com.example.musicdictionaryandroid.ui.util/-status/index.md)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>>` |

### Functions

| Name | Summary |
|---|---|
| [enableRadioButton](enable-radio-button.md) | `fun enableRadioButton(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [firstCheck](first-check.md) | `fun firstCheck(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unEnableRadioButton](un-enable-radio-button.md) | `fun unEnableRadioButton(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
