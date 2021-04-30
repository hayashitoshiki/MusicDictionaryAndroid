package com.example.musicdictionaryandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import kotlinx.coroutines.launch

/**
 * スプラッシュ画面_UIロジック
 */
class SplashViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    val status = MutableLiveData<Status<*>>()

    // ログインチェック
    fun firstCheck() {
        viewModelScope.launch {
            status.value = Status.Loading
            if (userUseCase.firstCheck()) {
                status.value = Status.Success("login")
            } else {
                status.value = Status.Success("logout")
            }
        }
    }
}
