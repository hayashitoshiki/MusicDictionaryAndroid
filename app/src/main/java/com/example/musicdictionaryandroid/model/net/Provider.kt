package com.example.musicdictionaryandroid.model.net

import com.example.musicdictionaryandroid.model.util.Constant
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * API接続先設定
 */
object Provider {

    fun api(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.webServer)
            .client(OkHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
