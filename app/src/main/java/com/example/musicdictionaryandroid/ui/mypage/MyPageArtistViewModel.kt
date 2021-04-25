package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.data.database.entity.Artist
import com.example.musicdictionaryandroid.data.database.entity.ArtistsForm
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import kotlinx.coroutines.*

/**
 * 登録済みアーティスト一覧画面_UIロジック
 *
 */
class MyPageArtistViewModel(
    private val userUseCase: UserUseCase,
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val artistList: LiveData<List<Artist>> = artistUseCase.getArtistList()
    val status = MutableLiveData<Status<List<ArtistsForm>?>>(Status.Non)
    private val email = userUseCase.getEmail()

    /**
     * アーティスト削除
     */
    fun deleteArtist(artist: Artist): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.deleteArtist(artist.name!!, email)) {
            is Result.Success -> { status.postValue(Status.Success(result.data)) }
            is Result.Error -> { status.value = Status.Failure(result.exception) }
        }
    }
}
