package com.example.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.database.entity.BookmarkArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkArtistDao {
    @Insert
    suspend fun insert(bookmarkArtistEntity: BookmarkArtistEntity)

    @Query("delete from bookMarkArtists WHERE name = :name")
    suspend fun deleteByName(name: String)

    @Query("delete from bookMarkArtists")
    suspend fun deleteAll()

    @Query("SELECT * FROM bookMarkArtists")
    fun getAll(): Flow<List<BookmarkArtistEntity>>

    @Query("select * from bookMarkArtists where name = :name")
    suspend fun getArtistByName(name: String): BookmarkArtistEntity?
}
