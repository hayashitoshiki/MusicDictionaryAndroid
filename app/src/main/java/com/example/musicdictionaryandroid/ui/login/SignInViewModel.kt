package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ログイン画面_UIロジック
 */
class SignInViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<Boolean>>()
    val emailText = MutableLiveData<String>("")
    val passwordText = MutableLiveData<String>("")
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    /**
     * ボタンバリデート
     */
    init {
        _isEnableSubmitButton.addSource(emailText) { validateSubmit() }
        _isEnableSubmitButton.addSource(passwordText) { validateSubmit() }
    }

    // バリデート判定
    private fun validateSubmit() {
        _isEnableSubmitButton.value = validateEmail() && validatePassword()
    }

    // email入力欄バリデート
    private fun validateEmail(): Boolean {
        return emailText.value != null && emailText.value!!.length > 5
    }

    // password入力欄バリデート
    private fun validatePassword(): Boolean {
        return passwordText.value != null && passwordText.value!!.length > 5
    }

    /**
     * ログイン処理
     */
    fun signIn(): Job = viewModelScope.launch {
        status.value = Status.Loading
        userUseCase.signIn(emailText.value!!, passwordText.value!!,
            { status.postValue(Status.Success(true)) },
            { if (it != null) status.postValue(Status.Failure(it))
            else status.postValue(Status.Success(false)) }
        )
    }
}
