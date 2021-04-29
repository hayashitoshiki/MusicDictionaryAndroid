package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
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

    val status = MutableLiveData<Status<List<ArtistSearchContents<*>>>>()

    /**
     * アーティスト検索
     *
     * @return おすすめアーティスト一覧
     */
    fun getRecommend(): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsByRecommend()) {
            is Result.Success -> {
                val artist = ArtistConditions("おすすめ", null, null, null, null, null, null)
                val conditions = ArtistSearchContents.Conditions(artist)
                val arrayList = arrayListOf<ArtistSearchContents<*>>(conditions)
                result.data.forEach { contents ->
                    arrayList.add(ArtistSearchContents.Item(contents))
                }
                status.postValue(Status.Success(arrayList))
            }
            is Result.Error -> {
                status.postValue(Status.Failure(result.exception))
            }
        }
    }
}
