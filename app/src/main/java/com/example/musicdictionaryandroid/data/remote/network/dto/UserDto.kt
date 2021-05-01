package com.example.musicdictionaryandroid.data.remote.network.dto

/**
 *  ユーザー情報
 *
 * @property email Email
 * @property name 名前
 * @property gender 性別
 * @property area 地域
 * @property birthday 生年月日
 * @property artist_count 登録済みアーティスト数
 */
data class UserDto(
    val email: String,
    val name: String,
    val gender: Int,
    val area: Int,
    val birthday: String,
    val artist_count: Int
) : java.io.Serializable
