package com.example.musicdictionaryandroid.model.dao

import androidx.room.*
import com.example.musicdictionaryandroid.model.entity.Artist

/**
 * DB呼び出しクエリ管理
 */
@Dao
interface ArtistDao {

    @Insert
    suspend fun insert(artist: Artist)

    @Update
    fun update(artist: Artist)

    @Delete
    fun delete(artist: Artist)

    @Query("delete from artists")
    fun deleteAll()

    @Query("SELECT * FROM artists")
    fun getAll(): Array<Artist>

    @Query("select * from artists where name = :name")
    fun getArtistByName(name: String): Artist
}
