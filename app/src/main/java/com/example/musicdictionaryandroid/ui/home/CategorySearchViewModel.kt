package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

/**
 * カテゴリ検索画面_UIロジック
 *
 */
class CategorySearchViewModel : ViewModel() {

    var artistForm = MutableLiveData<ArtistsForm>(ArtistsForm())
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = isButton

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        artistForm.value!!.gender = checkedId
        checkValidate()
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        artistForm.value!!.length = checkedId
        checkValidate()
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        artistForm.value!!.voice = checkedId
        checkValidate()
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        artistForm.value!!.lyrics = checkedId
        checkValidate()
    }

    // バリデーションチェック
    private fun checkValidate() {
        isButton.value =
            artistForm.value!!.gender != 0 || artistForm.value!!.length != 0 || artistForm.value!!.voice != 0 || artistForm.value!!.lyrics != 0
    }
}
