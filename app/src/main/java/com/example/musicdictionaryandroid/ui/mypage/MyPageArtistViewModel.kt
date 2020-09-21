package com.example.musicdictionaryandroid.ui.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.*

class MyPageArtistViewModel(
    private val firebaseRepository: FireBaseRepository,
    private val apiServerRepository: ApiServerRepository,
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()
    var email = ""

    init{
        email = firebaseRepository.getEmail()
    }

    // 登録しているアーティストを取得
    fun getArtistsByEmail(): Job = viewModelScope.launch {
        status.value = Status.Loading
        runCatching { withContext(Dispatchers.IO) { apiServerRepository.getArtistsByEmail(email) } }
            .onSuccess {
                it.body()?.let { artistsFormList ->
                    PreferenceRepositoryImp.setFavorite(artistsFormList.size)
                    artistsRepository.deleteAll()
                    artistsRepository.updateAll(artistsFormList)
                }
                status.value = Status.Success(it.body()) }
            .onFailure {
                val artist = artistsRepository.getArtistAll()
                status.value = Status.Success(artist) }
    }

    // アーティスト削除
    fun deleteArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        val artistList: ArrayList<ArtistsForm> = (status.value as Status.Success).data!!
        status.value = Status.Loading
        runCatching { withContext(Dispatchers.IO) { apiServerRepository.deleteArtist(artist.name, email) } }
            .onSuccess {
                artistList.remove(artist)
                PreferenceRepositoryImp.setFavorite(artistList.size)
                artistsRepository.deleteArtist(artist.name)
                status.value = Status.Success(artistList) }
            .onFailure { status.value = Status.Failure(it) }
    }
}
