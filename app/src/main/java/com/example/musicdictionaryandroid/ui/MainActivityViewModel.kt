package com.example.musicdictionaryandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    fun init() {
        // 最新の登録アーティスト取得
        viewModelScope.launch {
            artistUseCase.getArtistsByEmail()
        }
    }
}
