[app](../../index.md) / [com.example.data.repository](../index.md) / [LocalUserRepositoryImp](./index.md)

# LocalUserRepositoryImp

`class LocalUserRepositoryImp : `[`LocalUserRepository`](../-local-user-repository/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocalUserRepositoryImp(userSharedPreferences: `[`UserSharedPreferences`](../../com.example.musicdictionaryandroid.data.local.preferences/-user-shared-preferences/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [getArea](get-area.md) | `fun getArea(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>地域取得 |
| [getBirthday](get-birthday.md) | `fun getBirthday(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>生年月日取得 |
| [getEmail](get-email.md) | `fun getEmail(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>メールアドレス取得 |
| [getFavorite](get-favorite.md) | `fun getFavorite(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>登録済みアーティスト件数取得 |
| [getGender](get-gender.md) | `fun getGender(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>性別取得 |
| [getName](get-name.md) | `fun getName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ユーザ名取得 |
| [getUser](get-user.md) | `fun getUser(): `[`User`](../../com.example.domain.model.entity/-user/index.md)<br>ユーザ情報取得 |
| [removeAll](remove-all.md) | `fun removeAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ情報全削除 |
| [setFavorite](set-favorite.md) | `fun setFavorite(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>登録済みアーティスト件数設定 |
| [setUser](set-user.md) | `fun setUser(user: `[`User`](../../com.example.domain.model.entity/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ情報設定 |
