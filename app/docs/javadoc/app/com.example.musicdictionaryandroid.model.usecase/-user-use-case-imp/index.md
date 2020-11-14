[app](../../index.md) / [com.example.musicdictionaryandroid.model.usecase](../index.md) / [UserUseCaseImp](./index.md)

# UserUseCaseImp

`class UserUseCaseImp : `[`UserUseCase`](../-user-use-case/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UserUseCaseImp(apiRepository: `[`ApiServerRepository`](../../com.example.musicdictionaryandroid.model.repository/-api-server-repository/index.md)`, fireBaseRepository: `[`FireBaseRepository`](../../com.example.musicdictionaryandroid.model.repository/-fire-base-repository/index.md)`, dataBaseRepository: `[`DataBaseRepository`](../../com.example.musicdictionaryandroid.model.repository/-data-base-repository/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [changeUser](change-user.md) | `suspend fun changeUser(user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?>`<br>ユーザー情報変更 |
| [createUser](create-user.md) | `suspend fun createUser(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, onSuccess: (result: `[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザー登録 |
| [delete](delete.md) | `fun delete(onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザー削除 |
| [firstCheck](first-check.md) | `fun firstCheck(onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログイン状態チェック |
| [getEmail](get-email.md) | `fun getEmail(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ユーザーのEmail取得 |
| [getUserByEmail](get-user-by-email.md) | `suspend fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.model.util/-result/index.md)`<`[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`?>`<br>登録したユーザーの情報取得 |
| [signIn](sign-in.md) | `suspend fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログイン |
| [signOut](sign-out.md) | `suspend fun signOut(onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |
