[app](../../index.md) / [com.example.musicdictionaryandroid.domain.usecase](../index.md) / [UserUseCaseImp](index.md) / [createUser](./create-user.md)

# createUser

`suspend fun createUser(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, user: `[`User`](../../com.example.musicdictionaryandroid.model.entity/-user/index.md)`, onSuccess: (result: `[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Overrides [UserUseCase.createUser](../-user-use-case/create-user.md)

ユーザー登録

### Parameters

`user` - 登録数ユーザー情報

**Return**
登録処理結果

