package com.example.data.remote.network.dto

/**
 * ユーザ情報のレスポンス
 *
 * @property status ステータス情報
 * @property user ユーザ情報
 */
class UserResponseDto(
    val status: StatusDto,
    val user: UserDto
)
