package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.ui.util.Status
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

    // ステータス
    private val _status = MutableLiveData<Status<Boolean>>(Status.Non)
    val status: LiveData<Status<Boolean>> = _status

    // 入力項目
    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    private val _emailErrorText = MutableLiveData<String?>()
    val emailErrorText: LiveData<String?> = _emailErrorText
    private val _passwordErrorText = MutableLiveData<String?>()
    val passwordErrorText: LiveData<String?> = _passwordErrorText

    // ボタン制御
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton
    private val _isProgressBar = MediatorLiveData<Boolean>()
    val isProgressBar: LiveData<Boolean> = _isProgressBar

    init {
        _isEnableSubmitButton.addSource(emailText) { validateSubmit() }
        _isEnableSubmitButton.addSource(passwordText) { validateSubmit() }
        _isEnableSubmitButton.addSource(isProgressBar) { validateSubmit() }
        _isProgressBar.addSource(_status, Observer { changeProgressBar(it) })
    }

    // バリデート判定
    private fun validateSubmit() {
        _isEnableSubmitButton.value = validateEmail() && validatePassword() && !isProgressBar.value!!
    }

    // email入力欄バリデート
    private fun validateEmail(): Boolean {
        emailText.value?.let { email ->
            return if (email.isEmpty() || email.length <= 5) {
                false
            } else {
                val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
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

    // プログレスバーの表示制御
    private fun changeProgressBar(status: Status<Boolean>) {
        _isProgressBar.value = status is Status.Loading
    }

    // メールアドレスエラー文言表示
    fun focusChangeEmail() {
        emailText.value?.let { email ->
            if (email.isNotEmpty() && email.length <= 5) {
                _emailErrorText.value = "メールアドレスは最低でも６文字必要です"
            } else {
                val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
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

    // ログイン処理
    fun signIn(): Job = externalScope.launch {
        _status.postValue(Status.Loading)
        userUseCase.signIn(emailText.value!!, passwordText.value!!).collect {
            when (it) {
                is Result.Success -> _status.postValue(Status.Success(true))
                is Result.Error -> _status.postValue(Status.Failure(it.exception))
            }
        }
    }
}
