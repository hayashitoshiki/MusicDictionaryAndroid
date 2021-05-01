package com.example.musicdictionaryandroid.data.remote.network.dto

/**
 * アーティスト取得（単数）のレスポンス
 * @property status ステータス情報
 * @property artist アーティスト情報
 */
data class ArtistResponseDto(
    val status: StatusDto,
    val artist: ArtistDto
)
