package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.ApiServerRepositoryImp
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultViewModel : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()

    private val apiServerRepository = ApiServerRepositoryImp()

    // 指定した条件でアーティストを検索
    fun getArtists(artist: ArtistsForm): Job = viewModelScope.launch {
        status.value = Status.Loading
        runCatching { withContext(Dispatchers.IO) { apiServerRepository.getArtistsBy(artist) } }
            .onSuccess { status.value = Status.Success(it.body()) }
            .onFailure { status.value = Status.Failure(it) }
    }
}
