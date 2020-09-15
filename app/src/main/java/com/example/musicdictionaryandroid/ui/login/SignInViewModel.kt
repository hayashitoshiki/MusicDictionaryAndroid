package com.example.musicdictionaryandroid.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    //ログイン
    fun signIn() {
        status.value = Status.Loading

        when {
            emailText.value.isNullOrEmpty() -> {
                status.value = Status.Success("error1")
                return
            }
            passwordText.value.isNullOrEmpty() -> {
                status.value = Status.Success("error2")
                return
            }
            else -> {
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
    }

}