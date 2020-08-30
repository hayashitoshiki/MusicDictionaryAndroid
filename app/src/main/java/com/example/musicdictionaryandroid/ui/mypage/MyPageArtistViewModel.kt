package com.example.musicdictionaryandroid.ui.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.ApiServerRepositoryImp
import com.example.musicdictionaryandroid.model.repository.FireBaseRepositoryMock
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.*

class MyPageArtistViewModel : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()

    // 登録しているアーティストを取得
    fun getArtistsByEmail(): Job = viewModelScope.launch {
        Log.d("TAG", "アーティスト取得")
        status.value = Status.Loading
        val email = FireBaseRepositoryMock().getEmail()
        runCatching { withContext(Dispatchers.IO) { ApiServerRepositoryImp().getArtistsByEmail(email) } }
            .onSuccess { status.value = Status.Success(it.body()) }
            .onFailure { status.value = Status.Failure(it) }
    }

    // アーティスト削除
    fun deleteArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        val artistList: ArrayList<ArtistsForm> = (status.value as Status.Success).data!!
        status.value = Status.Loading
        val email = FireBaseRepositoryMock().getEmail()
        runCatching { withContext(Dispatchers.IO) { ApiServerRepositoryImp().deleteArtist(artist.name, email) } }
            .onSuccess {
                artistList.remove(artist)
                status.value = Status.Success(artistList) }
            .onFailure { status.value = Status.Failure(it) }
    }
}
