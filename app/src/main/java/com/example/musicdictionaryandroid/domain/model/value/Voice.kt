package com.example.musicdictionaryandroid.domain.model.value

/**
 * 声の高さ
 */
data class Voice(val value: Int) {
    init {
        if (value < 0 || 5 < value) {
            throw IllegalArgumentException("不正な値です")
        }
    }
}
