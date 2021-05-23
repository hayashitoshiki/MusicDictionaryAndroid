package com.example.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ブックマーク登録したアーティストEntity
 */
@Entity(tableName = "bookMarkArtists")
data class BookmarkArtistEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    var name: String,
    var gender: Int,
    var voice: Int,
    var length: Int,
    var lyrics: Int,
    var genre1: Int,
    var genre2: Int,
    val thumb: String? = "",
    val preview: String? = "",
    val generation1: Int,
    val generation2: Int,
    val generation3: Int,
    val generation4: Int,
    val generation5: Int,
    val generation6: Int,
    @ColumnInfo(name = "user_man")
    val userMan: Int,
    @ColumnInfo(name = "user_woman")
    val userWoman: Int
) : java.io.Serializable
