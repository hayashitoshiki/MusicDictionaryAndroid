package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

class HomeViewModel : ViewModel() {

    val artistsFrom = ArtistsForm()
    val searchText = MutableLiveData<String>()
}
