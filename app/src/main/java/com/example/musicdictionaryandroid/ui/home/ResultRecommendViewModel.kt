package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.Status
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

    val status = MutableLiveData<Status<ArrayList<ArtistsForm>?>>()
    private var email: String = ""

    init { email = userUseCase.getEmail() }

    /**
     * アーティスト検索
     *
     * @return おすすめアーティスト一覧
     */
    fun getRecommend(): Job = viewModelScope.launch {
        status.value = Status.Loading
        when (val result = artistUseCase.getArtistsByRecommend(email)) {
            is Result.Success -> {
                val artist = ArtistsForm("おすすめ")
                val arrayList = arrayListOf(artist)
                result.data?.let{arrayList.addAll(it)}
                status.postValue(Status.Success(arrayList)) }
            is Result.Error -> { status.postValue(Status.Failure(result.exception)) }
        }
    }
}
