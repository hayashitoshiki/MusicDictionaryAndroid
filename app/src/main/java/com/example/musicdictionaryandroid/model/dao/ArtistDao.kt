package com.example.musicdictionaryandroid.model.dao

import androidx.room.*
import com.example.musicdictionaryandroid.model.entity.Artist

@Dao
interface ArtistDao {

    @Insert
    fun insert(artist: Artist)

    @Update
    fun update(artist: Artist)

    @Delete
    fun delete(artist: Artist)

    @Query("delete from artists")
    fun deleteAll()

    @Query("select * from artists")
    fun getAll(): List<Artist>

    @Query("select * from artists where name = :name")
    fun getArtistByName(name: String): Artist
}