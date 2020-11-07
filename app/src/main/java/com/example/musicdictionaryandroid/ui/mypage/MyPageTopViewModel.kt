package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.repository.ArtistsRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 設定画面_UIロジック
 *
 * @property artistsRepository
 * @property fireBaseRepository
 */
class MyPageTopViewModel(
    private val artistsRepository: ArtistsRepository,
    private val fireBaseRepository: FireBaseRepository
) : ViewModel() {

    val authStatus = MutableLiveData<Status<*>>()

    /**
     * ログアウト
     *
     */
    fun signOut() {
        authStatus.value = Status.Loading
        fireBaseRepository.signOut({
            PreferenceRepositoryImp.removeAll()
            viewModelScope.launch(Dispatchers.IO) {
                artistsRepository.deleteAll()
            }
            authStatus.value = Status.Success("success")
        }, {
            authStatus.value = Status.Success("error")
        })
    }
}
