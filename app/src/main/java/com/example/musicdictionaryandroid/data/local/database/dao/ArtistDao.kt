package com.example.musicdictionaryandroid.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.musicdictionaryandroid.data.local.database.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow

/**
 * アーティストテーブル呼び出しクエリ管理
 */
@Dao
interface ArtistDao {

    @Insert
    suspend fun insert(artistEntity: ArtistEntity)

    @Query("update artists SET gender = :gender, voice = :voice, length = :length, lyrics = :lyrics, genre1 = :genre1, genre2 = :genre2 WHERE name = :name")
    suspend fun update(name: String, gender: Int, voice: Int, length: Int, lyrics: Int, genre1: Int, genre2: Int)

    @Query("delete from artists WHERE name = :name")
    suspend fun deleteByName(name: String)

    @Query("delete from artists")
    suspend fun deleteAll()

    @Query("SELECT * FROM artists")
    fun getAll(): Flow<List<ArtistEntity>>

    @Query("select * from artists where name = :name")
    suspend fun getArtistByName(name: String): ArtistEntity
}
