package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 設定画面_UIロジック
 */
class MyPageTopViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _authStatus = MutableLiveData<Status<*>>()
    val authStatus: LiveData<Status<*>> = _authStatus

    // ログアウト
    fun signOut(): Job = viewModelScope.launch {
        _authStatus.value = Status.Loading
        userUseCase.signOut()
        _authStatus.postValue(Status.Success(true))
    }
}
