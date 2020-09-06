package com.example.musicdictionaryandroid.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.repository.FireBaseRepositoryImp
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import com.example.musicdictionaryandroid.model.util.Status

class MyPageTopViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val fireBaseRepository = FireBaseRepositoryImp()
    val authStatus = MutableLiveData<Status<*>>()

    //ログアウト
    fun signOut(){
        authStatus.value = Status.Loading
        fireBaseRepository.signOut({
            PreferenceRepositoryImp.removeAll()
            authStatus.value = Status.Success("success")
        },{
            authStatus.value = Status.Success("error")
        })
    }
}
