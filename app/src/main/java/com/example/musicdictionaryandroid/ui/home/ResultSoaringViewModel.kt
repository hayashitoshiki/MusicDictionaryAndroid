package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 急上昇アーティスト一覧画面_UIロジック
 *
 * @property artistUseCase
 */
class ResultSoaringViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()

    /**
     * アーティスト検索
     *
     * @return 急上昇アーティスト一覧
     */
    fun getSoaring(): Job = viewModelScope.launch {
        status.value = Status.Loading
        runCatching { withContext(Dispatchers.IO) { artistUseCase.getArtistsBySoaring() } }
            .onSuccess { status.value = Status.Success(it.body()) }
            .onFailure { status.value = Status.Failure(it) }
    }
}
