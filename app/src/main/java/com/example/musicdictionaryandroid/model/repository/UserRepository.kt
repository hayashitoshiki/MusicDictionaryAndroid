package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.User
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import retrofit2.Response

interface UserRepository {

    // ユーザーの登録したアーティスト取得
    fun getUserByEmail(email: String): Response<User>

    // ユーザー登録
    fun createUser(user: String): Response<CallBackData>

    // ユーザー情報変更
    fun changeUser(user: User, email: String): Response<CallBackData>
}