package com.example.musicdictionaryandroid.data.remote.network

import com.example.musicdictionaryandroid.data.remote.network.service.MusicDictionaryService

interface Provider {
    fun musicDictionaryApi(): MusicDictionaryService
}
