package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * アーティスト情報登録・追加画面_UIロジック
 *
 * @property userUseCase
 * @property artistUseCase
 */
class MyPageArtistAddViewModel(
    private val userUseCase: UserUseCase,
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    private var email = ""
    private var oldArtistName: String = ""
    val searchText = MutableLiveData<String>("")
    var artistForm = MutableLiveData<ArtistsForm>(ArtistsForm())
    val status = MutableLiveData<Status<CallBackData?>>()

    init {
        email = userUseCase.getEmail()
    }

    fun init(artist: ArtistsForm) {
        searchText.value = artist.name
        oldArtistName = artist.name
        artistForm.value = artist
    }

    /**
     * 送信処理
     */
    fun submit() {
        artistForm.value?.let { when {
            it.name == "" -> status.value = Status.Success(CallBackData("001"))
            it.gender == 0 -> status.value = Status.Success(CallBackData("002"))
            it.length == 0 -> status.value = Status.Success(CallBackData("003"))
            it.voice == 0 -> status.value = Status.Success(CallBackData("004"))
            it.lyrics == 0 -> status.value = Status.Success(CallBackData("005"))
            else -> { when (oldArtistName) {
                    "" -> addArtist(it)
                    else -> updateArtist(it)
            } }
        } }
    }

    // アーティスト登録
    private fun addArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        when (val result = artistUseCase.addArtist(artist, email)) {
            is Result.Success -> { status.value = Status.Success(result.data) }
            is Result.Error -> { status.value = Status.Failure(result.exception) }
        }
    }

    // アーティスト更新
    private fun updateArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        when (val result = artistUseCase.updateArtist(artist, oldArtistName, email)) {
            is Result.Success -> { status.value = Status.Success(result.data) }
            is Result.Error -> { status.value = Status.Failure(result.exception) }
        }
    }

    // アーティスト名変更
    fun changeArtistName(name: String) {
        artistForm.value!!.name = name
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        artistForm.value!!.gender = checkedId
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        artistForm.value!!.length = checkedId
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        artistForm.value!!.voice = checkedId
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        artistForm.value!!.lyrics = checkedId
    }
}
