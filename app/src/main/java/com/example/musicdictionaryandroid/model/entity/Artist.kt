package com.example.musicdictionaryandroid.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * アーティスト
 *
 * @property id
 * @property name アーティスト名
 * @property gender 性別
 * @property voice 声の高さ
 * @property length 曲の長さ
 * @property lyrics 歌詞の言語比率
 * @property genre1 ジャンル１
 * @property genre2 ジャンル２
 */
@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String? = "アーティスト名",
    var gender: Int? = 0,
    var voice: Int? = 0,
    var length: Int? = 0,
    var lyrics: Int? = 0,
    var genre1: String = "ジャンル１",
    var genre2: String = "ジャンル２"
)
