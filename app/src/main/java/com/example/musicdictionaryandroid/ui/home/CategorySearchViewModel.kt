package com.example.musicdictionaryandroid.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

class CategorySearchViewModel : ViewModel() {

    var artistForm = ArtistsForm()
    val searchData = MutableLiveData<ArtistsForm>()

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

    // 検索ボタンたっぷ
    fun goSearch() {
        if (artistForm.gender != 0 || artistForm.length != 0 || artistForm.voice != 0 || artistForm.lyrics != 0) {
            searchData.value = artistForm
        } else {
            Log.d("TAG", "どれか一つ以上入力してください")
        }
    }
}
