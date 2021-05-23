package com.example.domain.model.entity

/**
 *  ユーザー情報
 *
 * @property email Email
 * @property name 名前
 * @property gender 性別
 * @property area 地域
 * @property birthday 生年月日
 * @property artistCount 登録済みアーティスト数
 */
data class User(
    val email: String,
    val name: String,
    val gender: Int,
    val area: Int,
    val birthday: String,
    val artistCount: Int
) : java.io.Serializable
