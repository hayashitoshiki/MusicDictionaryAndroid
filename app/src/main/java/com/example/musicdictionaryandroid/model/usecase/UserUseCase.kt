package com.example.musicdictionaryandroid.model.usecase

import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.util.Result

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
     * 登録したユーザーの情報取得
     *
     * @param email ユーザーのEmail
     * @return 登録したユーザー情報取得
     */
    suspend fun getUserByEmail(email: String): Result<User?>
    /**
     * ユーザー登録
     *
     * @param user 登録数ユーザー情報
     * @return 登録処理結果
     */
    suspend fun createUser(email: String, password: String, user: User, onSuccess: (result: CallBackData?) -> Unit, onError: (error: Throwable) -> Unit)
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
    fun firstCheck(): Boolean
    /**
     * ログイン
     *
     * @param email ユーザーのEmail
     * @param password ユーザーのPassword
     * @param onSuccess 成功
     * @param onError 失敗
     */
    suspend fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Throwable?) -> Unit)
    /**
     * ログアウト
     *
     */
    suspend fun signOut()
    /**
     * ユーザー削除
     *
     * @param onSuccess 成功
     * @param onError 失敗
     */
    fun delete(onSuccess: () -> Unit, onError: (error: Throwable?) -> Unit)
    /**
     * ユーザーのEmail取得
     *
     * @return ユーザーのEmail
     */
    fun getEmail(): String
}
