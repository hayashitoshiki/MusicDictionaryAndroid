package com.example.musicdictionaryandroid.ui

import android.app.Application
import androidx.room.Room
import com.example.musicdictionaryandroid.model.dao.AppDatabase
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.ui.home.HomeViewModel
import com.example.musicdictionaryandroid.ui.home.ResultRecommendViewModel
import com.example.musicdictionaryandroid.ui.home.ResultSoaringViewModel
import com.example.musicdictionaryandroid.ui.home.ResultViewModel
import com.example.musicdictionaryandroid.ui.login.SignInViewModel
import com.example.musicdictionaryandroid.ui.login.SignUpViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageArtistAddViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageArtistViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageTopViewModel
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
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceRepositoryImp.init(applicationContext)

        startKoin {
            androidContext(applicationContext)
            modules(module)
        }

        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    // Koinモジュール
    private val module: Module = module {

        viewModel { MyPageTopViewModel(get(), get()) }
        viewModel { MyPageArtistAddViewModel(get(), get(), get()) }
        viewModel { MyPageArtistViewModel(get(), get(), get()) }
        viewModel { SignInViewModel(get(), get()) }
        viewModel { SignUpViewModel(get(), get()) }
        viewModel { ResultViewModel(get()) }
        viewModel { ResultRecommendViewModel(get(), get()) }
        viewModel { ResultSoaringViewModel(get()) }
        viewModel { HomeViewModel(get()) }

        factory <FireBaseRepository> { FireBaseRepositoryImp() }
        factory <ApiServerRepository> { ApiServerRepositoryImp() }
        factory <UserRepository> { UserRepositoryImp() }
        factory <ArtistsRepository> { ArtistsRepositoryImp() }
        factory <PreferenceRepositoryImp> { PreferenceRepositoryImp }
    }

}