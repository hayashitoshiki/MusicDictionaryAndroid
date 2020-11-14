[app](../../index.md) / [com.example.musicdictionaryandroid.model.entity](../index.md) / [ArtistsForm](./index.md)

# ArtistsForm

`data class ArtistsForm : `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)

API連絡用アーティスト

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ArtistsForm(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", gender: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, voice: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, lyrics: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, genre1: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", genre2: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "", lyricsGenre: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = "")`<br>API連絡用アーティスト |

### Properties

| Name | Summary |
|---|---|
| [gender](gender.md) | `var gender: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>性別 |
| [genre1](genre1.md) | `var genre1: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>ジャンル１ |
| [genre2](genre2.md) | `var genre2: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>ジャンル２ |
| [length](length.md) | `var length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>曲の長さ |
| [lyrics](lyrics.md) | `var lyrics: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>歌詞の言語比率 |
| [lyricsGenre](lyrics-genre.md) | `var lyricsGenre: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [name](name.md) | `var name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>アーティスト名 |
| [voice](voice.md) | `var voice: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>声の高さ |

### Functions

| Name | Summary |
|---|---|
| [getMapList](get-map-list.md) | `fun getMapList(): `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
