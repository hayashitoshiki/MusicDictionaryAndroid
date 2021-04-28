package com.example.musicdictionaryandroid.ui.mypage

import android.util.Log
import androidx.lifecycle.*
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * アーティスト情報登録・追加画面_UIロジック
 *
 * @property artistUseCase
 */
class MyPageArtistAddViewModel(private val artistUseCase: ArtistUseCase) : ViewModel() {

    val status = MutableLiveData<Status<Artist>>()

    // 絞り込みリスト
    private lateinit var mainGenreList: List<String>
    private lateinit var subGenre0List: List<String>
    private lateinit var subGenre1List: List<String>
    private lateinit var subGenre2List: List<String>
    private lateinit var subGenre3List: List<String>
    private lateinit var subGenre4List: List<String>
    private lateinit var subGenre5List: List<String>
    private lateinit var subGenre6List: List<String>

    //    private val _artist = MutableLiveData<Artist>()
//    val artist: LiveData<Artist> = _artist
    private val _editMode = MutableLiveData<Int>()
    val editMode: LiveData<Int> = _editMode
    private val _titleText = MutableLiveData("")
    val titleText: LiveData<String> = _titleText
    val nameText = MutableLiveData<String>()

    val gender = MutableLiveData(0)
    val voice = MutableLiveData(0)
    val length = MutableLiveData(0)
    val lyrics = MutableLiveData(0)

    private val _genre1ValueList = MutableLiveData<List<String>>(listOf())
    val genre1ValueList: LiveData<List<String>> = _genre1ValueList
    private val _genre2ValueList = MutableLiveData<List<String>>(listOf())
    val genre2ValueList: LiveData<List<String>> = _genre2ValueList
    val genre1 = MutableLiveData(0)
    val genre2 = MutableLiveData(0)
    private val _submitText = MutableLiveData<String>()
    val submitText: LiveData<String> = _submitText
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    companion object {
        const val ADD_MODE = 1
        const val CHANGE_MODE = 2
        const val TAG = "MyPageArtistAddViewModel"
    }

    init {
        _isEnableSubmitButton.addSource(nameText) { validate() }
        _isEnableSubmitButton.addSource(gender) { validate() }
        _isEnableSubmitButton.addSource(voice) { validate() }
        _isEnableSubmitButton.addSource(length) { validate() }
        _isEnableSubmitButton.addSource(lyrics) { validate() }
        _isEnableSubmitButton.addSource(genre1) { validate() }
        _isEnableSubmitButton.addSource(genre2) { validate() }
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
        _genre1ValueList.value = mainGenreList.toList()

        artist?.let {
            when (artist.genre1.value) {
                0 -> _genre2ValueList.value = subGenre0List.toList()
                1 -> _genre2ValueList.value = subGenre1List.toList()
                2 -> _genre2ValueList.value = subGenre2List.toList()
                3 -> _genre2ValueList.value = subGenre3List.toList()
                4 -> _genre2ValueList.value = subGenre4List.toList()
                5 -> _genre2ValueList.value = subGenre5List.toList()
                6 -> _genre2ValueList.value = subGenre6List.toList()
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

    /**
     * 送信処理
     */
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
                Log.e(TAG, "指定モードが正しくありません。mode = " + editMode.value)
            }
        }
    }

    // アーティスト登録
    private fun addArtist(artist: Artist): Job = viewModelScope.launch {
        when (val result = artistUseCase.addArtist(artist)) {
            is Result.Success -> {
                status.value = Status.Success(result.data)
            }
            is Result.Error -> {
                status.value = Status.Failure(result.exception)
            }
        }
    }

    // アーティスト更新
    private fun updateArtist(artist: Artist): Job = viewModelScope.launch {
        when (val result = artistUseCase.updateArtist(artist)) {
            is Result.Success -> {
                status.value = Status.Success(result.data)
            }
            is Result.Error -> {
                status.value = Status.Failure(result.exception)
            }
        }
    }

    /**
     * 絞り込みジャンル変更処理
     *
     * @param index 大分類ジャンルの中のインデックス値
     */
    fun changeGenre1(index: Int) {
        when (index) {
            0 -> _genre2ValueList.postValue(subGenre0List.toList())
            1 -> _genre2ValueList.postValue(subGenre1List.toList())
            2 -> _genre2ValueList.postValue(subGenre2List.toList())
            3 -> _genre2ValueList.postValue(subGenre3List.toList())
            4 -> _genre2ValueList.postValue(subGenre4List.toList())
            5 -> _genre2ValueList.postValue(subGenre5List.toList())
            6 -> _genre2ValueList.postValue(subGenre6List.toList())
        }
    }
}
