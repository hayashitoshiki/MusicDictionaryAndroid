package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.ArtistUseCase
import kotlinx.coroutines.launch

/**
 * メイン画面_UIロジック
 */
class MainActivityViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    fun init() {
        // 最新の登録アーティスト取得
        viewModelScope.launch {
            artistUseCase.getArtistsByEmail()
        }
    }
}
