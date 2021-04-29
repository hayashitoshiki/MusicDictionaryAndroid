package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.Status
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

    val status = MutableLiveData<Status<List<ArtistSearchContents<*>>>>()

    /**
     * アーティスト検索
     *
     * @param artist 検索するアーティストの条件
     * @return 一致したアーティスト一覧
     */
    fun getArtists(artist: ArtistConditions): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsBy(artist)) {
            is Result.Success -> {
                val conditions = ArtistSearchContents.Conditions(artist)
                val arrayList = arrayListOf<ArtistSearchContents<*>>(conditions)
                result.data.forEach { contents ->
                    arrayList.add(ArtistSearchContents.Item(contents))
                }
                status.postValue(Status.Success(arrayList))
            }
            is Result.Error -> {
                status.postValue(Status.Failure(result.exception))
            }
        }
    }
}
