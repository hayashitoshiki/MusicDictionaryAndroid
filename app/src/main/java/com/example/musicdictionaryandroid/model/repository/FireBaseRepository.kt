package com.example.musicdictionaryandroid.model.repository

import java.lang.Exception

interface FireBaseRepository {

    // ログイン状態チェック
    fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit)
    // ログイン
    fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    // ログアウト
    fun signOut(onSuccess: () -> Unit, onError: () -> Unit)
    // アカウント作成
    fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    // アカウント削除
    fun delete(onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    // ユーザーのEmailを取得
    fun getEmail(): String
}
