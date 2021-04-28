package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 登録済みアーティスト一覧画面_UIロジック
 *
 */
class MyPageArtistViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    val artistEntityList: LiveData<List<Artist>> = artistUseCase.getArtistList().asLiveData()
    val status = MutableLiveData<Status<List<Artist>>>(Status.Non)

    /**
     * アーティスト削除
     */
    fun deleteArtist(artist: Artist): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.deleteArtist(artist.name)) {
            is Result.Success -> {
                status.postValue(Status.Success(result.data))
            }
            is Result.Error -> {
                status.value = Status.Failure(result.exception)
            }
        }
    }
}
