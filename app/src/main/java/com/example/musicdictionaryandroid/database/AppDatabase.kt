package com.example.musicdictionaryandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.database.dao.ArtistDao
import com.example.data.local.database.dao.BookmarkArtistDao
import com.example.data.local.database.entity.ArtistEntity
import com.example.data.local.database.entity.BookmarkArtistEntity

/**
 * DB定義
 */
@Database(entities = [ArtistEntity::class, BookmarkArtistEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun bookmarkArtistDao(): BookmarkArtistDao
}
