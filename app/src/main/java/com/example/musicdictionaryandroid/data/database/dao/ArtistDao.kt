package com.example.musicdictionaryandroid.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musicdictionaryandroid.data.database.entity.Artist

/**
 * DB呼び出しクエリ管理
 */
@Dao
interface ArtistDao {

    @Insert
    suspend fun insert(artist: Artist)

    @Update
    fun update(artist: Artist)

    @Query("delete from artists WHERE name = :name")
    suspend fun deleteByName(name: String)

    @Query("delete from artists")
    fun deleteAll()

    @Query("SELECT * FROM artists")
    suspend fun getAll(): Array<Artist>

    @Query("SELECT * FROM artists")
    fun getArtistList(): LiveData<List<Artist>>

    @Query("select * from artists where name = :name")
    fun getArtistByName(name: String): Artist
}
