package com.example.musicdictionaryandroid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey open var id: Int? = 0,
    var name: String? = "アーティスト名",
    var gender: Int? = 0,
    var voice: Int? = 0,
    var length: Int? = 0,
    var lyrics: Int? = 0,
    var genre1: String = "ジャンル１",
    var genre2: String = "ジャンル２"
)
