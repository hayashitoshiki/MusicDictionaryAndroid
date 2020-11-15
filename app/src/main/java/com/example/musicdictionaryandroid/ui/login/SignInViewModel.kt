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

    val status = MutableLiveData<Status<String?>>()
    val emailText = MutableLiveData<String>("")
    val passwordText = MutableLiveData<String>("")
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = isButton

    /**
     * ボタンバリデート
     */
    init {
        isButton.addSource(emailText) { validateSubmit() }
        isButton.addSource(passwordText) { validateSubmit() }
    }

    // バリデート判定
    private fun validateSubmit() {
        isButton.value = validateEmail() && validatePassword()
    }

    // email入力欄バリデート
    private fun validateEmail(): Boolean {
        emailText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // password入力欄バリデート
    private fun validatePassword(): Boolean {
        passwordText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    /**
     * ログイン処理
     */
    fun signIn(): Job = viewModelScope.launch {
        status.value = Status.Loading
        userUseCase.signIn(emailText.value!!, passwordText.value!!,
            { status.postValue(Status.Success("success")) },
            { status.postValue(Status.Failure(it!!)) }
        )
    }
}
