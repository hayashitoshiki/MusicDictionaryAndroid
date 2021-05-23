[app](../../index.md) / [com.example.musicdictionaryandroid.ui.mypage](../index.md) / [SettingBaseAdapter](./index.md)

# SettingBaseAdapter

`class SettingBaseAdapter : `[`BaseAdapter`](https://developer.android.com/reference/android/widget/BaseAdapter.html)

アーティスト一覧画面(アーティストリスト)用Adapter

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SettingBaseAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`?, items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>)`<br>アーティスト一覧画面(アーティストリスト)用Adapter |

### Functions

| Name | Summary |
|---|---|
| [getCount](get-count.md) | `fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getItem](get-item.md) | `fun getItem(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [getItemId](get-item-id.md) | `fun getItemId(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getView](get-view.md) | `fun getView(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, convertView: `[`View`](https://developer.android.com/reference/android/view/View.html)`?, parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`View`](https://developer.android.com/reference/android/view/View.html) |
| [setData](set-data.md) | `fun setData(items: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Artist`](../../com.example.domain.model.entity/-artist/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
