[app](../../index.md) / [com.example.musicdictionaryandroid.ui.mypage](../index.md) / [MyPageArtistAddViewModel](./index.md)

# MyPageArtistAddViewModel

`class MyPageArtistAddViewModel : ViewModel`

アーティスト情報登録・追加画面_UIロジック

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MyPageArtistAddViewModel(userUseCase: `[`UserUseCase`](../../com.example.musicdictionaryandroid.model.usecase/-user-use-case/index.md)`, artistUseCase: `[`ArtistUseCase`](../../com.example.musicdictionaryandroid.model.usecase/-artist-use-case/index.md)`)`<br>アーティスト情報登録・追加画面_UIロジック |

### Properties

| Name | Summary |
|---|---|
| [artistForm](artist-form.md) | `var artistForm: MutableLiveData<`[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`>` |
| [searchText](search-text.md) | `val searchText: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [status](status.md) | `val status: MutableLiveData<`[`Status`](../../com.example.musicdictionaryandroid.model.util/-status/index.md)`<`[`CallBackData`](../../com.example.musicdictionaryandroid.model.entity/-call-back-data/index.md)`?>>` |

### Functions

| Name | Summary |
|---|---|
| [changeArtistName](change-artist-name.md) | `fun changeArtistName(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeGender](checked-change-gender.md) | `fun checkedChangeGender(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeLength](checked-change-length.md) | `fun checkedChangeLength(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeLyric](checked-change-lyric.md) | `fun checkedChangeLyric(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [checkedChangeVoice](checked-change-voice.md) | `fun checkedChangeVoice(checkedId: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [init](init.md) | `fun init(artist: `[`ArtistsForm`](../../com.example.musicdictionaryandroid.model.entity/-artists-form/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [submit](submit.md) | `fun submit(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>送信処理 |
