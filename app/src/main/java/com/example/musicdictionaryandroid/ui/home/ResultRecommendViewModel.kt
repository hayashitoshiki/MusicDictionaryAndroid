package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * おすすめアーティスト検索結果画面_UIロジック
 *
 * @property userUseCase
 * @property artistUseCase
 */
class ResultRecommendViewModel(
    private val userUseCase: UserUseCase,
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<List<ArtistContents>>>()
    private val email: String = userUseCase.getEmail()

    /**
     * アーティスト検索
     *
     * @return おすすめアーティスト一覧
     */
    fun getRecommend(): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsByRecommend(email)) {
            is Result.Success -> {
                val artist = Artist("おすすめ", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
                val artistContents = ArtistContents(artist,null, null, 0, 0, 0, 0 ,0 ,0 ,0, 0)
                val arrayList = arrayListOf(artistContents)
                arrayList.addAll(result.data)
                status.postValue(Status.Success(arrayList))
            }
            is Result.Error -> { status.postValue(Status.Failure(result.exception)) }
        }
    }
}
