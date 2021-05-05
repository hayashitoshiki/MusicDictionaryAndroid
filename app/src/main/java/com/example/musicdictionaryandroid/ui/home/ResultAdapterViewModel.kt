package com.example.musicdictionaryandroid.ui.home

import android.os.Handler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.data.repository.LocalBookmarkArtistRepository
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import kotlinx.coroutines.launch

/**
 * ResultAdapterのロジッククラス
 * ItemごとではなくAdapterに対して１つで管理
 *
 */
class ResultAdapterViewModel(private val localBookmarkArtistRepository: LocalBookmarkArtistRepository) : ViewModel() {

    // ブックマーク切り替え
    fun setBookMark(artistContents: ArtistContents) = viewModelScope.launch {
        when (artistContents.bookmarkFlg) {
            // 登録
            true -> localBookmarkArtistRepository.addArtist(artistContents)
            // 解除
            false -> localBookmarkArtistRepository.deleteArtist(artistContents.artist.name)
        }
    }

    // 現在再生している音楽
    private var holdState: ResultAdapterState? = null

    // 再生処理
    fun onClickPlayBack(state: ResultAdapterState, item: ArtistSearchContents.Item) {
        if (!item.value.preview.isNullOrEmpty()) {
            if (holdState != state) {
                holdState?.stopPlayback()
                state.startPlayback(item.value.preview)
                holdState = state
                Handler().postDelayed({
                    if (holdState == state) {
                        state.stopPlayback()
                        holdState = null
                    }
                }, 30500)
            } else {
                state.stopPlayback()
                holdState = null
            }
        }
    }

}
