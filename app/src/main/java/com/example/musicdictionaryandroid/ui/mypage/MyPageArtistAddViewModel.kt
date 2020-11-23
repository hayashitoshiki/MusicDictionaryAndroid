package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
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
    val status = MutableLiveData<Status<ArtistsForm?>>()

    // 絞り込みリスト
    private lateinit var mainGenreList: Array<String>
    private lateinit var subGenre0List: Array<String>
    private lateinit var subGenre1List: Array<String>
    private lateinit var subGenre2List: Array<String>
    private lateinit var subGenre3List: Array<String>
    private lateinit var subGenre4List: Array<String>
    private lateinit var subGenre5List: Array<String>
    private lateinit var subGenre6List: Array<String>

    val genre1ValueList = MutableLiveData<Array<String>>()
    val genre2ValueList = MutableLiveData<Array<String>>()
    val genre1ValueInt = MutableLiveData<Int>(0)
    val genre2ValueInt = MutableLiveData<Int>(0)

    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean>
        get() = isButton

    init {
        email = userUseCase.getEmail()
        isButton.addSource(nameText) { changeArtistName(it!!) }
    }

    fun init(
        genreList: Array<String>,
        genre1List: Array<String>,
        genre2List: Array<String>,
        genre3List: Array<String>,
        genre4List: Array<String>,
        genre5List: Array<String>,
        genre6List: Array<String>
    ) {
        this.mainGenreList = genreList
        this.subGenre0List = arrayOf("未選択")
        this.subGenre1List = genre1List
        this.subGenre2List = genre2List
        this.subGenre3List = genre3List
        this.subGenre4List = genre4List
        this.subGenre5List = genre5List
        this.subGenre6List = genre6List
        genre1ValueList.value = mainGenreList
    }

    fun setArtist(artist: ArtistsForm) {
        oldArtistName = artist.name
        nameText.value = artist.name
        artistForm.value = artist
        when (artist.genre1) {
            0 -> genre2ValueList.value = subGenre0List
            1 -> genre2ValueList.value = subGenre1List
            2 -> genre2ValueList.value = subGenre2List
            3 -> genre2ValueList.value = subGenre3List
            4 -> genre2ValueList.value = subGenre4List
            5 -> genre2ValueList.value = subGenre5List
            6 -> genre2ValueList.value = subGenre6List
        }
        genre1ValueInt.value = artist.genre1
        genre2ValueInt.value = artist.genre2
    }

    // 入力バリデート
    private fun validate() {
        isButton.value = artistForm.value!!.name != "" && artistForm.value!!.gender != 0 &&
                artistForm.value!!.length != 0 && artistForm.value!!.voice != 0 &&
                artistForm.value!!.lyrics != 0 && artistForm.value!!.genre1 != 0 && artistForm.value!!.genre2 != 0
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
        when (val result = artistUseCase.updateArtist(artist, email)) {
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

    /**
     * 絞り込みジャンル変更処理
     *
     * @param index 大分類ジャンルの中のインデックス値
     */
    fun changeGenre1(index: Int) {
        artistForm.value!!.genre1 = index
        genre2ValueInt.postValue(0)
        when (index) {
            0 -> genre2ValueList.postValue(subGenre0List)
            1 -> genre2ValueList.postValue(subGenre1List)
            2 -> genre2ValueList.postValue(subGenre2List)
            3 -> genre2ValueList.postValue(subGenre3List)
            4 -> genre2ValueList.postValue(subGenre4List)
            5 -> genre2ValueList.postValue(subGenre5List)
            6 -> genre2ValueList.postValue(subGenre6List)
        }
    }

    // 歌詞情報の変更
    fun changeGenre2(index: Int) {
        artistForm.value!!.genre2 = index
        validate()
    }
}
