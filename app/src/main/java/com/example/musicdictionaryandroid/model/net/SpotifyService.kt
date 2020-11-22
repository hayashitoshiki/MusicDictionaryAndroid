package com.example.musicdictionaryandroid.model.net

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.QueryMap

interface SpotifyService
{
    @PUT("v1/me/player/play")
    fun search(@QueryMap stringParams: Map<String, String>): Call<ArrayList<ArtistsForm>>
}