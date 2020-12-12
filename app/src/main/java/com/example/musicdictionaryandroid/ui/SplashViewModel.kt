package com.example.musicdictionaryandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.launch

class SplashViewModel (private val userUseCase: UserUseCase) : ViewModel() {

    val status = MutableLiveData<Status<*>>()

    /**
     * ログインチェック
     *
     */
    fun firstCheck() {
        viewModelScope.launch {
            status.value = Status.Loading
            userUseCase.firstCheck({
                status.value = Status.Success("login")
            }, {
                status.value = Status.Success("logout")
            })
        }
    }
}
