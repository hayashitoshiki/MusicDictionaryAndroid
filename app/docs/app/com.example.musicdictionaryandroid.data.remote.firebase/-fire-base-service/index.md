[app](../../index.md) / [com.example.musicdictionaryandroid.data.remote.firebase](../index.md) / [FireBaseService](./index.md)

# FireBaseService

`interface FireBaseService`

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>アカウント削除 |
| [firstCheck](first-check.md) | `abstract fun firstCheck(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>ログイン状態チェック |
| [signIn](sign-in.md) | `abstract fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ログイン |
| [signOut](sign-out.md) | `abstract fun signOut(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |
| [signUp](sign-up.md) | `abstract fun signUp(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>アカウント作成 |

### Inheritors

| Name | Summary |
|---|---|
| [FireBaseServiceImp](../-fire-base-service-imp/index.md) | `class FireBaseServiceImp : `[`FireBaseService`](./index.md) |
