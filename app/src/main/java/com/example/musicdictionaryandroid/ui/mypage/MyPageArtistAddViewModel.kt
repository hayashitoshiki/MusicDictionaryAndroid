package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.*
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
    val nameText = MutableLiveData<String>()
    var artistForm = MutableLiveData<ArtistsForm>(ArtistsForm())
    val status = MutableLiveData<Status<CallBackData?>>()
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean>
        get() = isButton

    init {
        email = userUseCase.getEmail()
        isButton.addSource(nameText) { changeArtistName(it!!) }
    }

    fun init(artist: ArtistsForm) {
        oldArtistName = artist.name
        nameText.value = artist.name
        artistForm.value = artist
    }

    // 入力バリデート
    private fun validate() {
        isButton.value = artistForm.value!!.name != "" && artistForm.value!!.gender != 0 && artistForm.value!!.length != 0 && artistForm.value!!.voice != 0 && artistForm.value!!.lyrics != 0
    }

    /**
     * 送信処理
     */
    fun submit() {
        artistForm.value?.let {
            when (oldArtistName) {
                "" -> addArtist(it)
                else -> updateArtist(it)
            }
        }
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
        validate()
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        artistForm.value!!.gender = checkedId
        validate()
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        artistForm.value!!.length = checkedId
        validate()
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        artistForm.value!!.voice = checkedId
        validate()
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        artistForm.value!!.lyrics = checkedId
        validate()
    }
}
