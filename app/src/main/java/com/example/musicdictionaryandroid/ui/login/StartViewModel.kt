package com.example.musicdictionaryandroid.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepositoryImp
import com.example.musicdictionaryandroid.model.util.Status


class StartViewModel: ViewModel() {

    private val fireBaseRepository: FireBaseRepository = FireBaseRepositoryImp()

    val status = MutableLiveData<Status<*>>()

    //ログインチェック
    fun firstCheck(){
        status.value = Status.Loading
        fireBaseRepository.firstCheck({
            status.value = Status.Success("")
        },{
            //status.value = Status.Failure("")
           // view.showErrorNetwork()
        })
    }

}