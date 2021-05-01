package com.example.musicdictionaryandroid.data.local.preferences

import com.example.musicdictionaryandroid.domain.model.entity.User

/**
 * Preferenceへのユーザ情報格納
 */
interface UserSharedPreferences {

    /**
     * ユーザ情報取得
     *
     * @return ユーザ情報
     */
    fun getUser(): User

    /**
     * ユーザ情報設定
     *
     * @param user ユーザ情報
     */
    fun setUser(user: User)

    /**
     * メールアドレス取得
     *
     * @return メールアドレス
     */
    fun getEmail(): String

    /**
     * メールアドレス設定
     *
     * @param value メールアドレス
     */
    fun setEmail(value: String)

    /**
     * ユーザ名取得
     *
     * @return ユーザ名
     */
    fun getName(): String

    /**
     * ユーザ名設定
     *
     * @param value ユーザ名
     */
    fun setName(value: String)

    /**
     * 性別取得
     *
     * @return 性別
     */
    fun getGender(): Int

    /**
     * 性別設定
     *
     * @param value 性別
     */
    fun setGender(value: Int)

    /**
     * 地域取得
     *
     * @return 地域
     */
    fun getArea(): Int

    /**
     * 地域設定
     *
     * @param value 地域
     */
    fun setArea(value: Int)

    /**
     * 生年月日取得
     *
     * @return 生年月日
     */
    fun getBirthday(): String

    /**
     * 生年月日取得
     *
     * @param value 生年月日
     */
    fun setBirthday(value: String)

    /**
     * 登録済みアーティスト件数取得
     *
     * @return 登録済みアーティスト件数
     */
    fun getFavorite(): Int

    /**
     * 登録済みアーティスト件数設定
     *
     * @param value 登録済みアーティスト件数
     */
    fun setFavorite(value: Int)

    /**
     * ユーザ情報全削除
     *
     */
    fun removeAll()
}
