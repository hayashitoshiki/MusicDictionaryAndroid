package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp

class HomeViewModel : ViewModel() {

    val artistsFrom = ArtistsForm()
    val searchText = MutableLiveData<String>()
}
