package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
        when (val result = artistUseCase.getArtistsBySoaring()) {
            is Result.Success -> { status.postValue(Status.Success(result.data)) }
            is Result.Error -> { status.postValue(Status.Failure(result.exception)) }
        }
    }
}
