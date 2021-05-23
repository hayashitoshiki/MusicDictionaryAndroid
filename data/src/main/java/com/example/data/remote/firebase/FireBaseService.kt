package com.example.data.remote.firebase

import com.example.domain.model.value.Result
import kotlinx.coroutines.flow.Flow

interface FireBaseService {

    /**
     * ログイン状態チェック
     *
     */
    fun firstCheck(): Boolean

    /**
     * ログイン
     *
     * @param email ユーザーのEmail
     * @param password ユーザーのPassword
     * @return ログインの結果
     */
    fun signIn(email: String, password: String): Flow<Result<String>>

    /**
     * ログアウト
     *
     */
    fun signOut()

    /**
     * アカウント作成
     *
     * @param email ユーザーのEmail
     * @param password ユーザーのPassword
     * @return アカウント作成の結果
     */
    fun signUp(email: String, password: String): Flow<Result<String>>

    /**
     * アカウント削除
     *
     * @return アカウント削除の結果
     */
    fun delete(): Flow<Result<String>>
}
