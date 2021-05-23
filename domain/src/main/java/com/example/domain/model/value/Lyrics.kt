package com.example.domain.model.value

/**
 * 歌詞
 */
data class Lyrics(val value: Int) {
    init {
        if (value < 0 || 5 < value) {
            throw IllegalArgumentException("不正な値です")
        }
    }
}
