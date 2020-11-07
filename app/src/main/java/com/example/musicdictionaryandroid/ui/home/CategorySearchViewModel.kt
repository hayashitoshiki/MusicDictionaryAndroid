package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

/**
 * カテゴリ検索画面_UIロジック
 *
 */
class CategorySearchViewModel : ViewModel() {

    var artistForm = ArtistsForm()

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

    val isSubmit = MutableLiveData<Boolean>(false)
    private fun checkValidate() {
        isSubmit.value =
            !(artistForm.gender != 0 || artistForm.length != 0 || artistForm.voice != 0 || artistForm.lyrics != 0)
    }
}
