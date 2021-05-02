[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [UserUseCaseImp](./index.md)

# UserUseCaseImp

`class UserUseCaseImp : `[`UserUseCase`](../-user-use-case/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `UserUseCaseImp(remoteUserRepository: `[`RemoteUserRepository`](../../com.example.musicdictionaryandroid.data.repository/-remote-user-repository/index.md)`, localUserRepository: `[`LocalUserRepository`](../../com.example.musicdictionaryandroid.data.repository/-local-user-repository/index.md)`, localArtistRepository: `[`LocalArtistRepository`](../../com.example.musicdictionaryandroid.data.repository/-local-artist-repository/index.md)`, externalScope: CoroutineScope, defaultDispatcher: CoroutineDispatcher = Dispatchers.Default)` |

### Functions

| Name | Summary |
|---|---|
| [createUser](create-user.md) | `suspend fun createUser(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ユーザー登録 |
| [delete](delete.md) | `fun delete(): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ユーザー削除 |
| [firstCheck](first-check.md) | `fun firstCheck(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>ログイン状態チェック |
| [getUserByCache](get-user-by-cache.md) | `fun getUserByCache(): `[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)<br>キャッシュから登録したユーザーの情報取得 |
| [signIn](sign-in.md) | `suspend fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Flow<`[`Result`](../../com.example.musicdictionaryandroid.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`<br>ログイン |
| [signOut](sign-out.md) | `suspend fun signOut(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ログアウト |
