package com.example.musicdictionaryandroid.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel(private val userUseCase: UserUseCase,
                            private val artistUseCase: ArtistUseCase ) : ViewModel() {
    fun init() {
        // 最新の登録アーティスト取得
        viewModelScope.launch {
            val email = userUseCase.getEmail()
            artistUseCase.getArtistsByEmail(email)
        }
    }
}
