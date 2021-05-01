package com.example.musicdictionaryandroid.data.remote.network.dto

/**
 * アーティスト取得(複数)のレスポンス
 * @property status ステータス情報
 * @property artist アーティスト情報
 */
data class ArtistsResponseDto(
    val status: StatusDto,
    val artist: List<ArtistDto>
)
