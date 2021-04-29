package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.local.preferences.UserSharedPreferences
import com.example.musicdictionaryandroid.domain.model.entity.User

class LocalUserRepositoryImp(private val userSharedPreferences: UserSharedPreferences) : LocalUserRepository {

    // ユーザ情報取得
    override fun getUser(): User = userSharedPreferences.getUser()

    // ユーザ情報設定
    override fun setUser(user: User) {
        userSharedPreferences.setUser(user)
    }

    // Email設定
    override fun getEmail(): String = userSharedPreferences.getEmail()

    // Email取得
    override fun setEmail(value: String) {
        userSharedPreferences.setEmail(value)
    }

    // ユーザ名設定
    override fun getName(): String = userSharedPreferences.getName()

    // ユーザ名取得
    override fun setName(value: String) {
        userSharedPreferences.setName(value)
    }

    // 性別設定
    override fun getGender(): Int = userSharedPreferences.getGender()

    // 性別取得
    override fun setGender(value: Int) {
        userSharedPreferences.setGender(value)
    }

    // 地域設定
    override fun getArea(): Int = userSharedPreferences.getArea()

    // 地域取得
    override fun setArea(value: Int) {
        userSharedPreferences.setArea(value)
    }

    // 生年月日設定
    override fun getBirthday(): Int = userSharedPreferences.getBirthday()

    // 生年月日取得
    override fun setBirthday(value: Int) {
        userSharedPreferences.setBirthday(value)
    }

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
