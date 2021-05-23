package com.example.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.domain.model.value.ArtistSearchContents
import com.example.domain.usecase.ArtistUseCase

class BookmarkArtistListViewModel(artistUseCase: ArtistUseCase) : ViewModel() {

    val bookmarkArtistList: LiveData<List<ArtistSearchContents.Item>> = artistUseCase.getBookArkArtistAll().asLiveData()
}
