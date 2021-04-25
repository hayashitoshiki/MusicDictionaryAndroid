package com.example.musicdictionaryandroid.data.util

/**
 * ビジネスロジックの非同期処理ののResultクラス
 *
 * @param R
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}
