package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status

/**
 * ログイン・新規登録画面 BaseActivity_UIロジック
 *
 */
class StartViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    val status = MutableLiveData<Status<*>>()

    /**
     * ログインチェック
     *
     */
    fun firstCheck() {
        status.value = Status.Loading
        userUseCase.firstCheck({
            status.value = Status.Success("")
        }, {
            // status.value = Status.Failure("")
           // view.showErrorNetwork()
        })
    }
}
