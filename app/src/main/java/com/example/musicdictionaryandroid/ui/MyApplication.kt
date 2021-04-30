package com.example.musicdictionaryandroid.ui

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.musicdictionaryandroid.data.local.database.AppDatabase
import com.example.musicdictionaryandroid.data.local.preferences.UserSharedPreferences
import com.example.musicdictionaryandroid.data.local.preferences.UserSharedPreferencesImp
import com.example.musicdictionaryandroid.data.repository.*
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCaseImp
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCaseImp
import com.example.musicdictionaryandroid.ui.home.HomeViewModel
import com.example.musicdictionaryandroid.ui.home.ResultRecommendViewModel
import com.example.musicdictionaryandroid.ui.home.ResultSoaringViewModel
import com.example.musicdictionaryandroid.ui.home.ResultViewModel
import com.example.musicdictionaryandroid.ui.login.SignInViewModel
import com.example.musicdictionaryandroid.ui.login.SignUpViewModel
import com.example.musicdictionaryandroid.ui.login.StartViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageArtistAddViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageArtistViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageTopViewModel
import com.example.musicdictionaryandroid.ui.mypage.MyPageUserViewModel
import com.example.musicdictionaryandroid.ui.util.UserInfoChangeListUtil
import com.example.musicdictionaryandroid.ui.util.UserInfoChangeListUtilImp
import kotlinx.coroutines.MainScope
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
        lateinit var shered: MyApplication

        @Suppress("JAVA_CLASS_ON_COMPANION")
        val TAG = javaClass.name
    }

    // Global Scope
    private val applicationScope = MainScope()

    init {
        shered = this
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

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
        viewModel { MainActivityViewModel(get()) }

        viewModel { MyPageTopViewModel(get()) }
        viewModel { MyPageUserViewModel(get(), get()) }
        viewModel { MyPageArtistAddViewModel(get()) }
        viewModel { MyPageArtistViewModel(get()) }
        viewModel { SignInViewModel(get(), applicationScope) }
        viewModel { SignUpViewModel(get(), get(), applicationScope) }
        viewModel { ResultViewModel(get()) }
        viewModel { ResultRecommendViewModel(get()) }
        viewModel { ResultSoaringViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { StartViewModel(get()) }
        viewModel { SplashViewModel(get()) }

        factory<ArtistUseCase> { ArtistUseCaseImp(get(), get(), get(), applicationScope) }
        factory<UserUseCase> { UserUseCaseImp(get(), get(), get(), applicationScope) }

        factory<RemoteArtistRepository> { RemoteArtistRepositoryImp() }
        factory<RemoteUserRepository> { RemoteUserRepositoryImp() }
        factory<LocalArtistRepository> { LocalArtistRepositoryImp() }
        factory<LocalUserRepository> { LocalUserRepositoryImp(get()) }

        factory<UserSharedPreferences> { UserSharedPreferencesImp(get()) }

        single<UserInfoChangeListUtil> { UserInfoChangeListUtilImp }
    }
}
