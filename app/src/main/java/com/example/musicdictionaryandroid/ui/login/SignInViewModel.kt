package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status
import java.util.regex.Pattern
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * ログイン画面_UIロジック
 */
class SignInViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<Boolean>>()

    val emailText = MutableLiveData("")
    val passwordText = MutableLiveData("")
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
    fun signIn(): Job = GlobalScope.launch {
        status.postValue(Status.Loading)
        userUseCase.signIn(emailText.value!!, passwordText.value!!,
            { status.postValue(Status.Success(true)) },
            {
                if (it != null) status.postValue(Status.Failure(it))
                else status.postValue(Status.Success(false))
            }
        )
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
