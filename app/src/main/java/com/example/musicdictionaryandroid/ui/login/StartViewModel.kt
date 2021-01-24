package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status

/**
 * ログイン・新規登録画面 BaseActivity_UIロジック
 *
 */
class StartViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _status = MutableLiveData<Status<Boolean>>()
    val status: LiveData<Status<Boolean>> = _status

    private val _isEnableRadioButton = MutableLiveData<Boolean>(true)
    val isEnableRadioButton: LiveData<Boolean> = _isEnableRadioButton

    /**
     * ログインチェック
     *
     */
    fun firstCheck() {
        _status.value = Status.Loading
        if (userUseCase.firstCheck()) {
           _status.postValue(Status.Success(true))
        } else {
            _status.postValue(Status.Success(false))
        }
    }

    // ラジオボタン活性
    fun enableRadioButton() {
        _isEnableRadioButton.value = true
    }

    // ラジオボタン非活性
    fun unEnableRadioButton() {
        _isEnableRadioButton.value = false
    }
}
