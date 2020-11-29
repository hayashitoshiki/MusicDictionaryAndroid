[app](../../index.md) / [com.example.musicdictionaryandroid.ui.adapter](../index.md) / [ResultAdapter](./index.md)

# ResultAdapter

`class ResultAdapter : Adapter<`[`ViewHolder`](-view-holder/index.md)`>`

アーティスト検索結果画面のリサイクルビュー

### Types

| Name | Summary |
|---|---|
| [ViewHolder](-view-holder/index.md) | `class ViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ResultAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, artistList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>)`<br>アーティスト検索結果画面のリサイクルビュー |

### Properties

| Name | Summary |
|---|---|
| [holdButton](hold-button.md) | `var holdButton: `[`ImageButton`](https://developer.android.com/reference/android/widget/ImageButton.html)`?` |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`ViewHolder`](-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ViewHolder`](-view-holder/index.md) |
| [setOnItemClickListener](set-on-item-click-listener.md) | `fun setOnItemClickListener(listener: `[`OnClickListener`](https://developer.android.com/reference/android/view/View/OnClickListener.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
