package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.local.preferences.UserSharedPreferences
import com.example.musicdictionaryandroid.domain.model.entity.User

class LocalUserRepositoryImp(private val userSharedPreferences: UserSharedPreferences) : LocalUserRepository {

    // ユーザ情報設定
    override fun setUser(user: User) {
        userSharedPreferences.setUser(user)
    }

    // ユーザ情報取得
    override fun getUser(): User = userSharedPreferences.getUser()

    // Email取得
    override fun getEmail(): String = userSharedPreferences.getEmail()

    // ユーザ名取得
    override fun getName(): String = userSharedPreferences.getName()

    // 性別取得
    override fun getGender(): Int = userSharedPreferences.getGender()

    // 地域取得
    override fun getArea(): Int = userSharedPreferences.getArea()

    // 生年月日取得
    override fun getBirthday(): String = userSharedPreferences.getBirthday()

    // 登録済アーティスト数設定
    override fun getFavorite(): Int = userSharedPreferences.getFavorite()

    // 登録済みアーティスト取得
    override fun setFavorite(value: Int) {
        userSharedPreferences.setFavorite(value)
    }

    // 全ユーザ情報削除
    override fun removeAll() {
        userSharedPreferences.removeAll()
    }

}
