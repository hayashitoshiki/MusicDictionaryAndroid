package com.example.data.remote.network

import com.example.data.BuildConfig
import com.example.data.remote.network.service.MusicDictionaryService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * API接続先設定
 */
object ProviderImp : Provider {

    // ネットワークタイムアウト
    private const val defaultTimeout = 5L

    // ネットワーク設定
    private fun build(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(defaultTimeout, TimeUnit.SECONDS)
            .writeTimeout(defaultTimeout, TimeUnit.SECONDS)
            .readTimeout(defaultTimeout, TimeUnit.SECONDS)
            .build()
    }

    /**
     * MusicDictionaryApi
     */
    override fun musicDictionaryApi(): MusicDictionaryService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.webServer)
            .client(build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(MusicDictionaryService::class.java)
    }
}
