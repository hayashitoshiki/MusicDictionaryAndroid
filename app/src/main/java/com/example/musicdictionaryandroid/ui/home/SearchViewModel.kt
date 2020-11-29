package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

/**
 * 検索条件ダイアログUIロジック
 *
 */
class SearchViewModel  : ViewModel() {
    var artistForm = ArtistsForm()

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
    val genre1ValueList = MutableLiveData<Array<String>>()
    val genre2ValueList = MutableLiveData<Array<String>>()
    val genre1ValueInt = MutableLiveData<Int>(0)
    val genre2ValueInt = MutableLiveData<Int>(0)
    val genderValueInt = MutableLiveData<Int>(0)
    val lengthValueInt = MutableLiveData<Int>(0)
    val voiceValueInt = MutableLiveData<Int>(0)
    val lyricsValueInt = MutableLiveData<Int>(0)
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = isButton

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
        isButton.addSource(nameText) { changeArtistName(it!!) }
    }

    fun setArtist(artist: ArtistsForm) {
        artistForm = artist
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

    // アーティスト名変更
    fun changeArtistName(name: String) {
        artistForm.name = name
        checkValidate()
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        if (genderValueInt.value == checkedId) {
            genderValueInt.value = 0
        } else {
            genderValueInt.value = checkedId
        }
        artistForm.gender = genderValueInt.value!!
        checkValidate()
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        if (lengthValueInt.value == checkedId) {
            lengthValueInt.value = 0
        } else {
            lengthValueInt.value = checkedId
        }
        artistForm.length = lengthValueInt.value!!
        checkValidate()
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        if (voiceValueInt.value == checkedId) {
            voiceValueInt.value = 0
        } else {
            voiceValueInt.value = checkedId
        }
        artistForm.voice = voiceValueInt.value!!
        checkValidate()
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        if (lyricsValueInt.value == checkedId) {
            lyricsValueInt.value = 0
        } else {
            lyricsValueInt.value = checkedId
        }
        artistForm.lyrics = lyricsValueInt.value!!
        checkValidate()
    }

    // ジャンル１の変更
    fun changeGenre1(index: Int) {
        artistForm.genre1 = index
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
        checkValidate()
    }

    // ジャンル２の変更
    fun changeGenre2(index: Int) {
        artistForm.genre2 = index
    }

    // バリデーションチェック
    private fun checkValidate() {
        isButton.value = artistForm.name != "" || artistForm.gender != 0 || artistForm.length != 0 || artistForm.voice != 0 || artistForm.lyrics != 0 || artistForm.genre1 != 0
    }
}