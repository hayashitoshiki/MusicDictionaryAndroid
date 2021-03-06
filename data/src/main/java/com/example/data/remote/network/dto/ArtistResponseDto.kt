package com.example.data.remote.network.dto

import kotlinx.serialization.Serializable

/**
 * アーティスト取得（単数）のレスポンス
 * @property status ステータス情報
 * @property artist アーティスト情報
 */
@Serializable
data class ArtistResponseDto(
    val status: StatusDto,
    val artist: ArtistDto
)
