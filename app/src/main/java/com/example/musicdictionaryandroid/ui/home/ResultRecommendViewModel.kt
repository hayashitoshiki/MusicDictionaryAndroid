package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.ApiServerRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultRecommendViewModel(
    private val firebaseRepository: FireBaseRepository,
    private val apiServerRepository : ApiServerRepository
) : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()
    private var email: String = ""

    init { email = firebaseRepository.getEmail() }

    // 指定した条件でアーティストを検索
    fun getRecommend(): Job = viewModelScope.launch {
        if (email.isNotEmpty()) {
            status.value = Status.Loading
            runCatching { withContext(Dispatchers.IO) { apiServerRepository.getArtistsByRecommend(email) } }
                .onSuccess { status.value = Status.Success(it.body()) }
                .onFailure { status.value = Status.Failure(it) }
        }
    }
}