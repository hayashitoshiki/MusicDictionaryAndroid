package com.example.musicdictionaryandroid.ui

import android.app.Application
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.ui.home.HomeViewModel
import com.example.musicdictionaryandroid.ui.home.ResultRecommendViewModel
import com.example.musicdictionaryandroid.ui.home.ResultSoaringViewModel
import com.example.musicdictionaryandroid.ui.home.ResultViewModel
import com.example.musicdictionaryandroid.ui.login.SignInViewModel
import com.example.musicdictionaryandroid.ui.login.SignUpViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageArtistAddViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageArtistViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

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

        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }

    // Koinモジュール
    private val module: Module = module {

        viewModel { MyPageArtistAddViewModel(get(), get()) }
        viewModel { MyPageArtistViewModel(get(), get()) }
        viewModel { SignInViewModel(get(), get()) }
        viewModel { SignUpViewModel(get(), get()) }
        viewModel { ResultViewModel(get()) }
        viewModel { ResultRecommendViewModel(get(), get()) }
        viewModel { ResultSoaringViewModel(get()) }
        viewModel { HomeViewModel(get()) }

        factory <FireBaseRepository> { FireBaseRepositoryImp() }
        factory <ApiServerRepository> { ApiServerRepositoryImp() }
        factory <UserRepository> { UserRepositoryImp() }
        factory <PreferenceRepositoryImp> { PreferenceRepositoryImp }
    }

}