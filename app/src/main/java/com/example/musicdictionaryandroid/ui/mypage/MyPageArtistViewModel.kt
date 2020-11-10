package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.*

/**
 * 登録済みアーティスト一覧画面_UIロジック
 *
 * @property userUseCase
 * @property artistUseCase
 */
class MyPageArtistViewModel(
    private val userUseCase: UserUseCase,
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()
    var email = ""

    init {
        email = userUseCase.getEmail()
    }

    /**
     * 登録済みアーティスト取得
     *
     * @return 登録済みアーティスト
     */
    fun getArtistsByEmail(): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsByEmail(email)) {
            is Result.Success -> { status.value = Status.Success(result.data) }
            is Result.Error -> { status.value = Status.Failure(result.exception) }
        }
    }

    /**
     * アーティスト削除
     *
     * @param artist 削除するアーティスト
     * @return
     */
    fun deleteArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        val artistList: ArrayList<ArtistsForm> = (status.value as Status.Success).data!!
        status.value = Status.Loading
        when (val result = artistUseCase.deleteArtist(artist.name, email)) {
            is Result.Success -> { artistList.remove(artist)
                status.value = Status.Success(artistList) }
            is Result.Error -> { status.value = Status.Failure(result.exception) } }
    }
}
