package com.example.musicdictionaryandroid.data.net

import com.example.musicdictionaryandroid.BuildConfig
import com.example.musicdictionaryandroid.data.util.Constant
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * API呼び出詳細設定
 */
object OkHttpClientBuilder {
    fun build(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(Constant.defaultTimeout, TimeUnit.SECONDS)
            .writeTimeout(Constant.defaultTimeout, TimeUnit.SECONDS)
            .readTimeout(Constant.defaultTimeout, TimeUnit.SECONDS)
            .build()
    }
}
