package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

/**
 * カテゴリ検索画面_UIロジック
 *
 */
class CategorySearchViewModel : ViewModel() {

    val artistForm = ArtistsForm()
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = isButton

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        artistForm.gender = checkedId
        checkValidate()
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        artistForm.length = checkedId
        checkValidate()
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        artistForm.voice = checkedId
        checkValidate()
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        artistForm.lyrics = checkedId
        checkValidate()
    }

    // バリデーションチェック
    private fun checkValidate() {
        isButton.value =
            artistForm.gender != 0 || artistForm.length != 0 || artistForm.voice != 0 || artistForm.lyrics != 0
    }
}
