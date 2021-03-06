package com.example.domain.model.value

/**
 * ジャンル２
 */
data class Genre2(val value: Int) {
    init {
        if (value < 0 || 11 < value) {
            throw IllegalArgumentException("不正な値です")
        }
    }
}
