package com.example.data.remote.network

import com.example.data.remote.network.service.MusicDictionaryService

interface Provider {
    fun musicDictionaryApi(): MusicDictionaryService
}
