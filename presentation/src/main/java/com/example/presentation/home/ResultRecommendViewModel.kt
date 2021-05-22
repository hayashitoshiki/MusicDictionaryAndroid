package com.example.presentation.home

import androidx.lifecycle.*
import com.example.domain.model.value.Result
import com.example.domain.model.value.ArtistConditions
import com.example.domain.model.value.ArtistSearchContents
import com.example.domain.usecase.ArtistUseCase
import com.example.presentation.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * おすすめアーティスト検索結果画面_UIロジック
 */
class ResultRecommendViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    // ステータス
    private val _status = MutableLiveData<Status<List<ArtistSearchContents<*>>>>()
    val status: LiveData<Status<List<ArtistSearchContents<*>>>> = _status


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
    fun getRecommend(): Job = viewModelScope.launch {
        _status.value = Status.Loading
        when (val result = artistUseCase.getArtistsByRecommend()) {
            is Result.Success -> {
                val artist = ArtistConditions("おすすめ", null, null, null, null, null, null)
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
