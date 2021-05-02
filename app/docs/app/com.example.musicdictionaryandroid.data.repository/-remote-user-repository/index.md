[app](../../index.md) / [com.example.musicdictionaryandroid.data.repository](../index.md) / [RemoteUserRepository](./index.md)

# RemoteUserRepository

`interface RemoteUserRepository`

リモート保持ののユーザ情報に関するアクセスRepository

### Functions

| Name | Summary |
|---|---|
| [createUser](create-user.md) | `abstract suspend fun createUser(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>ユーザー登録 |
| [delete](delete.md) | `abstract fun delete(): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>アカウント削除 |
| [firstCheck](first-check.md) | `abstract fun firstCheck(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>ログイン状態チェック |
| [getUserByEmail](get-user-by-email.md) | `abstract suspend fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)`>`<br>登録したユーザーの情報取得 |
| [signIn](sign-in.md) | `abstract fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ログイン |
| [signOut](sign-out.md) | `abstract fun signOut(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |
| [signUp](sign-up.md) | `abstract fun signUp(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>アカウント作成 |

### Inheritors

| Name | Summary |
|---|---|
| [RemoteUserRepositoryImp](../-remote-user-repository-imp/index.md) | `class RemoteUserRepositoryImp : `[`RemoteUserRepository`](./index.md) |
