package com.example.musicdictionaryandroid.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicdictionaryandroid.data.local.database.dao.ArtistDao
import com.example.musicdictionaryandroid.data.local.database.entity.ArtistEntity

/**
 * DB定義
 */
@Database(entities = [ArtistEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}
