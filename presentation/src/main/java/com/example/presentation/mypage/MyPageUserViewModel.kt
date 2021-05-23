package com.example.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.UserUseCase
import com.example.presentation.util.MessageUtil

/**
 * ユーザー情報画面_UIロジック
 */
class MyPageUserViewModel(
    userUseCase: UserUseCase,
    messageUtil: MessageUtil
) : ViewModel() {

    // ユーザ情報
    private val _emailText = MutableLiveData<String>()
    val emailText: LiveData<String> = _emailText
    private val _nameText = MutableLiveData<String>()
    val nameText: LiveData<String> = _nameText
    private val _genderText = MutableLiveData<String>()
    val genderText: LiveData<String> = _genderText
    private val _areaText = MutableLiveData<String>()
    val areaText: LiveData<String> = _areaText
    private val _birthdayText = MutableLiveData<String>()
    val birthdayText: LiveData<String> = _birthdayText
    private val _favoriteText = MutableLiveData<String>()
    val favoriteText: LiveData<String> = _favoriteText

    init {
        val user = userUseCase.getUserByCache()
        _emailText.value = user.email
        _nameText.value = user.name
        _genderText.value = messageUtil.getGender(user.gender)
        _areaText.value = messageUtil.getArea(user.area)
        _birthdayText.value = user.birthday
        _favoriteText.value = user.artistCount.toString()
    }
}
