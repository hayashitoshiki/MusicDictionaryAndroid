package com.example.tosik.musicdictionary_androlid.model.net

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // @GET("api2.json")
    @GET("artist/find.json")
    fun search(@QueryMap stringParams: Map<String, String>): Call<ArrayList<ArtistsForm>>

    // @GET("findByEmail.json")
    @GET("artist/find/email.json")
    fun findByEmail(@Query("email") email: String): Call<ArrayList<ArtistsForm>>

    // @GET("saveArtist.json")
    @GET("artist/save.json")
    fun addArtist(@QueryMap stringParams: Map<String, String>, @Query("email") email: String): Call<CallBackData>

    // @GET("updateArtist.json")
    @GET("/artist/update")
    fun updateArtist(@QueryMap stringParams: Map<String, String>, @Query("before_name") beforeName: String, @Query("email") email: String): Call<CallBackData>

    // @GET("deleteArtist.json")
    @GET("artist/delete.json")
    fun deleteArtist(@Query("name") name: String, @Query("email") email: String): Call<CallBackData>

    @GET("user/find.json")
    fun getUserByEmail(@Query("email") email: String): Call<User>

    @GET("/user/save.json")
    fun createUser(@Query("user") user: String
    ): Call<CallBackData>

    @GET("user/update.json")
    fun changeUser(@Query("user") user: User, @Query("email") email: String): Call<CallBackData>
}
