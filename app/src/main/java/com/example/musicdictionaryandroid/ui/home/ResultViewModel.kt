package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 検索結果画面_UIロジック
 */
class ResultViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    // ステータス
    private val _status = MutableLiveData<Status<List<ArtistSearchContents<*>>>>()
    val status: LiveData<Status<List<ArtistSearchContents<*>>>> = _status

    // Viewの表示制御
    private val _isProgressBar = MediatorLiveData<Boolean>()
    val isProgressBar: LiveData<Boolean> = _isProgressBar
    private val _isNoDataText = MediatorLiveData<Boolean>()
    val isNoDataText: LiveData<Boolean> = _isNoDataText

    init {
        _isProgressBar.addSource(status, Observer { changeProgressBar(it) })
        _isNoDataText.addSource(status, Observer { changeNoDataText(it) })
    }

    // プログレスバーの表示制御
    private fun changeProgressBar(status: Status<List<ArtistSearchContents<*>>>) {
        _isProgressBar.value = status is Status.Loading
    }

    // データ０件文言の表示制御
    private fun changeNoDataText(status: Status<List<ArtistSearchContents<*>>>) {
        _isNoDataText.value = when (status) {
            is Status.Success -> status.data.size < 2
            else -> false
        }
    }


    // アーティスト検索
    fun getArtists(artist: ArtistConditions): Job = viewModelScope.launch {
        _status.value = Status.Loading
        when (val result = artistUseCase.getArtistsBy(artist)) {
            is Result.Success -> {
                val conditions = ArtistSearchContents.Conditions(artist)
                val arrayList = arrayListOf<ArtistSearchContents<*>>(conditions)
                result.data.forEach { contents ->
                    arrayList.add(ArtistSearchContents.Item(contents))
                }
                _status.postValue(Status.Success(arrayList))
            }
            is Result.Error -> {
                _status.postValue(Status.Failure(result.exception))
            }
        }
    }
}
