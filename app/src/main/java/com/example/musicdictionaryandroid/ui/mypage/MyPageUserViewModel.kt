package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil

/**
 * ユーザー情報画面_UIロジック
 */
class MyPageUserViewModel(
    private val userUseCase : UserUseCase
) : ViewModel() {

    val emailText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    val genderText = MutableLiveData<String>()
    val areaText = MutableLiveData<String>()
    val birthdayText = MutableLiveData<String>()
    val favoriteText = MutableLiveData<String>()

    fun init() {
        val user = userUseCase.getUserByCache()
        emailText.value = user.email
        nameText.value = user.name
        genderText.value = UserInfoChangeListUtil.changeGender(user.gender)
        areaText.value = UserInfoChangeListUtil.changeArea(user.area)
        birthdayText.value = user.birthday
        favoriteText.value = user.artist_count.toString()
    }
}
