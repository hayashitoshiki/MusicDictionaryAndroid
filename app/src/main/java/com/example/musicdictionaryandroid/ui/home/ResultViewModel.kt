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
 * 検索結果画面_UIロジック
 *
 * @property artistUseCase
 */
class ResultViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()

    /**
     * アーティスト検索
     *
     * @param artist 検索するアーティストの条件
     * @return 一致したアーティスト一覧
     */
    fun getArtists(artist: ArtistsForm): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsBy(artist)) {
            is Result.Success -> {
                val arrayList = arrayListOf(artist)
                result.data?.let { arrayList.addAll(it) }
                status.postValue(Status.Success(arrayList)) }
            is Result.Error -> { status.postValue(Status.Failure(result.exception)) }
        }
    }
}
