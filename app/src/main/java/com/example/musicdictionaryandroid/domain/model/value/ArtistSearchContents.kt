package com.example.musicdictionaryandroid.domain.model.value

import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents

/**
 * アーティスト検索情報
 */
sealed class ArtistSearchContents<out R> {
    // 検索情報
    data class Conditions(val value: ArtistConditions) : ArtistSearchContents<ArtistConditions>()

    // アーティスト情報
    data class Item(val value: ArtistContents) : ArtistSearchContents<ArtistContents>()
}

