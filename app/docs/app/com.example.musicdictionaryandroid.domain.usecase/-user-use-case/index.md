[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [UserUseCase](./index.md)

# UserUseCase

`interface UserUseCase`

ユーザーに関するビジネスロジック

### Functions

| Name | Summary |
|---|---|
| [createUser](create-user.md) | `abstract suspend fun createUser(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../com.example.domain.model.entity/-user/index.md)`): Flow<`[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ユーザー登録 |
| [delete](delete.md) | `abstract fun delete(): Flow<`[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ユーザー削除 |
| [firstCheck](first-check.md) | `abstract fun firstCheck(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>ログイン状態チェック |
| [getUserByCache](get-user-by-cache.md) | `abstract fun getUserByCache(): `[`User`](../../com.example.domain.model.entity/-user/index.md)<br>キャッシュから登録したユーザーの情報取得 |
| [signIn](sign-in.md) | `abstract suspend fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ログイン |
| [signOut](sign-out.md) | `abstract suspend fun signOut(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |

### Inheritors

| Name | Summary |
|---|---|
| [UserUseCaseImp](../-user-use-case-imp/index.md) | `class UserUseCaseImp : `[`UserUseCase`](./index.md) |
