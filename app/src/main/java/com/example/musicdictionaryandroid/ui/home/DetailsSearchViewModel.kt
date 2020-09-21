package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

class DetailsSearchViewModel : ViewModel() {

    var artistForm = ArtistsForm()

    // 絞り込みリスト
    private lateinit var genreKeyList: Array<String>
    private lateinit var genre1List: Array<String>
    private lateinit var genre2List: Array<String>
    private lateinit var genre3List: Array<String>
    private lateinit var genre4List: Array<String>

    // 絞り込みカテゴリ、絞り込み属性
    val genre1KeyList = MutableLiveData<Array<String>>()
    val genre2KeyList = MutableLiveData<Array<String>>()
    val genre3KeyList = MutableLiveData<Array<String>>()
    val genre4KeyList = MutableLiveData<Array<String>>()
    val genre1KeyInt = MutableLiveData<Int>(0)
    val genre2KeyInt = MutableLiveData<Int>(0)
    val genre3KeyInt = MutableLiveData<Int>(0)
    val genre4KeyInt = MutableLiveData<Int>(0)
    val genre1ValueList = MutableLiveData<Array<String>>()
    val genre2ValueList = MutableLiveData<Array<String>>()
    val genre3ValueList = MutableLiveData<Array<String>>()
    val genre4ValueList = MutableLiveData<Array<String>>()
    val genre1ValueInt = MutableLiveData<Int>(0)
    val genre2ValueInt = MutableLiveData<Int>(0)
    val genre3ValueInt = MutableLiveData<Int>(0)
    val genre4ValueInt = MutableLiveData<Int>(0)

    // 絞り込みエリア、絞り込みスピナー、絞り込みラベル、絞り込み属性スピナー、絞り込み追加ボタン
    val isSortContainer2 = MutableLiveData<Boolean>(false)
    val isSortContainer3 = MutableLiveData<Boolean>(false)
    val isSortContainer4 = MutableLiveData<Boolean>(false)
    val isSortGenre1Key = MutableLiveData<Boolean>(true)
    val isSortGenre2Key = MutableLiveData<Boolean>(true)
    val isSortGenre3Key = MutableLiveData<Boolean>(true)
    val isSortGenre1Label = MutableLiveData<Boolean>(false)
    val isSortGenre2Label = MutableLiveData<Boolean>(false)
    val isSortGenre3Label = MutableLiveData<Boolean>(false)
    val isSortGenre1ValueList = MutableLiveData<Boolean>(false)
    val isSortGenre2ValueList = MutableLiveData<Boolean>(false)
    val isSortGenre3ValueList = MutableLiveData<Boolean>(false)
    val isSortGenre4ValueList = MutableLiveData<Boolean>(false)
    val isGenreButtonArea1 = MutableLiveData<Boolean>(true)
    val isGenreButtonArea2 = MutableLiveData<Boolean>(true)
    val isGenreButtonArea3 = MutableLiveData<Boolean>(true)
    val isGenreAddButton1 = MutableLiveData<Boolean>(true)
    val isGenreAddButton2 = MutableLiveData<Boolean>(true)
    val isGenreAddButton3 = MutableLiveData<Boolean>(true)


    // 初期化
    fun init(genreKeyList: Array<String>,
             genre1List: Array<String>,
             genre2List: Array<String>,
             genre3List: Array<String>,
             genre4List: Array<String>) {
        this.genreKeyList = genreKeyList
        this.genre1List = genre1List
        this.genre2List = genre2List
        this.genre3List = genre3List
        this.genre4List = genre4List
        genre1KeyList.value = genreKeyList
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        artistForm.gender = checkedId
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        artistForm.length = checkedId
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        artistForm.voice = checkedId
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        artistForm.lyrics = checkedId
    }

    // 絞り込みジャンル変更
    fun changeGenreKey(position: Int, index: Int) {
        when (position) {
            1 -> changeGenreKey(genre1KeyList.value?.get(index), genre1ValueList, isSortGenre1ValueList)
            2 -> changeGenreKey(genre2KeyList.value?.get(index), genre2ValueList, isSortGenre2ValueList)
            3 -> changeGenreKey(genre3KeyList.value?.get(index), genre3ValueList, isSortGenre3ValueList)
            4 -> changeGenreKey(genre4KeyList.value?.get(index), genre4ValueList, isSortGenre4ValueList)
        }
    }
    private fun changeGenreKey(categoryName: String?, KeySpinner: MutableLiveData<Array<String>>, valueSpinner: MutableLiveData<Boolean>) {
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
        }?: run {
            valueSpinner.value = false
        }
    }

    // 絞り込みジャンル追加ボタン
    fun addSortList(position: Int) {
        when (position) {
            1 -> {listFilter(genre1KeyList.value, genre1KeyInt.value, genre2KeyList, isGenreButtonArea1, isSortContainer2, isSortGenre1Key, isSortGenre1Label)}
            2 -> {listFilter(genre2KeyList.value, genre2KeyInt.value, genre3KeyList, isGenreButtonArea2, isSortContainer3, isSortGenre2Key, isSortGenre2Label)}
            3 -> {listFilter(genre3KeyList.value, genre3KeyInt.value, genre4KeyList, isGenreButtonArea3, isSortContainer4, isSortGenre3Key, isSortGenre3Label)}
        }
    }
    private fun listFilter(keyList:Array<String>?, position: Int?, newList: MutableLiveData<Array<String>>,
                           buttonArea: MutableLiveData<Boolean>, newSortArea: MutableLiveData<Boolean>,
                           sortKeySelect: MutableLiveData<Boolean>, sortKeyLabel: MutableLiveData<Boolean>
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
            2 -> deleteGenre(isGenreButtonArea1, isSortContainer2, isSortGenre1Key, isSortGenre1Label)
            3 -> deleteGenre(isGenreButtonArea2, isSortContainer3, isSortGenre2Key, isSortGenre2Label)
            4 -> deleteGenre(isGenreButtonArea3, isSortContainer4, isSortGenre3Key, isSortGenre3Label)
        }
    }
    private fun deleteGenre(buttonArea: MutableLiveData<Boolean>, newSortArea: MutableLiveData<Boolean>,
                            sortKeySelect: MutableLiveData<Boolean>, sortKeyLabel: MutableLiveData<Boolean>) {
        buttonArea.value = true
        newSortArea.value = false
        sortKeyLabel.value = false
        sortKeySelect.value = true
    }

    // カテゴリの各項目設定
    fun changeGenreValue(position: Int, index: Int) {
        when (position) {
            1 -> changeGenreValue(index, isGenreAddButton1)
            2 -> changeGenreValue(index, isGenreAddButton2)
            3 -> changeGenreValue(index, isGenreAddButton3)
        }
    }
    private fun changeGenreValue(position: Int, button: MutableLiveData<Boolean>) {
        button.value = when(position) {
            0 -> false
            else -> true
        }
    }
    val isSubmit = MutableLiveData<Boolean>(false)
    private fun checkValidate() {
        isSubmit.value =
            !(artistForm.gender != 0 || artistForm.length != 0 || artistForm.voice != 0 || artistForm.lyrics != 0)
    }

}