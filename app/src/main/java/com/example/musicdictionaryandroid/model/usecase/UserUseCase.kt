package com.example.musicdictionaryandroid.model.usecase

import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.util.Result
import java.lang.Exception
import retrofit2.Response

/**
 * ユーザーに関するビジネスロジック
 *
 */
interface UserUseCase {

    /**
     * 登録したユーザーの情報取得
     *
     * @param email ユーザーのEmail
     * @return 登録したユーザー情報取得
     */
    fun getUserByEmail(email: String): Response<User>
    /**
     * ユーザー登録
     *
     * @param user 登録数ユーザー情報
     * @return 登録処理結果
     */
    suspend fun createUser(email: String, password: String, user: User, onSuccess: (result: CallBackData?) -> Unit, onError: (error: Exception?) -> Unit)
    /**
     * ユーザー情報変更
     *
     * @param user 更新するユーザー情報
     * @param email 更新したユーザのEmail
     * @return 変更処理結果
     */
    suspend fun changeUser(user: User, email: String): Result<CallBackData?>

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
    suspend fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit)
    /**
     * ログアウト
     *
     * @param onSuccess 成功
     * @param onError 失敗
     */
    suspend fun signOut(onSuccess: () -> Unit, onError: () -> Unit)
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
