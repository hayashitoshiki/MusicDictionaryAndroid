package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 登録済みアーティスト一覧画面_UIロジック
 */
class MyPageArtistViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    // アーティストリスト
    val artistList: LiveData<List<Artist>> = artistUseCase.getArtistList().asLiveData()

    // ステータス
    private val _status = MutableLiveData<Status<String>>(Status.Non)
    val status: LiveData<Status<String>> = _status

    // Viewの表示制御
    private val _isProgressBar = MediatorLiveData<Boolean>()
    val isProgressBar: LiveData<Boolean> = _isProgressBar
    private val _isNoDataText = MediatorLiveData<Boolean>()
    val isNoDataText: LiveData<Boolean> = _isNoDataText

    init {
        _isProgressBar.addSource(status, Observer { changeProgressBar(it) })
        _isNoDataText.addSource(artistList, Observer { changeNoDataText(it) })
    }

    // プログレスバーの表示制御
    private fun changeProgressBar(status: Status<String>) {
        _isProgressBar.value = status is Status.Loading
    }

    // データ０件文言の表示制御
    private fun changeNoDataText(artistList: List<Artist>) {
        _isNoDataText.value = artistList.isNullOrEmpty()
    }

    // アーティスト削除
    fun deleteArtist(artist: Artist): Job = viewModelScope.launch {
        _status.value = Status.Loading
        when (val result = artistUseCase.deleteArtist(artist.name)) {
            is Result.Success -> {
                _status.postValue(Status.Success(result.data))
            }
            is Result.Error -> {
                _status.value = Status.Failure(result.exception)
            }
        }
    }
}
