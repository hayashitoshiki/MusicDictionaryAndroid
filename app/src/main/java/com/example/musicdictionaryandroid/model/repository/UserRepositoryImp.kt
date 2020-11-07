package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.net.Provider
import retrofit2.Response

class UserRepositoryImp : UserRepository {

    // ユーザー取得
    override fun getUserByEmail(email: String): Response<User> {
        return Provider.api().getUserByEmail(email).execute()
    }
    // ユーザー登録
    override fun createUser(user: String): Response<CallBackData> {
        return Provider.api().createUser(user).execute()
    }

    // ユーザー情報変更
    override fun changeUser(user: User, email: String): Response<CallBackData> {
        return Provider.api().changeUser(user, email).execute()
    }
}
