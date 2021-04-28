package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.data.util.UserInfoChangeListUtil
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.regex.Pattern

/**
 * 新規登録画面_UIロジック
 *
 * @property userUseCase
 */
class SignUpViewModel(
    private val userUseCase: UserUseCase,
    private val externalScope: CoroutineScope
) : ViewModel() {

    val status = MutableLiveData<Status<String?>>()

    val emailText = MutableLiveData<String>()
    val password1Text = MutableLiveData<String>()
    val password2Text = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    private val _emailErrorText = MutableLiveData<String?>()
    val emailErrorText: LiveData<String?> = _emailErrorText
    private val _passwordError1Text = MutableLiveData<String?>()
    val passwordError1Text: LiveData<String?> = _passwordError1Text
    private val _passwordError2Text = MutableLiveData<String?>()
    val passwordError2Text: LiveData<String?> = _passwordError2Text
    private val _nameErrorText = MutableLiveData<String?>()
    val nameErrorText: LiveData<String?> = _nameErrorText
    val genderInt = MutableLiveData(0)
    val areaSelectedPosition = MutableLiveData(0)
    val birthdaySelectedPosition = MutableLiveData(0)
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton
    private val _isProgressBer = MutableLiveData(false)
    val isProgressBer: LiveData<Boolean> = _isProgressBer

    /**
     * バリデート処理
     */
    init {
        _isEnableSubmitButton.addSource(emailText) { validateSubmit() }
        _isEnableSubmitButton.addSource(password1Text) { validateSubmit() }
        _isEnableSubmitButton.addSource(password2Text) { validateSubmit() }
        _isEnableSubmitButton.addSource(nameText) { validateSubmit() }
        _isEnableSubmitButton.addSource(genderInt) { validateSubmit() }
        _isEnableSubmitButton.addSource(areaSelectedPosition) { validateSubmit() }
        _isEnableSubmitButton.addSource(birthdaySelectedPosition) { validateSubmit() }
        _isEnableSubmitButton.addSource(isProgressBer) { validateSubmit() }
    }

    // ボタンのバリデート
    private fun validateSubmit() {
        _isEnableSubmitButton.value =
            validateEmail() && validatePassword() && validateName() && validateGender() && validateArea() && validateBirthday() && validateProgressBar()
    }

    // email入力欄
    private fun validateEmail(): Boolean {
        emailText.value?.let { email ->
            return if (email.isNotEmpty() && email.length <= 5) {
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

    // password入力欄
    private fun validatePassword(): Boolean {
        return password1Text.value != null && password1Text.value!!.length > 5 &&
            password2Text.value != null && password1Text.value!! == password2Text.value!!
    }

    // 名前入力欄
    private fun validateName(): Boolean {
        return nameText.value != null && nameText.value!!.length > 5
    }

    // 性別入力欄
    private fun validateGender(): Boolean {
        return genderInt.value != null && genderInt.value!! != 0
    }

    // 地域入力欄
    private fun validateArea(): Boolean {
        return areaSelectedPosition.value != null && areaSelectedPosition.value!! != 0
    }

    // 年齢入力欄
    private fun validateBirthday(): Boolean {
        return birthdaySelectedPosition.value != null && birthdaySelectedPosition.value!! != 0
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
                val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
                val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                val matcher = pattern.matcher(email)
                if (!matcher.matches()) {
                    _emailErrorText.value = "メールアドレスを入力しでください"
                } else {
                    _emailErrorText.value = null
                }
            }
        }
    }

    // パスワードエラー文言表示
    fun focusChangePassword1() {
        if (password1Text.value != null && password1Text.value!!.isNotEmpty() && password1Text.value!!.length <= 5) {
            _passwordError1Text.value = "パスワードは最低でも６文字必要です"
        } else if (password1Text.value != null && password2Text.value != null && password1Text.value!! != password2Text.value!!) {
            _passwordError1Text.value = null
            _passwordError2Text.value = "パスワードが一致しません"
        } else {
            _passwordError1Text.value = null
            _passwordError2Text.value = null
        }
    }

    // 確認用パスワードエラー文言表示
    fun focusChangePassword2() {
        if (password2Text.value != null && password2Text.value!!.isNotEmpty() && password2Text.value!!.length <= 5) {
            _passwordError2Text.value = "パスワードは最低でも６文字必要です"
        } else if (password1Text.value != null && password2Text.value != null && password1Text.value!! != password2Text.value!!) {
            _passwordError2Text.value = "パスワードが一致しません"
        } else {
            _passwordError2Text.value = null
        }
    }

    // 名前エラー文言表示
    fun focusChangeName() {
        if (nameText.value != null && nameText.value!!.isNotEmpty() && nameText.value!!.length <= 5) {
            _nameErrorText.value = "名前は最低でも６文字必要です"
        } else {
            _nameErrorText.value = null
        }
    }

    /**
     * 新規作成
     */
    fun signUp(): Job = externalScope.launch {
        status.postValue(Status.Loading)
        val birthday = UserInfoChangeListUtil.getBirthday(birthdaySelectedPosition.value!!)
        val user = User(emailText.value!!, nameText.value!!, genderInt.value!!, areaSelectedPosition.value!!, birthday, 0)
        userUseCase.createUser(emailText.value!!, password1Text.value!!, user).collect {
            when (it) {
                is Result.Success -> status.postValue(Status.Success(it.data))
                is Result.Error -> status.postValue(Status.Failure(it.exception))
            }
        }
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        genderInt.value = checkedId
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
