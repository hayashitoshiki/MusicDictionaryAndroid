[app](../../index.md) / [com.example.musicdictionaryandroid.data.repository](../index.md) / [RemoteUserRepositoryImp](./index.md)

# RemoteUserRepositoryImp

`class RemoteUserRepositoryImp : `[`RemoteUserRepository`](../-remote-user-repository/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RemoteUserRepositoryImp(provider: `[`Provider`](../../com.example.musicdictionaryandroid.data.remote.network/-provider/index.md)` = ProviderImp, firebaseService: `[`FireBaseService`](../../com.example.musicdictionaryandroid.data.remote.firebase/-fire-base-service/index.md)` = FireBaseServiceImp(), ioDispatcher: CoroutineDispatcher = Dispatchers.IO)` |

### Functions

| Name | Summary |
|---|---|
| [createUser](create-user.md) | `suspend fun createUser(user: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>ユーザー登録 |
| [delete](delete.md) | `fun delete(): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>アカウント削除 |
| [firstCheck](first-check.md) | `fun firstCheck(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>ログイン状態チェック |
| [getUserByEmail](get-user-by-email.md) | `suspend fun getUserByEmail(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)`>`<br>登録したユーザーの情報取得 |
| [signIn](sign-in.md) | `fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ログイン |
| [signOut](sign-out.md) | `fun signOut(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |
| [signUp](sign-up.md) | `fun signUp(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>アカウント作成 |
