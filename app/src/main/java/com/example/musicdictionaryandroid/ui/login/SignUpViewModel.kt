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
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean>
        get() = isButton

    /**
     * バリデート処理
     */
    init {
        isButton.addSource(emailText) { validateSubmit() }
        isButton.addSource(passwordText) { validateSubmit() }
        isButton.addSource(nameText) { validateSubmit() }
        isButton.addSource(genderInt) { validateSubmit() }
        isButton.addSource(areaSelectedPosition) { validateSubmit() }
        isButton.addSource(birthdaySelectedPosition) { validateSubmit() }
    }

    // ボタンのバリデート
    private fun validateSubmit() {
        isButton.value = validateEmail() && validatePassword() && validateName() && validateGender() && validateArea() && validateBirthday()
    }

    // email入力欄
    private fun validateEmail(): Boolean {
        emailText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // password入力欄
    private fun validatePassword(): Boolean {
        passwordText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // 名前入力欄
    private fun validateName(): Boolean {
        nameText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // 性別入力欄
    private fun validateGender(): Boolean {
        genderInt.value?.let {
            if (it != 0) {
                return true
            }
        }
        return false
    }

    // 地域入力欄
    private fun validateArea(): Boolean {
        areaSelectedPosition.value?.let {
            if (it != 0) {
                return true
            }
        }
        return false
    }

    // 年齢入力欄
    private fun validateBirthday(): Boolean {
        birthdaySelectedPosition.value?.let {
            if (it != 0) {
                return true
            }
        }
        return false
    }

    /**
     * 新規作成
     */
    fun signUp(): Job = viewModelScope.launch {
        status.value = Status.Loading
        val birthday = UserInfoChangeListUtil.getBirthday(birthdaySelectedPosition.value!!)
        val user = User(emailText.value!!, nameText.value!!, genderInt.value!!, areaSelectedPosition.value!!, birthday, 0)
        userUseCase.createUser(emailText.value!!, passwordText.value!!, user,
            { status.value = Status.Success(it!!.status) },
            { it?.let { status.value = Status.Failure(it) } })
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        genderInt.value = checkedId
    }
}
