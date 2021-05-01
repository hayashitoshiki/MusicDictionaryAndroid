[app](../index.md) / [com.example.musicdictionaryandroid.ui.util](index.md) / [setSafeCheckListener](./set-safe-check-listener.md)

# setSafeCheckListener

`fun <T : `[`CompoundButton`](https://developer.android.com/reference/android/widget/CompoundButton.html)`> `[`T`](set-safe-check-listener.md#T)`.setSafeCheckListener(listener: (it: `[`T`](set-safe-check-listener.md#T)`, isChecked: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Extension function for [CompoundButton.setOnCheckedChangeListener](https://developer.android.com/reference/android/widget/CompoundButton.html#setOnCheckedChangeListener(android.widget.CompoundButton.OnCheckedChangeListener)).
It prevents fast clicking by user.

