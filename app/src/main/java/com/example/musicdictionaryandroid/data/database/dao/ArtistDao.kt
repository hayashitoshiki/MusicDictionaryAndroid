package com.example.musicdictionaryandroid.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity

/**
 * DB呼び出しクエリ管理
 */
@Dao
interface ArtistDao {

    @Insert
    suspend fun insert(artistEntity: ArtistEntity)

    @Update
    fun update(artistEntity: ArtistEntity)

    @Query("delete from artists WHERE name = :name")
    suspend fun deleteByName(name: String)

    @Query("delete from artists")
    fun deleteAll()

    @Query("SELECT * FROM artists")
    suspend fun getAll(): Array<ArtistEntity>

    @Query("SELECT * FROM artists")
    fun getArtistList(): LiveData<List<ArtistEntity>>

    @Query("select * from artists where name = :name")
    fun getArtistByName(name: String): ArtistEntity
}