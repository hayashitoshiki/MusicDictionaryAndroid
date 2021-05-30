package com.example.domain.repository

import com.example.domain.model.entity.User
import com.example.domain.model.value.Result
import kotlinx.coroutines.flow.Flow

/**
 * リモート保持ののユーザ情報に関するアクセスRepository
 */
interface RemoteUserRepository {

    /**
     * 登録したユーザーの情報取得
     *
     * @param email ユーザーのEmail
     * @return 登録したユーザー情報取得
     */
    suspend fun getUserByEmail(email: String): Result<User>

    /**
     * ユーザー登録
     *
     * @param user 登録数ユーザー情報
     * @return 登録処理結果
     */
    suspend fun createUser(user: User): Result<String>

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
