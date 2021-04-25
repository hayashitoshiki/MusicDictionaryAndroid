package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
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

    val status = MutableLiveData<Status<List<ArtistContents>>>()

    /**
     * アーティスト検索
     *
     * @param artist 検索するアーティストの条件
     * @return 一致したアーティスト一覧
     */
    fun getArtists(artist: Artist): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsBy(artist)) {
            is Result.Success -> {
                val artistContents = ArtistContents(artist,null, null, 0, 0, 0, 0 ,0 ,0 ,0, 0)
                val arrayList = arrayListOf(artistContents)
                arrayList.addAll(result.data)
                status.postValue(Status.Success(arrayList))
            }
            is Result.Error -> { status.postValue(Status.Failure(result.exception)) }
        }
    }
}
