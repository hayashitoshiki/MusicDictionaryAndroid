package com.example.musicdictionaryandroid.data.net

import com.example.musicdictionaryandroid.data.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * API接続先設定
 */
object Provider {

    fun api(): ApiService {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.webServer)
            .client(OkHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}
