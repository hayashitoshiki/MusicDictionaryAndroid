package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.domain.model.value.*

/**
 * 詳細検索画面_UIロジック
 */
class DetailsSearchViewModel : ViewModel() {

    // 絞り込みリスト
    private lateinit var genreKeyList: Array<String>
    private lateinit var genre1List: Array<String>
    private lateinit var genre2List: Array<String>
    private lateinit var genre3List: Array<String>
    private lateinit var genre4List: Array<String>

    // 絞り込みカテゴリ、絞り込み属性
    val genderValueInt = MutableLiveData(0)
    val lengthValueInt = MutableLiveData(0)
    val voiceValueInt = MutableLiveData(0)
    val lyricsValueInt = MutableLiveData(0)
    val genre1KeyList = MutableLiveData<Array<String>>()
    val genre2KeyList = MutableLiveData<Array<String>>()
    val genre3KeyList = MutableLiveData<Array<String>>()
    val genre4KeyList = MutableLiveData<Array<String>>()
    val genre1KeyInt = MutableLiveData(0)
    val genre2KeyInt = MutableLiveData(0)
    val genre3KeyInt = MutableLiveData(0)
    val genre4KeyInt = MutableLiveData(0)
    val genre1ValueList = MutableLiveData<Array<String>>()
    val genre2ValueList = MutableLiveData<Array<String>>()
    val genre3ValueList = MutableLiveData<Array<String>>()
    val genre4ValueList = MutableLiveData<Array<String>>()
    val genre1ValueInt = MutableLiveData(0)
    val genre2ValueInt = MutableLiveData(0)
    val genre3ValueInt = MutableLiveData(0)
    val genre4ValueInt = MutableLiveData(0)

    // 絞り込みエリア、絞り込みスピナー、絞り込みラベル、絞り込み属性スピナー、絞り込み追加ボタン
    private val _isSortContainer2 = MutableLiveData(false)
    val isSortContainer2: LiveData<Boolean> = _isSortContainer2
    private val _isSortContainer3 = MutableLiveData(false)
    val isSortContainer3: LiveData<Boolean> = _isSortContainer3
    private val _isSortContainer4 = MutableLiveData(false)
    val isSortContainer4: LiveData<Boolean> = _isSortContainer4
    private val _isSortGenre1Key = MutableLiveData(true)
    val isSortGenre1Key: LiveData<Boolean> = _isSortGenre1Key
    private val _isSortGenre2Key = MutableLiveData(true)
    val isSortGenre2Key: LiveData<Boolean> = _isSortGenre2Key
    private val _isSortGenre3Key = MutableLiveData(true)
    val isSortGenre3Key: LiveData<Boolean> = _isSortGenre3Key
    private val _isSortGenre1Label = MutableLiveData(false)
    val isSortGenre1Label: LiveData<Boolean> = _isSortGenre1Label
    private val _isSortGenre2Label = MutableLiveData(false)
    val isSortGenre2Label: LiveData<Boolean> = _isSortGenre2Label
    private val _isSortGenre3Label = MutableLiveData(false)
    val isSortGenre3Label: LiveData<Boolean> = _isSortGenre3Label
    private val _isSortGenre1ValueList = MutableLiveData(false)
    val isSortGenre1ValueList: LiveData<Boolean> = _isSortGenre1ValueList
    private val _isSortGenre2ValueList = MutableLiveData(false)
    val isSortGenre2ValueList: LiveData<Boolean> = _isSortGenre2ValueList
    private val _isSortGenre3ValueList = MutableLiveData(false)
    val isSortGenre3ValueList: LiveData<Boolean> = _isSortGenre3ValueList
    private val _isSortGenre4ValueList = MutableLiveData(false)
    val isSortGenre4ValueList: LiveData<Boolean> = _isSortGenre4ValueList
    private val _isGenreButtonArea1 = MutableLiveData(true)
    val isGenreButtonArea1: LiveData<Boolean> = _isGenreButtonArea1
    private val _isGenreButtonArea2 = MutableLiveData(true)
    val isGenreButtonArea2: LiveData<Boolean> = _isGenreButtonArea2
    private val _isGenreButtonArea3 = MutableLiveData(true)
    val isGenreButtonArea3: LiveData<Boolean> = _isGenreButtonArea3
    private val _isGenreAddButton1 = MutableLiveData(true)
    val isGenreAddButton1: LiveData<Boolean> = _isGenreAddButton1
    private val _isGenreAddButton2 = MutableLiveData(true)
    val isGenreAddButton2: LiveData<Boolean> = _isGenreAddButton2
    private val _isGenreAddButton3 = MutableLiveData(true)
    val isGenreAddButton3: LiveData<Boolean> = _isGenreAddButton3
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    init {
        _isEnableSubmitButton.addSource(genderValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(lengthValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(voiceValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(lyricsValueInt) { checkValidate() }
        _isEnableSubmitButton.addSource(genre1ValueInt) { checkValidate() }
    }

    // 初期化
    fun init(
        genreKeyList: Array<String>,
        genre1List: Array<String>,
        genre2List: Array<String>,
        genre3List: Array<String>,
        genre4List: Array<String>
    ) {
        this.genreKeyList = genreKeyList
        this.genre1List = genre1List
        this.genre2List = genre2List
        this.genre3List = genre3List
        this.genre4List = genre4List
        genre1KeyList.value = genreKeyList
    }

    // アーティスト取得
    fun getArtist(): ArtistConditions {
        val gender = Gender.getEnumByValue(genderValueInt.value!!)
        val length = Length(lengthValueInt.value!!)
        val voice = Voice(voiceValueInt.value!!)
        val lyrics = Lyrics(lyricsValueInt.value!!)
        val genre1 = Genre1(genre1ValueInt.value!!)
        val genre2 = Genre2(genre2ValueInt.value!!)
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

    // 絞り込みジャンル変更処理
    fun changeGenreKey(position: Int, index: Int) {
        when (position) {
            1 -> changeGenreKey(genre1KeyList.value?.get(index), genre1ValueList, _isSortGenre1ValueList)
            2 -> changeGenreKey(genre2KeyList.value?.get(index), genre2ValueList, _isSortGenre2ValueList)
            3 -> changeGenreKey(genre3KeyList.value?.get(index), genre3ValueList, _isSortGenre3ValueList)
            4 -> changeGenreKey(genre4KeyList.value?.get(index), genre4ValueList, _isSortGenre4ValueList)
        }
    }

    // ジャンルのキー変更
    private fun changeGenreKey(
        categoryName: String?,
        KeySpinner: MutableLiveData<Array<String>>,
        valueSpinner: MutableLiveData<Boolean>
    ) {
        val data = when (categoryName) {
            genreKeyList[1] -> genre1List
            genreKeyList[2] -> genre2List
            genreKeyList[3] -> genre3List
            genreKeyList[4] -> genre4List
            else -> null
        }
        data?.let {
            KeySpinner.value = data
            valueSpinner.value = true
        } ?: run {
            valueSpinner.value = false
        }
    }

    // 絞り込みジャンル追加ボタン処理
    fun addSortList(position: Int) {
        when (position) {
            1 -> {
                listFilter(
                    genre1KeyList.value,
                    genre1KeyInt.value,
                    genre2KeyList,
                    _isGenreButtonArea1,
                    _isSortContainer2,
                    _isSortGenre1Key,
                    _isSortGenre1Label
                )
            }
            2 -> {
                listFilter(
                    genre2KeyList.value,
                    genre2KeyInt.value,
                    genre3KeyList,
                    _isGenreButtonArea2,
                    _isSortContainer3,
                    _isSortGenre2Key,
                    _isSortGenre2Label
                )
            }
            3 -> {
                listFilter(
                    genre3KeyList.value,
                    genre3KeyInt.value,
                    genre4KeyList,
                    _isGenreButtonArea3,
                    _isSortContainer4,
                    _isSortGenre3Key,
                    _isSortGenre3Label
                )
            }
        }
    }

    // ジャンル１に表示するジャンルの絞り込み
    private fun listFilter(
        keyList: Array<String>?,
        position: Int?,
        newList: MutableLiveData<Array<String>>,
        buttonArea: MutableLiveData<Boolean>,
        newSortArea: MutableLiveData<Boolean>,
        sortKeySelect: MutableLiveData<Boolean>,
        sortKeyLabel: MutableLiveData<Boolean>
    ) {
        keyList?.also { genreList ->
            position?.also { position ->
                newList.value = genreList.filter { it != genreList[position] }.toTypedArray()
                buttonArea.value = false
                newSortArea.value = true
                sortKeyLabel.value = true
                sortKeySelect.value = false
            }
        }
    }

    // 絞り込みジャンル削除ボタン
    fun deleteSortButton(position: Int) {
        when (position) {
            2 -> deleteGenre(_isGenreButtonArea1, _isSortContainer2, _isSortGenre1Key, _isSortGenre1Label)
            3 -> deleteGenre(_isGenreButtonArea2, _isSortContainer3, _isSortGenre2Key, _isSortGenre2Label)
            4 -> deleteGenre(_isGenreButtonArea3, _isSortContainer4, _isSortGenre3Key, _isSortGenre3Label)
        }
    }

    // ジャンル削除
    private fun deleteGenre(
        buttonArea: MutableLiveData<Boolean>,
        newSortArea: MutableLiveData<Boolean>,
        sortKeySelect: MutableLiveData<Boolean>,
        sortKeyLabel: MutableLiveData<Boolean>
    ) {
        buttonArea.value = true
        newSortArea.value = false
        sortKeyLabel.value = false
        sortKeySelect.value = true
    }

    // カテゴリの各項目設定
    fun changeGenreValue(position: Int, index: Int) {
        when (position) {
            1 -> changeGenreValue(index, _isGenreAddButton1)
            2 -> changeGenreValue(index, _isGenreAddButton2)
            3 -> changeGenreValue(index, _isGenreAddButton3)
        }
    }

    // ジャンル変更
    private fun changeGenreValue(position: Int, button: MutableLiveData<Boolean>) {
        button.value = when (position) {
            0 -> false
            else -> true
        }
    }
}
