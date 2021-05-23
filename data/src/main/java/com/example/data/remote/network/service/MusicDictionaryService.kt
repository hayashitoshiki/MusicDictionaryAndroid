package com.example.data.remote.network.service

import com.example.data.remote.network.dto.ArtistResponseDto
import com.example.data.remote.network.dto.ArtistsResponseDto
import com.example.data.remote.network.dto.StatusResponseDto
import com.example.data.remote.network.dto.UserResponseDto
import com.example.domain.model.entity.User
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * MusicDictionaryApiの各URLの管理
 */

interface MusicDictionaryService {

    // region アーティスト情報関連

    @GET("artist/find.json")
    suspend fun search(@QueryMap stringParams: Map<String, String>): ArtistsResponseDto

    @GET("artist/find/recommend.json")
    suspend fun getRecommend(@Query("email") email: String): ArtistsResponseDto

    @GET("artist/find/soaring.json")
    suspend fun getSoaring(): ArtistsResponseDto

    @GET("artist/find/email.json")
    suspend fun findByEmail(@Query("email") email: String): ArtistsResponseDto

    @GET("artist/save.json")
    suspend fun addArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): ArtistResponseDto

    @GET("/artist/update.json")
    suspend fun updateArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): ArtistResponseDto

    @GET("artist/delete.json")
    suspend fun deleteArtist(@Query("name") name: String, @Query("email") email: String): StatusResponseDto

    // endregion

    // region ユーザ情報関連

    @GET("user/find.json")
    suspend fun getUserByEmail(@Query("email") email: String): UserResponseDto

    @GET("/user/save.json")
    suspend fun createUser(@Query("user") user: String): StatusResponseDto

    @GET("user/update.json")
    suspend fun changeUser(@Query("user") user: User, @Query("email") email: String): StatusResponseDto

    // endregion
}
