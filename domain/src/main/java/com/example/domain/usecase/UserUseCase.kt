package com.example.domain.usecase

import com.example.domain.model.value.Result
import com.example.domain.model.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * ユーザーに関するビジネスロジック
 *
 */
interface UserUseCase {

    /**
     * キャッシュから登録したユーザーの情報取得
     *
     * @return 登録したユーザー情報取得
     */
    fun getUserByCache(): User

    /**
     * ユーザー登録
     *
     * @param user 登録数ユーザー情報
     * @return 登録処理結果
     */
    suspend fun createUser(email: String, password: String, user: User): Flow<Result<String>>

    /**
     * ログイン状態チェック
     *
     * @return ログイン状態
     */
    fun firstCheck(): Boolean

    /**
     * ログイン
     *
     * @param email ユーザーのEmail
     * @param password ユーザーのPassword
     * @return ログイン処理結果
     */
    suspend fun signIn(email: String, password: String): Flow<Result<String>>

    /**
     * ログアウト
     *
     */
    suspend fun signOut()

    /**
     * ユーザー削除
     *
     * @return　ユーザ削除処理結果
     */
    fun delete(): Flow<Result<String>>
}
