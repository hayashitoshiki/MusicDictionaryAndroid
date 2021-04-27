package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * ログイン画面_UIロジック
 */
class SignInViewModel(
    private val userUseCase: UserUseCase,
    private val externalScope: CoroutineScope
) : ViewModel() {

    private val _status = MutableLiveData<Status<Boolean>>(Status.Non)
    val status: LiveData<Status<Boolean>> = _status

    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    private val _emailErrorText = MutableLiveData<String>()
    val emailErrorText: LiveData<String> = _emailErrorText
    private val _passwordErrorText = MutableLiveData<String>()
    val passwordErrorText: LiveData<String> = _passwordErrorText
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton
    private val _isProgressBer = MutableLiveData(false)
    val isProgressBer: LiveData<Boolean> = _isProgressBer

    /**
     * ボタンバリデート
     */
    init {
        _isEnableSubmitButton.addSource(emailText) { validateSubmit() }
        _isEnableSubmitButton.addSource(passwordText) { validateSubmit() }
        _isEnableSubmitButton.addSource(isProgressBer) { validateSubmit() }
    }

    // バリデート判定
    private fun validateSubmit() {
        _isEnableSubmitButton.value = validateEmail() && validatePassword() && validateProgressBar()
    }

    // email入力欄バリデート
    private fun validateEmail(): Boolean {
        emailText.value?.let { email ->
            return if (email.isEmpty() || email.length <= 5) {
                false
            } else {
                val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
                val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                val matcher = pattern.matcher(email)
                !(email.isNotEmpty() && !matcher.matches())
            }
        } ?: run {
            return false
        }
    }

    // password入力欄バリデート
    private fun validatePassword(): Boolean {
        return passwordText.value != null && passwordText.value!!.length > 5
    }

    // progressBarバリデート
    private fun validateProgressBar(): Boolean {
        return _isProgressBer.value != null && !_isProgressBer.value!!
    }

    // メールアドレスエラー文言表示
    fun focusChangeEmail() {
        emailText.value?.let { email ->
            if (email.isNotEmpty() && email.length <= 5) {
                _emailErrorText.value = "メールアドレスは最低でも６文字必要です"
            } else {
                val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
                val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                val matcher = pattern.matcher(email)
                if (email.isNotEmpty() && !matcher.matches()) {
                    _emailErrorText.value = "メールアドレスを入力しでください"
                } else {
                    _emailErrorText.value = null
                }
            }
        }
    }

    // パスワードエラー文言表示
    fun focusChangePassword() {
        if (passwordText.value != null && passwordText.value!!.isNotEmpty() && passwordText.value!!.length <= 5) {
            _passwordErrorText.value = "パスワードが６文字以下です"
        } else {
            _passwordErrorText.value = null
        }
    }

    /**
     * ログイン処理
     */
    fun signIn(): Job = externalScope.launch {
        _status.postValue(Status.Loading)
        userUseCase.signIn(emailText.value!!, passwordText.value!!).collect {
            when (it) {
                is Result.Success -> _status.postValue(Status.Success(true))
                is Result.Error -> _status.postValue(Status.Failure(it.exception))
            }
        }
    }

    // プログレスバー表示
    fun showProgressBer() {
        _isProgressBer.value = true
    }

    // プログレスバー非表示
    fun hideProgressBer() {
        _isProgressBer.value = false
    }
}
