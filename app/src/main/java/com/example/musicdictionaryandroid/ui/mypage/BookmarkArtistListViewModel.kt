package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase

class BookmarkArtistListViewModel(artistUseCase: ArtistUseCase) : ViewModel() {

    val bookmarkArtistList: LiveData<List<ArtistSearchContents.Item>> = artistUseCase.getBookArkArtistAll().asLiveData()
}
