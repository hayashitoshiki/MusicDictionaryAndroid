[app](../../index.md) / [com.example.musicdictionaryandroid.model.util](../index.md) / [Result](./index.md)

# Result

`sealed class Result<out R>`

ビジネスロジックの非同期処理ののResultクラス

### Parameters

`R` -

### Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | `data class Error : `[`Result`](./index.md)`<`[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)`>` |
| [Success](-success/index.md) | `data class Success<out T> : `[`Result`](./index.md)`<`[`T`](-success/index.md#T)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [Error](-error/index.md) | `data class Error : `[`Result`](./index.md)`<`[`Nothing`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)`>` |
| [Success](-success/index.md) | `data class Success<out T> : `[`Result`](./index.md)`<`[`T`](-success/index.md#T)`>` |
