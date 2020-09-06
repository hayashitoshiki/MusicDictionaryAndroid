package com.example.musicdictionaryandroid.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepositoryImp
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import com.example.musicdictionaryandroid.model.repository.UserRepositoryImp
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModel() {

    private val fireBaseRepository: FireBaseRepository = FireBaseRepositoryImp()

    val status = MutableLiveData<Status<String?>>()
    val emailText = MutableLiveData<String>()
    val passwordText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    val genderInt = MutableLiveData<Int>(0)
    val areaSelectedPosition = MutableLiveData<Int>(0)
    val birthdaySelectedPosition = MutableLiveData<Int>(0)


    //新規作成
    fun signUp() {
        status.value = Status.Loading
        when {
            emailText.value.isNullOrEmpty() -> {
                Log.d("TAG","emailText is null error")
                status.value = Status.Success("error1")
                return
            }
            passwordText.value.isNullOrEmpty() -> {
                Log.d("TAG","passwordText is null error")
                status.value = Status.Success("error2")
                return
            }
            nameText.value.isNullOrEmpty() -> {
                Log.d("TAG","nameText is null error")
                status.value = Status.Success("error3")
                return
            }
            genderInt.value == 0 -> {
                Log.d("TAG","genderInt is null error")
                status.value = Status.Success("error4")
                return
            }
            areaSelectedPosition.value == 0 -> {
                Log.d("TAG","areaSelectedPosition is null error")
                status.value = Status.Success("error5")
                return
            }
            birthdaySelectedPosition.value == 0 -> {
                Log.d("TAG","birthdaySelectedPosition is null error")
                status.value = Status.Success("error6")
                return
            }
            else -> {
                val birthday = UserInfoChangeListUtil.getBirthday(birthdaySelectedPosition.value!!)
                val user = User(emailText.value!!, nameText.value!!, genderInt.value!!, areaSelectedPosition.value!!, birthday,0)
                val json: String = Gson().toJson(user)
                Log.d("TAG","respons:" + user)
                fireBaseRepository.signUp(emailText.value!!, passwordText.value!!, {
                    // APIから情報取得
                    viewModelScope.launch {
                        runCatching { withContext(Dispatchers.IO) { UserRepositoryImp().createUser(json) } }
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
        }
    }

    // genderの変更
    fun checkedChangeGender(checkedId: Int) {
        genderInt.value = checkedId
    }

}