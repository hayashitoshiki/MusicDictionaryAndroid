package com.example.musicdictionaryandroid.data.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity

/**
 * DB定義
 */
@Database(entities = arrayOf(ArtistEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}
