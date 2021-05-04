package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.data.repository.LocalBookmarkArtistRepository
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import kotlinx.coroutines.launch

class ResultAdapterViewModel(private val localBookmarkArtistRepository: LocalBookmarkArtistRepository) : ViewModel() {

    fun setBookMark(artistContents: ArtistContents) = viewModelScope.launch {
        localBookmarkArtistRepository.addArtist(artistContents)
    }

    fun setUnBookMark(artistContents: ArtistContents) = viewModelScope.launch {
        localBookmarkArtistRepository.deleteArtist(artistContents.artist.name)
    }
}
