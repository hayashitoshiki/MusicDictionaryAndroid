package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil

class MyPageUserViewModel : ViewModel() {

    val emailText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    val genderText = MutableLiveData<String>()
    val areaText = MutableLiveData<String>()
    val birthdayText = MutableLiveData<String>()
    val favoriteText = MutableLiveData<String>()

    fun init() {
        emailText.value = PreferenceRepositoryImp.getEmail()
        nameText.value = PreferenceRepositoryImp.getName()
        genderText.value = UserInfoChangeListUtil.changeGender(PreferenceRepositoryImp.getGender())
        areaText.value = UserInfoChangeListUtil.changeArea(PreferenceRepositoryImp.getArea())
        birthdayText.value = UserInfoChangeListUtil.getBirthday(PreferenceRepositoryImp.getBirthday())
        favoriteText.value = PreferenceRepositoryImp.getFavorite().toString()
    }


}