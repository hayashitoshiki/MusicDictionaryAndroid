package com.example.musicdictionaryandroid.model.repository

import java.lang.Exception

/**
 * FireBase呼び出し関連のRepository
 */
interface FireBaseRepository {

    /**
     * ログイン状態チェック
     *
     * @param onSuccess　成功
     * @param onError　失敗
     */
    fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit)
    /**
     * ログイン
     *
     * @param email ユーザーのEmail
     * @param password ユーザーのPassword
     * @param onSuccess 成功
     * @param onError 失敗
     */
    fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    /**
     * ログアウト
     *
     * @param onSuccess 成功
     * @param onError 失敗
     */
    fun signOut(onSuccess: () -> Unit, onError: () -> Unit)
    /**
     * アカウント作成
     *
     * @param email ユーザーのEmail
     * @param password ユーザーのPassword
     * @param onSuccess 成功
     * @param onError 失敗
     */
    fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    /**
     * アカウント削除
     *
     * @param onSuccess 成功
     * @param onError 失敗
     */
    fun delete(onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    /**
     * ユーザーのEmail取得
     *
     * @return ユーザーのEmail
     */
    fun getEmail(): String
}
