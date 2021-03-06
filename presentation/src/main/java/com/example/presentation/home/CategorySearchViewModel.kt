package com.example.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.value.*

/**
 * カテゴリ検索画面_UIロジック
 */
class CategorySearchViewModel : ViewModel() {

    // 絞り込みリスト
    private lateinit var mainGenreList: Array<String>
    private lateinit var subGenre0List: Array<String>
    private lateinit var subGenre1List: Array<String>
    private lateinit var subGenre2List: Array<String>
    private lateinit var subGenre3List: Array<String>
    private lateinit var subGenre4List: Array<String>
    private lateinit var subGenre5List: Array<String>
    private lateinit var subGenre6List: Array<String>

    // 入力項目
    val genderValueInt = MutableLiveData(0)
    val lengthValueInt = MutableLiveData(0)
    val voiceValueInt = MutableLiveData(0)
    val lyricsValueInt = MutableLiveData(0)
    val genre1ValueList = MutableLiveData<Array<String>>()
    val genre2ValueList = MutableLiveData<Array<String>>()
    val genre1ValueInt = MutableLiveData(0)
    val genre2ValueInt = MutableLiveData(0)

    // ボタンバリデート
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    init {
        _isEnableSubmitButton.addSource(genderValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(lengthValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(voiceValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(lyricsValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(genre1ValueInt) { checkValidate() }
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

    fun getArtist(): ArtistConditions {
        val gender = if (genderValueInt.value != null && genderValueInt.value != 0) {
            Gender.getEnumByValue(genderValueInt.value!!)
        } else null
        val length = if (lengthValueInt.value != null && lengthValueInt.value != 0) {
            Length(lengthValueInt.value!!)
        } else null
        val voice = if (voiceValueInt.value != null && voiceValueInt.value != 0) {
            Voice(voiceValueInt.value!!)
        } else null
        val lyrics = if (lyricsValueInt.value != null && lyricsValueInt.value != 0) {
            Lyrics(lyricsValueInt.value!!)
        } else null
        val genre1 = if (genre1ValueInt.value != null && genre1ValueInt.value != 0) {
            Genre1(genre1ValueInt.value!!)
        } else null
        val genre2 = if (genre2ValueInt.value != null && genre2ValueInt.value != 0) {
            Genre2(genre2ValueInt.value!!)
        } else null
        return ArtistConditions(null, gender, voice, length, lyrics, genre1, genre2)
    }

    // バリデーションチェック
    private fun checkValidate() {
        _isEnableSubmitButton.value = genderValueInt.value!! != 0 ||
            lengthValueInt.value!! != 0 ||
            voiceValueInt.value!! != 0 ||
            lyricsValueInt.value!! != 0 ||
            genre1ValueInt.value!! != 0
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        if (genderValueInt.value == checkedId) {
            genderValueInt.value = 0
        } else {
            genderValueInt.value = checkedId
        }
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        if (lengthValueInt.value == checkedId) {
            lengthValueInt.value = 0
        } else {
            lengthValueInt.value = checkedId
        }
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        if (voiceValueInt.value == checkedId) {
            voiceValueInt.value = 0
        } else {
            voiceValueInt.value = checkedId
        }
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        if (lyricsValueInt.value == checkedId) {
            lyricsValueInt.value = 0
        } else {
            lyricsValueInt.value = checkedId
        }
    }

    // ジャンル１の変更
    fun changeGenre1(index: Int) {
        genre2ValueInt.value = 0
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
}
