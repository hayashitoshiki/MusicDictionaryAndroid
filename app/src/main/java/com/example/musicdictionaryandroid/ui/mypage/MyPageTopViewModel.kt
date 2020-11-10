package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 設定画面_UIロジック
 *
 * @property dataBaseRepository
 * @property userUseCase
 */
class MyPageTopViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val authStatus = MutableLiveData<Status<*>>()

    /**
     * ログアウト
     *
     */
    fun signOut(): Job = viewModelScope.launch {
        authStatus.value = Status.Loading
        userUseCase.signOut(
            { authStatus.value = Status.Success("success") },
            { authStatus.value = Status.Success("error")
        })
    }
}
