package com.example.musicdictionaryandroid.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicdictionaryandroid.model.entity.Artist

@Database(entities = arrayOf(Artist::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
}