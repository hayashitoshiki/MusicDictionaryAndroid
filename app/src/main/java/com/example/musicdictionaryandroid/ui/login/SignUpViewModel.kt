package com.example.musicdictionaryandroid.ui.login

import android.util.Log
import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val fireBaseRepository: FireBaseRepository,
    private val userRepository: UserRepository
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


    init {
        isButton.addSource(emailText){ validateSubmit() }
        isButton.addSource(passwordText){ validateSubmit() }
        isButton.addSource(nameText){ validateSubmit() }
        isButton.addSource(genderInt){ validateSubmit() }
        isButton.addSource(areaSelectedPosition){ validateSubmit() }
        isButton.addSource(birthdaySelectedPosition){ validateSubmit() }
    }

    // ボタンのバリデート
    private fun validateSubmit() {
        isButton.value = validateEmail() && validatePassword() && validateName() && validateGender() && validateArea() && validateBirthday()
    }

    // email入力欄
    private fun validateEmail() : Boolean {
        emailText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // password入力欄
    private fun validatePassword() : Boolean {
        passwordText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // 名前入力欄
    private fun validateName() : Boolean {
        nameText.value?.let {
            if (it.length > 5) {
                return true
            }
        }
        return false
    }

    // 性別入力欄
    private fun validateGender() : Boolean {
        genderInt.value?.let {
            if (it != 0) {
                return true
            }
        }
        return false
    }

    // 地域入力欄
    private fun validateArea() : Boolean {
        areaSelectedPosition.value?.let {
            if (it != 0) {
                return true
            }
        }
        return false
    }

    // 年齢入力欄
    private fun validateBirthday() : Boolean {
        birthdaySelectedPosition.value?.let {
            if (it != 0) {
                return true
            }
        }
        return false
    }

    // 新規作成
    fun signUp() {
        status.value = Status.Loading
        val birthday = UserInfoChangeListUtil.getBirthday(birthdaySelectedPosition.value!!)
        val user = User(emailText.value!!, nameText.value!!, genderInt.value!!, areaSelectedPosition.value!!, birthday,0)
        val json: String = Gson().toJson(user)
        fireBaseRepository.signUp(emailText.value!!, passwordText.value!!, {
            // APIから情報取得
            viewModelScope.launch {
                runCatching { withContext(Dispatchers.IO) { userRepository.createUser(json) } }
                    .onSuccess {
                        PreferenceRepositoryImp.setEmail(user.email)
                        PreferenceRepositoryImp.setName(user.name)
                        PreferenceRepositoryImp.setGender(user.gender)
                        PreferenceRepositoryImp.setBirthday(birthdaySelectedPosition.value!!)
                        PreferenceRepositoryImp.setArea(user.area)
                        PreferenceRepositoryImp.setFavorite(0)
                        status.value = Status.Success(it.body()?.status)
                    }
                    .onFailure { status.value = Status.Failure(it) }
            }
        }, {
            it?.let {
                status.value = Status.Failure(it)
            }
        })
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        genderInt.value = checkedId
    }

}