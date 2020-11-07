package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.*

/**
 * 登録済みアーティスト一覧画面_UIロジック
 *
 * @property firebaseRepository
 * @property apiServerRepository
 * @property artistsRepository
 */
class MyPageArtistViewModel(
    private val firebaseRepository: FireBaseRepository,
    private val apiServerRepository: ApiServerRepository,
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()
    var email = ""

    init {
        email = firebaseRepository.getEmail()
    }

    /**
     * 登録済みアーティスト取得
     *
     * @return 登録済みアーティスト
     */
    fun getArtistsByEmail(): Job = viewModelScope.launch {
        status.value = Status.Loading
        runCatching { withContext(Dispatchers.IO) { apiServerRepository.getArtistsByEmail(email) } }
            .onSuccess {
                it.body()?.let { artistsFormList ->
                    PreferenceRepositoryImp.setFavorite(artistsFormList.size)
                    viewModelScope.launch(Dispatchers.IO) {
                        artistsRepository.deleteAll()
                        artistsRepository.updateAll(artistsFormList)
                    }
                }
                status.value = Status.Success(it.body()) }
            .onFailure {
                viewModelScope.launch(Dispatchers.IO) {
                    val artist = artistsRepository.getArtistAll()
                status.postValue(Status.Success(artist))
                } }
    }

    /**
     * アーティスト削除
     *
     * @param artist 削除するアーティスト
     * @return
     */
    fun deleteArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        val artistList: ArrayList<ArtistsForm> = (status.value as Status.Success).data!!
        status.value = Status.Loading
        runCatching { withContext(Dispatchers.IO) { apiServerRepository.deleteArtist(artist.name, email) } }
            .onSuccess {
                artistList.remove(artist)
                PreferenceRepositoryImp.setFavorite(artistList.size)
                viewModelScope.launch(Dispatchers.IO) {
                    artistsRepository.deleteArtist(artist.name)
                }
                status.value = Status.Success(artistList) }
            .onFailure { status.value = Status.Failure(it) }
    }
}
