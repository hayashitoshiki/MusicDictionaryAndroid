package com.example.musicdictionaryandroid.domain.model.entity

/**
 * 検索結果表示用アーティスト情報
 */
data class ArtistContents(
    val artist: Artist,
    val thumb: String? = "",
    val preview: String? = "",
    val generation1: Int,
    val generation2: Int,
    val generation3: Int,
    val generation4: Int,
    val generation5: Int,
    val generation6: Int,
    val user_man: Int,
    val user_woman: Int,
    var bookmarkFlg: Boolean = false
)
