package com.example.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * アーティストテーブル
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
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    var name: String,
    var gender: Int,
    var voice: Int,
    var length: Int,
    var lyrics: Int,
    var genre1: Int,
    var genre2: Int
) : java.io.Serializable
