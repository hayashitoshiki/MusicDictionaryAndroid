package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import retrofit2.Response

/**
 * APIからユーザー情報取得関連のRepository
 */
interface UserRepository {

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
    fun createUser(user: String): Response<CallBackData>
    /**
     * ユーザー情報変更
     *
     * @param user 更新するユーザー情報
     * @param email 更新したユーザのEmail
     * @return 変更処理結果
     */
    fun changeUser(user: User, email: String): Response<CallBackData>
}
