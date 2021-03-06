package com.example.data.remote.network.dto

import kotlinx.serialization.Serializable

/**
 * ユーザ情報のレスポンス
 *
 * @property status ステータス情報
 * @property user ユーザ情報
 */
@Serializable
class UserResponseDto(
    val status: StatusDto,
    val user: UserDto
)
