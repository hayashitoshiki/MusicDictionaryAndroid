[app](../../index.md) / [com.example.musicdictionaryandroid.model.usecase](../index.md) / [UserUseCase](index.md) / [signIn](./sign-in.md)

# signIn

`abstract suspend fun signIn(email: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, onSuccess: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`, onError: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

ログイン

### Parameters

`email` - ユーザーのEmail

`password` - ユーザーのPassword

`onSuccess` - 成功

`onError` - 失敗