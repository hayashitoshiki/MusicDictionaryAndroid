[app](../../index.md) / [com.example.musicdictionaryandroid.ui.util](../index.md) / [MessageUtil](./index.md)

# MessageUtil

`interface MessageUtil`

### Functions

| Name | Summary |
|---|---|
| [changeBirthdayString](change-birthday-string.md) | `abstract fun changeBirthdayString(code: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>生年月日からindexへ変更 |
| [getAddButton](get-add-button.md) | `abstract fun getAddButton(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>追加ボタン文言取得 |
| [getArea](get-area.md) | `abstract fun getArea(code: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>地域名取得 |
| [getArtistAddTitle](get-artist-add-title.md) | `abstract fun getArtistAddTitle(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>アーティスト追加画面タイトル取得 |
| [getArtistChangeTitle](get-artist-change-title.md) | `abstract fun getArtistChangeTitle(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>アーティスト変更画面タイトル取得 |
| [getBirthday](get-birthday.md) | `abstract fun getBirthday(code: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>生年月日取得 |
| [getChangeButton](get-change-button.md) | `abstract fun getChangeButton(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>変更ボタン文言取得 |
| [getGender](get-gender.md) | `abstract fun getGender(code: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>性別名取得 |
| [getGenre1](get-genre1.md) | `abstract fun getGenre1(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>メインカテゴリ名取得 |
| [getGenre2](get-genre2.md) | `abstract fun getGenre2(genre1Index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, genre2Index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>サブカテゴリ名取得 |
| [getLength](get-length.md) | `abstract fun getLength(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>曲の長さ（文字列）取得 |
| [getLyrics](get-lyrics.md) | `abstract fun getLyrics(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>歌詞の日本語比率（文字列）取得 |
| [getMainCategory](get-main-category.md) | `abstract fun getMainCategory(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>メインカテゴリ取得 |
| [getSubCategory](get-sub-category.md) | `abstract fun getSubCategory(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>サブカテゴリ取得 |
| [getVoice](get-voice.md) | `abstract fun getVoice(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>声の高さ（文字列）取得 |

### Inheritors

| Name | Summary |
|---|---|
| [MessageUtilImp](../-message-util-imp/index.md) | `object MessageUtilImp : `[`MessageUtil`](./index.md)<br>各数値項目の変換 |
