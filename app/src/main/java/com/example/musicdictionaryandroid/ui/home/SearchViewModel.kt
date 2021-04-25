package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*

/**
 * 検索条件ダイアログUIロジック
 *
 */
class SearchViewModel : ViewModel() {

    // 絞り込みリスト
    private lateinit var mainGenreList: Array<String>
    private lateinit var subGenre0List: Array<String>
    private lateinit var subGenre1List: Array<String>
    private lateinit var subGenre2List: Array<String>
    private lateinit var subGenre3List: Array<String>
    private lateinit var subGenre4List: Array<String>
    private lateinit var subGenre5List: Array<String>
    private lateinit var subGenre6List: Array<String>

    val nameText = MutableLiveData<String>()
    val genderValueInt = MutableLiveData(0)
    val lengthValueInt = MutableLiveData(0)
    val voiceValueInt = MutableLiveData(0)
    val lyricsValueInt = MutableLiveData(0)
    val genre1ValueList = MutableLiveData<Array<String>>()
    val genre2ValueList = MutableLiveData<Array<String>>()
    val genre1ValueInt = MutableLiveData(0)
    val genre2ValueInt = MutableLiveData(0)
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    init {
        _isEnableSubmitButton.addSource(nameText) { checkValidate() }
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

    fun getArtist(): Artist {
        val name = nameText.value.toString()
        val gender = Gender.getEnumByValue(0)
        val length = Length(0)
        val voice = Voice(0)
        val lyrics = Lyrics(0)
        val genre1 = Genre1(0)
        val genre2 = Genre2(0)
        return Artist(name, gender, voice, length, lyrics, genre1, genre2)
    }

    fun setArtist(artist: ArtistsDto) {
        when (artist.genre1) {
            0 -> genre2ValueList.value = subGenre0List
            1 -> genre2ValueList.value = subGenre1List
            2 -> genre2ValueList.value = subGenre2List
            3 -> genre2ValueList.value = subGenre3List
            4 -> genre2ValueList.value = subGenre4List
            5 -> genre2ValueList.value = subGenre5List
            6 -> genre2ValueList.value = subGenre6List
        }
        nameText.value = artist.name
        genderValueInt.value = artist.gender
        lengthValueInt.value = artist.length
        voiceValueInt.value = artist.voice
        lyricsValueInt.value = artist.lyrics
        genre1ValueInt.value = artist.genre1
        genre2ValueInt.value = artist.genre2
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
        genre1ValueInt.value = index
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

    // ジャンル２の変更
    fun changeGenre2(index: Int) {
        genre2ValueInt.value = index
    }

    // バリデーションチェック
    private fun checkValidate() {
        _isEnableSubmitButton.value = nameText.value != "" || genderValueInt.value != 0 || lengthValueInt.value != 0 || voiceValueInt.value != 0 || lyricsValueInt.value != 0 || genre1ValueInt.value != 0
    }
}
