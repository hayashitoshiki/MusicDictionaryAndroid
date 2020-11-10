package com.example.musicdictionaryandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        if (email.isNotEmpty()) {
            status.value = Status.Loading
            runCatching { withContext(Dispatchers.IO) { artistUseCase.getArtistsByRecommend(email) } }
                .onSuccess { status.value = Status.Success(it.body()) }
                .onFailure { status.value = Status.Failure(it) }
        }
    }
}
