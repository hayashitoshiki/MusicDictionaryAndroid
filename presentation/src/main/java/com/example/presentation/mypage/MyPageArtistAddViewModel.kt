package com.example.presentation.mypage

import android.util.Log
import androidx.lifecycle.*
import com.example.domain.model.entity.Artist
import com.example.domain.model.value.*
import com.example.domain.usecase.ArtistUseCase
import com.example.presentation.util.MessageUtil
import com.example.presentation.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * アーティスト情報登録・追加画面_UIロジック
 */
class MyPageArtistAddViewModel(
    private val artist: Artist?,
    private val artistUseCase: ArtistUseCase,
    private val messageUtil: MessageUtil
) : ViewModel() {

    companion object {
        const val ADD_MODE = 1
        const val CHANGE_MODE = 2
    }

    // 送信ステータス
    private val _status = MutableLiveData<Status<Artist>>(Status.Non)
    val status: LiveData<Status<Artist>> = _status

    // 絞り込みリスト
    private val mainGenreList: List<String>
    private val subGenre0List: List<String>
    private val subGenre1List: List<String>
    private val subGenre2List: List<String>
    private val subGenre3List: List<String>
    private val subGenre4List: List<String>
    private val subGenre5List: List<String>
    private val subGenre6List: List<String>

    // 入力項目
    private val _titleText = MutableLiveData("")
    val titleText: LiveData<String> = _titleText
    val nameText = MutableLiveData("")
    val gender = MutableLiveData(0)
    val voice = MutableLiveData(0)
    val length = MutableLiveData(0)
    val lyrics = MutableLiveData(0)
    val genre1 = MutableLiveData(0)
    val genre2 = MutableLiveData(0)
    private val _genre1List = MutableLiveData<List<String>>(listOf())
    val genre1List: LiveData<List<String>> = _genre1List
    private val _genre2List = MutableLiveData<List<String>>(listOf())
    val genre2List: LiveData<List<String>> = _genre2List

    // その他View制御
    private val _editMode = MutableLiveData<Int>()
    val editMode: LiveData<Int> = _editMode
    private val _submitText = MutableLiveData<String>()
    val submitText: LiveData<String> = _submitText
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton
    private val _isProgressBar = MediatorLiveData<Boolean>()
    val isProgressBar: LiveData<Boolean> = _isProgressBar

    init {
        // Observer設定
        _isEnableSubmitButton.addSource(nameText) { validate() }
        _isEnableSubmitButton.addSource(gender) { validate() }
        _isEnableSubmitButton.addSource(voice) { validate() }
        _isEnableSubmitButton.addSource(length) { validate() }
        _isEnableSubmitButton.addSource(lyrics) { validate() }
        _isEnableSubmitButton.addSource(genre1) { validate() }
        _isEnableSubmitButton.addSource(genre2) { validate() }
        _isProgressBar.addSource(status, Observer { changeProgressBar(it) })

        // カテゴリリスト取得
        this.mainGenreList = messageUtil.getMainCategory()
        this.subGenre0List = messageUtil.getSubCategory(0)
        this.subGenre1List = messageUtil.getSubCategory(1)
        this.subGenre2List = messageUtil.getSubCategory(2)
        this.subGenre3List = messageUtil.getSubCategory(3)
        this.subGenre4List = messageUtil.getSubCategory(4)
        this.subGenre5List = messageUtil.getSubCategory(5)
        this.subGenre6List = messageUtil.getSubCategory(6)
        _genre1List.value = mainGenreList

        // アーティスト情報初期化
        artist?.let {
            when (artist.genre1.value) {
                0 -> _genre2List.value = subGenre0List
                1 -> _genre2List.value = subGenre1List
                2 -> _genre2List.value = subGenre2List
                3 -> _genre2List.value = subGenre3List
                4 -> _genre2List.value = subGenre4List
                5 -> _genre2List.value = subGenre5List
                6 -> _genre2List.value = subGenre6List
            }
            _editMode.value = CHANGE_MODE
            _titleText.value = messageUtil.getArtistChangeTitle()
            _submitText.value = messageUtil.getChangeButton()
            nameText.value = artist.name
            gender.value = artist.gender.value
            voice.value = artist.voice.value
            length.value = artist.length.value
            lyrics.value = artist.lyrics.value
            genre1.value = artist.genre1.value
            genre2.value = artist.genre2.value
        } ?: run {
            _editMode.value = ADD_MODE
            _titleText.value = messageUtil.getArtistAddTitle()
            _submitText.value = messageUtil.getAddButton()
        }
    }

    // 送信ボタンバリデート
    private fun validate() {
        _isEnableSubmitButton.value = nameText.value != "" && gender.value != 0 &&
            length.value != 0 && voice.value != 0 &&
            lyrics.value != 0 && genre1.value != 0 && genre2.value != 0
    }

    // プログレスバーの表示制御
    private fun changeProgressBar(status: Status<Artist>) {
        _isProgressBar.value = status is Status.Loading
    }

    // 送信処理
    fun submit() {
        val name = nameText.value!!
        val gender = Gender.getEnumByValue(gender.value!!)
        val voice = Voice(voice.value!!)
        val length = Length(length.value!!)
        val lyrics = Lyrics(lyrics.value!!)
        val genre1 = Genre1(genre1.value!!)
        val genre2 = Genre2(genre2.value!!)
        val artist = Artist(name, gender, voice, length, lyrics, genre1, genre2)

        when (_editMode.value) {
            ADD_MODE -> addArtist(artist)
            CHANGE_MODE -> updateArtist(artist)
            else -> {
                Log.e("TAG", "指定モードが正しくありません。mode = " + editMode.value)
            }
        }
    }

    // アーティスト登録
    private fun addArtist(artist: Artist): Job = viewModelScope.launch {
        when (val result = artistUseCase.addArtist(artist)) {
            is Result.Success -> {
                _status.value = Status.Success(result.data)
            }
            is Result.Error -> {
                _status.value = Status.Failure(result.exception)
            }
        }
    }

    // アーティスト更新
    private fun updateArtist(artist: Artist): Job = viewModelScope.launch {
        when (val result = artistUseCase.updateArtist(artist)) {
            is Result.Success -> {
                _status.value = Status.Success(result.data)
            }
            is Result.Error -> {
                _status.value = Status.Failure(result.exception)
            }
        }
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        gender.value = checkedId
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        length.value = checkedId
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        voice.value = checkedId
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        lyrics.value = checkedId
    }

    // 絞り込みジャンル変更処理
    fun changeGenre1(index: Int) {
        when (index) {
            0 -> _genre2List.postValue(subGenre0List)
            1 -> _genre2List.postValue(subGenre1List)
            2 -> _genre2List.postValue(subGenre2List)
            3 -> _genre2List.postValue(subGenre3List)
            4 -> _genre2List.postValue(subGenre4List)
            5 -> _genre2List.postValue(subGenre5List)
            6 -> _genre2List.postValue(subGenre6List)
        }
    }
}
