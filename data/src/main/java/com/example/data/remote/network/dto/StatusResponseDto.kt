package com.example.data.remote.network.dto

import kotlinx.serialization.Serializable

/**
 * ステータスコードのみのレスポンス
 */
@Serializable
data class StatusResponseDto(val status: StatusDto)
