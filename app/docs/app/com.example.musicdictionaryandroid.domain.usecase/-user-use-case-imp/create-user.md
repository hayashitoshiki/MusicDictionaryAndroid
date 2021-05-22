[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [UserUseCaseImp](index.md) / [createUser](./create-user.md)

# createUser

`suspend fun createUser(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../com.example.domain.model.entity/-user/index.md)`): Flow<`[`Result`](../../com.example.domain.model.value/-result/index.md)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>`

Overrides [UserUseCase.createUser](../-user-use-case/create-user.md)

ユーザー登録

### Parameters

`user` - 登録数ユーザー情報

**Return**
登録処理結果

