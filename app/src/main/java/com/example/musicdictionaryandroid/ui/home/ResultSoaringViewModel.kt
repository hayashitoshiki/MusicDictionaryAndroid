package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 急上昇アーティスト一覧画面_UIロジック
 *
 * @property artistUseCase
 */
class ResultSoaringViewModel(
    private val artistUseCase: ArtistUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<List<ArtistContents>>>()

    /**
     * アーティスト検索
     *
     * @return 急上昇アーティスト一覧
     */
    fun getSoaring(): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsBySoaring()) {
            is Result.Success -> {
                val artist = Artist("急上昇", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
                val artistContents = ArtistContents(artist, null, null, 0, 0, 0, 0, 0, 0, 0, 0)
                val arrayList = arrayListOf(artistContents)
                arrayList.addAll(result.data)
                status.postValue(Status.Success(arrayList))
            }
            is Result.Error -> {
                status.postValue(Status.Failure(result.exception))
            }
        }
    }
}
