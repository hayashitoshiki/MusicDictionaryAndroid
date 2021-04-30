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
 * 急上昇アーティスト一覧画面_UIロジック
 */
class ResultSoaringViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<List<ArtistSearchContents<*>>>>()

    // Viewの表示制御
    private val _isProgressBar = MediatorLiveData<Boolean>()
    val isProgressBar: LiveData<Boolean> = _isProgressBar

    init {
        _isProgressBar.addSource(status, Observer { changeProgressBar(it) })
    }

    // プログレスバーの表示制御
    private fun changeProgressBar(status: Status<List<ArtistSearchContents<*>>>) {
        _isProgressBar.value = status is Status.Loading
    }

    // アーティスト検索
    fun getSoaring(): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsBySoaring()) {
            is Result.Success -> {
                val artist = ArtistConditions("急上昇", null, null, null, null, null, null)
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
