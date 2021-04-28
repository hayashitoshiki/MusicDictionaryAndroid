package com.example.musicdictionaryandroid.domain.model.value

import java.io.Serializable

/**
 * アーティスト検索条件
 */
data class ArtistConditions(
    val name: String?,
    val gender: Gender?,
    val voice: Voice?,
    val length: Length?,
    val lyrics: Lyrics?,
    val genre1: Genre1?,
    val genre2: Genre2?
) : Serializable
