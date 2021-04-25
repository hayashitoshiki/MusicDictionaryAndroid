package com.example.musicdictionaryandroid.domain.model.value

/**
 * 長さ
 */
data class Length(val value: Int) {
    init {
        if (value < 0 || 5 < value) {
            throw IllegalArgumentException("不正な値です")
        }
    }
}
