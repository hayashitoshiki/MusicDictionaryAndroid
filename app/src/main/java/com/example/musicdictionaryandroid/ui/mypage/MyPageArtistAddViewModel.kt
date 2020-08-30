package com.example.musicdictionaryandroid.ui.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.ApiServerRepositoryImp
import com.example.musicdictionaryandroid.model.repository.FireBaseRepositoryMock
import com.example.musicdictionaryandroid.model.util.Status
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyPageArtistAddViewModel : ViewModel() {

    var artistForm = ArtistsForm()
    var oldArtistName: String = ""
    val searchText = MutableLiveData<String>()
    val status = MutableLiveData<Status<CallBackData?>>()
    companion object {
        private const val TAG = "SettingAddArtistPresenter"
    }

    // アーティスト登録
    private fun addArtist(): Job = viewModelScope.launch {
        // バリデート
        if (artistForm.name != "" &&
                artistForm.gender != 0 &&
                artistForm.length != 0 &&
                artistForm.voice != 0 &&
                artistForm.lyrics != 0) {
            val email = FireBaseRepositoryMock().getEmail()
            runCatching { withContext(Dispatchers.IO) { ApiServerRepositoryImp().addArtist(artistForm, email) } }
                .onSuccess { status.value = Status.Success(it.body()) }
                .onFailure { status.value = Status.Failure(it) }
        } else {
            Log.d(TAG, "全て入力してください")
            val result = CallBackData()
            result.status = "000"
            status.value = Status.Success(result)
        }
    }

    // アーティスト更新
    private fun updateArtist(): Job = viewModelScope.launch {
        // バリデート
        if (artistForm.name != "" &&
            artistForm.gender != 0 &&
            artistForm.length != 0 &&
            artistForm.voice != 0 &&
            artistForm.lyrics != 0) {
            val email = FireBaseRepositoryMock().getEmail()
            runCatching { withContext(Dispatchers.IO) { ApiServerRepositoryImp().updateArtist(artistForm, oldArtistName, email) } }
                .onSuccess { status.value = Status.Success(it.body()) }
                .onFailure { status.value = Status.Failure(it) }
        } else {
            Log.d(TAG, "全て入力してください")
            val result = CallBackData()
            result.status = "000"
            status.value = Status.Success(result)
        }
    }

    // 送信
    fun submit() {
        if (oldArtistName != "") updateArtist()
        else addArtist()
    }

    // アーティスト名変更
    fun changeArtistName(name: String) {
        artistForm.name = name
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
}
