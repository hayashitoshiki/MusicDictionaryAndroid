[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [UserUseCase](./index.md)

# UserUseCase

`interface UserUseCase`

ユーザーに関するビジネスロジック

### Functions

| Name | Summary |
|---|---|
| [changeUser](change-user.md) | `abstract suspend fun changeUser(user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?>`<br>ユーザー情報変更 |
| [createUser](create-user.md) | `abstract suspend fun createUser(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, onSuccess: (result: `[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザー登録 |
| [delete](delete.md) | `abstract fun delete(onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザー削除 |
| [firstCheck](first-check.md) | `abstract fun firstCheck(onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログイン状態チェック |
| [getEmail](get-email.md) | `abstract fun getEmail(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ユーザーのEmail取得 |
| [getUserByCache](get-user-by-cache.md) | `abstract fun getUserByCache(): `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)<br>キャッシュから登録したユーザーの情報取得 |
| [getUserByEmail](get-user-by-email.md) | `abstract suspend fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`?>`<br>登録したユーザーの情報取得 |
| [signIn](sign-in.md) | `abstract suspend fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログイン |
| [signOut](sign-out.md) | `abstract suspend fun signOut(onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |

### Inheritors

| Name | Summary |
|---|---|
| [UserUseCaseImp](../-user-use-case-imp/index.md) | `class UserUseCaseImp : `[`UserUseCase`](./index.md) |
