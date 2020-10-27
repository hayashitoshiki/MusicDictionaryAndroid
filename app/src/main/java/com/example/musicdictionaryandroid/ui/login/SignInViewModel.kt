package com.example.musicdictionaryandroid.ui.login

import android.util.Log
import androidx.lifecycle.*
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(
    private val fireBaseRepository: FireBaseRepository,
    private val userRepository: UserRepository
) : ViewModel() {


    val status = MutableLiveData<Status<String?>>()
    val emailText = MutableLiveData<String>("")
    val passwordText = MutableLiveData<String>("")
    private val isButton = MediatorLiveData<Boolean>()
    val isEnableSubmitButton: LiveData<Boolean>
        get() = isButton

    init {
        isButton.addSource(emailText){ validateSubmit() }
        isButton.addSource(passwordText){ validateSubmit() }
    }

    // ボタンのバリデート
    private fun validateSubmit() {
        isButton.value = validateEmail() && validatePassword()
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

    //ログイン
    fun signIn() {
        status.value = Status.Loading
        fireBaseRepository.signIn(emailText.value!!, passwordText.value!!, {
            // APIから情報取得
            viewModelScope.launch {
                runCatching { withContext(Dispatchers.IO) { userRepository.getUserByEmail(emailText.value!!) } }
                    .onSuccess {
                        Log.d("TAG","respons:" + it.body())
                        it.body()?.let { user ->
                            PreferenceRepositoryImp.setEmail(user.email)
                            PreferenceRepositoryImp.setName(user.name)
                            PreferenceRepositoryImp.setGender(user.gender)
                            PreferenceRepositoryImp.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                            PreferenceRepositoryImp.setArea(user.area)
                            PreferenceRepositoryImp.setFavorite(user.artist_count)
                        }
                        status.value = Status.Success("success")
                    }
                    .onFailure { status.value = Status.Failure(it) }
            }
        }, {
            it?.let {
                status.value = Status.Failure(it)
            }
        })
    }

}