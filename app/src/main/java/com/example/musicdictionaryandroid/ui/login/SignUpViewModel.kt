package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 新規登録画面_UIロジック
 *
 * @property userUseCase
 */
class SignUpViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val status = MutableLiveData<Status<String?>>()
    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    val genderInt = MutableLiveData<Int>(0)
    val areaSelectedPosition = MutableLiveData<Int>(0)
    val birthdaySelectedPosition = MutableLiveData<Int>(0)
    private val _isEnableSubmitButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean> = _isEnableSubmitButton

    /**
     * バリデート処理
     */
    init {
        _isEnableSubmitButton.addSource(emailText) { validateSubmit() }
        _isEnableSubmitButton.addSource(passwordText) { validateSubmit() }
        _isEnableSubmitButton.addSource(nameText) { validateSubmit() }
        _isEnableSubmitButton.addSource(genderInt) { validateSubmit() }
        _isEnableSubmitButton.addSource(areaSelectedPosition) { validateSubmit() }
        _isEnableSubmitButton.addSource(birthdaySelectedPosition) { validateSubmit() }
    }

    // ボタンのバリデート
    private fun validateSubmit() {
        _isEnableSubmitButton.value = validateEmail() && validatePassword() && validateName() && validateGender() && validateArea() && validateBirthday()
    }

    // email入力欄
    private fun validateEmail(): Boolean {
        return emailText.value != null && emailText.value!!.length > 5
    }

    // password入力欄
    private fun validatePassword(): Boolean {
        return passwordText.value != null && passwordText.value!!.length > 5
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

    /**
     * 新規作成
     */
    fun signUp(): Job = viewModelScope.launch {
        status.value = Status.Loading
        val birthday = UserInfoChangeListUtil.getBirthday(birthdaySelectedPosition.value!!)
        val user = User(emailText.value!!, nameText.value!!, genderInt.value!!, areaSelectedPosition.value!!, birthday, 0)
        userUseCase.createUser(emailText.value!!, passwordText.value!!, user,
            { status.postValue(Status.Success(it!!.status)) },
            { status.postValue(Status.Failure(it)) }
        )
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        genderInt.value = checkedId
    }
}
