package com.example.musicdictionaryandroid.data.util

import com.example.musicdictionaryandroid.BuildConfig

class Constant {
    companion object {
        // APIサーバー
        const val webServer: String = BuildConfig.webServer

        // ネットワークタイムアウト
        const val defaultTimeout = 5L
    }
}
