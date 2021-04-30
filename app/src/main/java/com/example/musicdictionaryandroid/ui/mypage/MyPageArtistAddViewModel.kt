package com.example.musicdictionaryandroid.ui.mypage

import android.util.Log
import androidx.lifecycle.*
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * アーティスト情報登録・追加画面_UIロジック
 */
class MyPageArtistAddViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    companion object {
        const val ADD_MODE = 1
        const val CHANGE_MODE = 2
    }

    // 送信ステータス
    private val _status = MutableLiveData<Status<Artist>>(Status.Non)
    val status: LiveData<Status<Artist>> = _status

    // 絞り込みリスト
    private lateinit var mainGenreList: List<String>
    private lateinit var subGenre0List: List<String>
    private lateinit var subGenre1List: List<String>
    private lateinit var subGenre2List: List<String>
    private lateinit var subGenre3List: List<String>
    private lateinit var subGenre4List: List<String>
    private lateinit var subGenre5List: List<String>
    private lateinit var subGenre6List: List<String>

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
        _isEnableSubmitButton.addSource(nameText) { validate() }
        _isEnableSubmitButton.addSource(gender) { validate() }
        _isEnableSubmitButton.addSource(voice) { validate() }
        _isEnableSubmitButton.addSource(length) { validate() }
        _isEnableSubmitButton.addSource(lyrics) { validate() }
        _isEnableSubmitButton.addSource(genre1) { validate() }
        _isEnableSubmitButton.addSource(genre2) { validate() }
        _isProgressBar.addSource(status, Observer { changeProgressBar(it) })
    }

    fun init(
        genreList: Array<String>,
        genre1List: Array<String>,
        genre2List: Array<String>,
        genre3List: Array<String>,
        genre4List: Array<String>,
        genre5List: Array<String>,
        genre6List: Array<String>,
        artist: Artist?
    ) {
        this.mainGenreList = genreList.toList()
        this.subGenre0List = listOf("未選択")
        this.subGenre1List = genre1List.toList()
        this.subGenre2List = genre2List.toList()
        this.subGenre3List = genre3List.toList()
        this.subGenre4List = genre4List.toList()
        this.subGenre5List = genre5List.toList()
        this.subGenre6List = genre6List.toList()
        _genre1List.value = mainGenreList.toList()

        artist?.let {
            when (artist.genre1.value) {
                0 -> _genre2List.value = subGenre0List.toList()
                1 -> _genre2List.value = subGenre1List.toList()
                2 -> _genre2List.value = subGenre2List.toList()
                3 -> _genre2List.value = subGenre3List.toList()
                4 -> _genre2List.value = subGenre4List.toList()
                5 -> _genre2List.value = subGenre5List.toList()
                6 -> _genre2List.value = subGenre6List.toList()
            }
            _editMode.value = CHANGE_MODE
            _titleText.value = "アーティスト編集"
            _submitText.value = "変更"
            nameText.value = artist.name
            gender.value = artist.gender.value
            voice.value = artist.voice.value
            length.value = artist.length.value
            lyrics.value = artist.lyrics.value
            genre1.value = artist.genre1.value
            genre2.value = artist.genre2.value
        } ?: run {
            _editMode.value = ADD_MODE
            _titleText.value = "アーティスト登録"
            _submitText.value = "追加"
        }
    }

    // 入力バリデート
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
            0 -> _genre2List.postValue(subGenre0List.toList())
            1 -> _genre2List.postValue(subGenre1List.toList())
            2 -> _genre2List.postValue(subGenre2List.toList())
            3 -> _genre2List.postValue(subGenre3List.toList())
            4 -> _genre2List.postValue(subGenre4List.toList())
            5 -> _genre2List.postValue(subGenre5List.toList())
            6 -> _genre2List.postValue(subGenre6List.toList())
        }
    }
}
