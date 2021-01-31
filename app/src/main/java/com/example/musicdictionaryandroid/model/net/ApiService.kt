package com.example.musicdictionaryandroid.model.net

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import retrofit2.Call
import retrofit2.http.*

/**
 * 各URLの管理
 */

interface ApiService {

    @GET("artist/find.json")
    fun search(@QueryMap stringParams: Map<String, String>): Call<ArrayList<ArtistsForm>>

    @GET("artist/find/recommend.json")
    fun getRecommend(@Query("email") email: String): Call<ArrayList<ArtistsForm>>

    @GET("artist/find/soaring.json")
    fun getSoaring(): Call<ArrayList<ArtistsForm>>

    @GET("artist/find/email.json")
    fun findByEmail(@Query("email") email: String): Call<ArrayList<ArtistsForm>>

    @GET("artist/save.json")
    fun addArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): Call<ArtistsForm>

    @GET("/artist/update.json")
    fun updateArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): Call<ArtistsForm>

    @GET("artist/delete.json")
    fun deleteArtist(@Query("name") name: String, @Query("email") email: String): Call<CallBackData>

    @GET("user/find.json")
    fun getUserByEmail(@Query("email") email: String): Call<User>

    @GET("/user/save.json")
    fun createUser(
        @Query("user") user: String
    ): Call<CallBackData>

    @GET("user/update.json")
    fun changeUser(@Query("user") user: User, @Query("email") email: String): Call<CallBackData>
}
