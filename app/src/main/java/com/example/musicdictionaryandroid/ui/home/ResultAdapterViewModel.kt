package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ResultAdapterのロジッククラス
 * ItemごとではなくAdapterに対して１つで管理
 *
 */
class ResultAdapterViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    companion object {
        // 再生時間
        const val PLAY_BACK_TIME = 30500L
    }

    // 現在再生している楽曲
    private var holdState: ResultAdapterBodyState? = null

    // 再生処理
    fun onClickPlayBack(state: ResultAdapterBodyState, artistContents: ArtistContents) {
        if (!artistContents.preview.isNullOrEmpty()) {
            if (holdState == state) {
                state.stopPlayback()
                holdState = null
            } else {
                holdState?.stopPlayback()
                state.startPlayback(artistContents.preview)
                holdState = state
                viewModelScope.launch {
                    delay(PLAY_BACK_TIME)
                    if (holdState == state) {
                        state.stopPlayback()
                        holdState = null
                    }
                }
            }
        }
    }

    // ブックマーク切り替え
    fun setBookMark(artistContents: ArtistContents) = viewModelScope.launch {
        when (artistContents.bookmarkFlg) {
            // 登録
            true -> artistUseCase.setBookmarkArtist(artistContents)
            // 解除
            false -> artistUseCase.deleteBookmarkArtist(artistContents)
        }
    }

}
