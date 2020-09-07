package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

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
}
