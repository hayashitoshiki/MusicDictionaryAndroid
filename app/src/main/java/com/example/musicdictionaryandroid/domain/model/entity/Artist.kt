package com.example.musicdictionaryandroid.domain.model.entity

import com.example.musicdictionaryandroid.domain.model.value.*

/**
 * アーティスト情報
 */
data class Artist(
    val name: String,
    val gender: Gender,
    val voice: Voice,
    val length: Length,
    val lyrics: Lyrics,
    val genre1: Genre1,
    val genre2: Genre2
) : java.io.Serializable
