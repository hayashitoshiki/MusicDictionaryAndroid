package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.repository.ApiServerRepository
import com.example.musicdictionaryandroid.model.repository.ArtistsRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * アーティスト情報登録・追加画面_UIロジック
 *
 * @property firebaseRepository
 * @property apiServerRepositoryImp
 * @property artistsRepository
 */
class MyPageArtistAddViewModel(
    private val firebaseRepository: FireBaseRepository,
    private val apiServerRepositoryImp: ApiServerRepository,
    private val artistsRepository: ArtistsRepository
) : ViewModel() {

    private var email = ""
    private var oldArtistName: String = ""
    val searchText = MutableLiveData<String>("")
    var artistForm = MutableLiveData<ArtistsForm>(ArtistsForm())
    val status = MutableLiveData<Status<CallBackData?>>()

    init {
        email = firebaseRepository.getEmail()
    }

    fun init(artist: ArtistsForm) {
        searchText.value = artist.name
        oldArtistName = artist.name
        artistForm.value = artist
    }

    /**
     * 送信処理
     */
    fun submit() {
        artistForm.value?.let { when {
            it.name == "" -> status.value = Status.Success(CallBackData("001"))
            it.gender == 0 -> status.value = Status.Success(CallBackData("002"))
            it.length == 0 -> status.value = Status.Success(CallBackData("003"))
            it.voice == 0 -> status.value = Status.Success(CallBackData("004"))
            it.lyrics == 0 -> status.value = Status.Success(CallBackData("005"))
            else -> { when (oldArtistName) {
                    "" -> addArtist(it)
                    else -> updateArtist(it)
            } }
        } }
    }

    // アーティスト登録
    private fun addArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        runCatching { withContext(Dispatchers.IO) { apiServerRepositoryImp.addArtist(artist, email) } }
            .onSuccess {
                viewModelScope.launch(Dispatchers.IO) {
                    artistsRepository.addArtist(artist)
                }
                status.value = Status.Success(it.body()) }
            .onFailure { status.value = Status.Failure(it) }
    }

    // アーティスト更新
    private fun updateArtist(artist: ArtistsForm): Job = viewModelScope.launch {
        runCatching { withContext(Dispatchers.IO) { apiServerRepositoryImp.updateArtist(artist, oldArtistName, email) } }
            .onSuccess {
                viewModelScope.launch(Dispatchers.IO) {
                    artistsRepository.deleteAll()
                    artistsRepository.updateArtist(oldArtistName, artist)
                }
                status.value = Status.Success(it.body()) }
            .onFailure { status.value = Status.Failure(it) }
    }

    // アーティスト名変更
    fun changeArtistName(name: String) {
        artistForm.value!!.name = name
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        artistForm.value!!.gender = checkedId
    }

    // lengthの変更
    fun checkedChangeLength(checkedId: Int) {
        artistForm.value!!.length = checkedId
    }

    // voiceの変更
    fun checkedChangeVoice(checkedId: Int) {
        artistForm.value!!.voice = checkedId
    }

    // 歌詞情報の変更
    fun checkedChangeLyric(checkedId: Int) {
        artistForm.value!!.lyrics = checkedId
    }
}
