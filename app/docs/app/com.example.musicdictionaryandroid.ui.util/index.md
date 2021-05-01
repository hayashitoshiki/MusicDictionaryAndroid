[app](../index.md) / [com.example.musicdictionaryandroid.ui.util](./index.md)

## Package com.example.musicdictionaryandroid.ui.util

Presentation層共通処理関連

### Types

| Name | Summary |
|---|---|
| [Status](-status/index.md) | `sealed class Status<out T>`<br>UIのステータス管理 |
| [UserInfoChangeListUtil](-user-info-change-list-util/index.md) | `interface UserInfoChangeListUtil` |
| [UserInfoChangeListUtilImp](-user-info-change-list-util-imp/index.md) | `object UserInfoChangeListUtilImp : `[`UserInfoChangeListUtil`](-user-info-change-list-util/index.md)<br>各数値項目の変換 |

### Functions

| Name | Summary |
|---|---|
| [setSafeCheckListener](set-safe-check-listener.md) | `fun <T : `[`CompoundButton`](https://developer.android.com/reference/android/widget/CompoundButton.html)`> `[`T`](set-safe-check-listener.md#T)`.setSafeCheckListener(listener: (it: `[`T`](set-safe-check-listener.md#T)`, isChecked: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Extension function for [CompoundButton.setOnCheckedChangeListener](https://developer.android.com/reference/android/widget/CompoundButton.html#setOnCheckedChangeListener(android.widget.CompoundButton.OnCheckedChangeListener)). It prevents fast clicking by user. |
| [setSafeClickListener](set-safe-click-listener.md) | `fun <T : `[`View`](https://developer.android.com/reference/android/view/View.html)`> `[`T`](set-safe-click-listener.md#T)`.setSafeClickListener(listener: (it: `[`T`](set-safe-click-listener.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Extension function for [View.setOnClickListener](https://developer.android.com/reference/android/view/View.html#setOnClickListener(android.view.View.OnClickListener)). It prevents fast clicking by user. |
