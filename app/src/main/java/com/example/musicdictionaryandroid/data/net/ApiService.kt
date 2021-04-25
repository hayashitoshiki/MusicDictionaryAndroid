package com.example.musicdictionaryandroid.data.net

import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import retrofit2.http.*

/**
 * 各URLの管理
 */

interface ApiService {

    @GET("artist/find.json")
    suspend fun search(@QueryMap stringParams: Map<String, String>): List<ArtistsDto>

    @GET("artist/find/recommend.json")
    suspend fun getRecommend(@Query("email") email: String): List<ArtistsDto>

    @GET("artist/find/soaring.json")
    suspend fun getSoaring(): List<ArtistsDto>

    @GET("artist/find/email.json")
    suspend fun findByEmail(@Query("email") email: String): List<ArtistsDto>

    @GET("artist/save.json")
    suspend fun addArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): ArtistsDto

    @GET("/artist/update.json")
    suspend fun updateArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): ArtistsDto

    @GET("artist/delete.json")
    suspend fun deleteArtist(@Query("name") name: String, @Query("email") email: String): CallBackData

    @GET("user/find.json")
    suspend fun getUserByEmail(@Query("email") email: String): User

    @GET("/user/save.json")
    suspend fun createUser(@Query("user") user: String): CallBackData

    @GET("user/update.json")
    suspend fun changeUser(@Query("user") user: User, @Query("email") email: String): CallBackData
}
