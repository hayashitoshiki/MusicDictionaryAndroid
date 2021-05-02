[app](../../index.md) / [com.example.musicdictionaryandroid.data.repository](../index.md) / [LocalUserRepository](./index.md)

# LocalUserRepository

`interface LocalUserRepository`

ローカル保持のユーザ情報に関するアクセスRepository

### Functions

| Name | Summary |
|---|---|
| [getArea](get-area.md) | `abstract fun getArea(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>地域取得 |
| [getBirthday](get-birthday.md) | `abstract fun getBirthday(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>生年月日取得 |
| [getEmail](get-email.md) | `abstract fun getEmail(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>メールアドレス取得 |
| [getFavorite](get-favorite.md) | `abstract fun getFavorite(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>登録済みアーティスト件数取得 |
| [getGender](get-gender.md) | `abstract fun getGender(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>性別取得 |
| [getName](get-name.md) | `abstract fun getName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ユーザ名取得 |
| [getUser](get-user.md) | `abstract fun getUser(): `[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)<br>ユーザ情報取得 |
| [removeAll](remove-all.md) | `abstract fun removeAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ情報全削除 |
| [setFavorite](set-favorite.md) | `abstract fun setFavorite(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>登録済みアーティスト件数設定 |
| [setUser](set-user.md) | `abstract fun setUser(user: `[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ情報設定 |

### Inheritors

| Name | Summary |
|---|---|
| [LocalUserRepositoryImp](../-local-user-repository-imp/index.md) | `class LocalUserRepositoryImp : `[`LocalUserRepository`](./index.md) |
