package com.example.musicdictionaryandroid.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicdictionaryandroid.model.entity.Artist

/**
 * DB定義
 */
@Database(entities = arrayOf(Artist::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}
