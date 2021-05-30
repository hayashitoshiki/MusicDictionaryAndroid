package com.example.data.remote.network.dto

import kotlinx.serialization.Serializable

/**
 * ステータス情報
 * @property code ステータスコード
 * @property message メッセージ
 */
@Serializable
data class StatusDto(
    val code: Int,
    val message: String
)
