package com.example.musicdictionaryandroid.ui

import android.app.Application
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    companion object {
        private lateinit var sInstance: MyApplication

        @JvmStatic
        @Synchronized
        fun getInstance(): MyApplication {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        PreferenceRepositoryImp.init(applicationContext)
    }

}