[app](../../index.md) / [com.example.musicdictionaryandroid.data.local.preferences](../index.md) / [UserSharedPreferences](./index.md)

# UserSharedPreferences

`interface UserSharedPreferences`

Preferenceへのユーザ情報格納

### Functions

| Name | Summary |
|---|---|
| [getArea](get-area.md) | `abstract fun getArea(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>地域取得 |
| [getBirthday](get-birthday.md) | `abstract fun getBirthday(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>生年月日取得 |
| [getEmail](get-email.md) | `abstract fun getEmail(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>メールアドレス取得 |
| [getFavorite](get-favorite.md) | `abstract fun getFavorite(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>登録済みアーティスト件数取得 |
| [getGender](get-gender.md) | `abstract fun getGender(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>性別取得 |
| [getName](get-name.md) | `abstract fun getName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ユーザ名取得 |
| [getUser](get-user.md) | `abstract fun getUser(): `[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)<br>ユーザ情報取得 |
| [removeAll](remove-all.md) | `abstract fun removeAll(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ情報全削除 |
| [setArea](set-area.md) | `abstract fun setArea(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>地域設定 |
| [setBirthday](set-birthday.md) | `abstract fun setBirthday(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>生年月日取得 |
| [setEmail](set-email.md) | `abstract fun setEmail(value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>メールアドレス設定 |
| [setFavorite](set-favorite.md) | `abstract fun setFavorite(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>登録済みアーティスト件数設定 |
| [setGender](set-gender.md) | `abstract fun setGender(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>性別設定 |
| [setName](set-name.md) | `abstract fun setName(value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ名設定 |
| [setUser](set-user.md) | `abstract fun setUser(user: `[`User`](../../com.example.musicdictionaryandroid.domain.model.entity/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>ユーザ情報設定 |

### Inheritors

| Name | Summary |
|---|---|
| [UserSharedPreferencesImp](../-user-shared-preferences-imp/index.md) | `class UserSharedPreferencesImp : `[`UserSharedPreferences`](./index.md) |
