package com.example.musicdictionaryandroid.domain.model.value

/**
 * ジャンル１
 */
data class Genre1(val value: Int){
    init {
        if (value < 0 || 5 < value) {
            throw IllegalArgumentException("不正な値です")
        }
    }
}
