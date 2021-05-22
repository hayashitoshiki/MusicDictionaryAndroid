package com.example.data.remote.network.dto

/**
 * ステータス情報
 * @property code ステータスコード
 * @property message メッセージ
 */
data class StatusDto(
    val code: Int,
    val message: String
)
