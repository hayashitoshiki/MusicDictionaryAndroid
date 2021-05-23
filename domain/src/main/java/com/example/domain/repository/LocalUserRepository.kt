package com.example.domain.repository

import com.example.domain.model.entity.User

/**
 * ローカル保持のユーザ情報に関するアクセスRepository
 */
interface LocalUserRepository {

    /**
     * ユーザ情報設定
     *
     * @param user ユーザ情報
     */
    fun setUser(user: User)

    /**
     * ユーザ情報取得
     *
     * @return ユーザ情報
     */
    fun getUser(): User

    /**
     * メールアドレス取得
     *
     * @return メールアドレス
     */
    fun getEmail(): String

    /**
     * ユーザ名取得
     *
     * @return ユーザ名
     */
    fun getName(): String

    /**
     * 性別取得
     *
     * @return 性別
     */
    fun getGender(): Int

    /**
     * 地域取得
     *
     * @return 地域
     */
    fun getArea(): Int

    /**
     * 生年月日取得
     *
     * @return 生年月日
     */
    fun getBirthday(): String

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
