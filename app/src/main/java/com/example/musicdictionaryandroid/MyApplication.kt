package com.example.musicdictionaryandroid

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.data.local.preferences.UserSharedPreferences
import com.example.data.repository.*
import com.example.domain.model.entity.Artist
import com.example.domain.repository.LocalBookmarkArtistRepository
import com.example.domain.repository.LocalUserRepository
import com.example.domain.usecase.ArtistUseCase
import com.example.domain.usecase.ArtistUseCaseImp
import com.example.domain.usecase.UserUseCase
import com.example.domain.usecase.UserUseCaseImp
import com.example.musicdictionaryandroid.database.AppDatabase
import com.example.musicdictionaryandroid.preferences.UserSharedPreferencesImp
import com.example.presentation.MainActivityViewModel
import com.example.presentation.SplashViewModel
import com.example.presentation.home.*
import com.example.presentation.login.SignInViewModel
import com.example.presentation.login.SignUpViewModel
import com.example.presentation.login.StartViewModel
import com.example.presentation.mypage.*
import com.example.presentation.util.MessageUtil
import kotlinx.coroutines.MainScope
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
        lateinit var shared: MyApplication

        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val TAG = javaClass.name
    }

    // Global Scope
    private val applicationScope = MainScope()

    init {
        shared = this
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
        viewModel { (messageUtil: MessageUtil) -> MyPageUserViewModel(get(), messageUtil) }
        viewModel { (artist: Artist?, messageUtil: MessageUtil) -> MyPageArtistAddViewModel(artist, get(), messageUtil) }
        viewModel { MyPageArtistViewModel(get()) }
        viewModel { BookmarkArtistListViewModel(get()) }
        viewModel { SignInViewModel(get(), applicationScope) }
        viewModel { (messageUtil: MessageUtil) -> SignUpViewModel(messageUtil, get(), applicationScope) }
        viewModel { ResultViewModel(get()) }
        viewModel { ResultAdapterViewModel(get()) }
        viewModel { ResultRecommendViewModel(get()) }
        viewModel { ResultSoaringViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { StartViewModel(get()) }
        viewModel { SplashViewModel(get()) }

        factory<ArtistUseCase> { ArtistUseCaseImp(get(), get(), get(), get(), applicationScope) }
        factory<UserUseCase> { UserUseCaseImp(get(), get(), get(), get()) }

        factory<com.example.domain.repository.RemoteArtistRepository> { RemoteArtistRepositoryImp() }
        factory<com.example.domain.repository.RemoteUserRepository> { RemoteUserRepositoryImp() }
        factory<com.example.domain.repository.LocalArtistRepository> { LocalArtistRepositoryImp(database.artistDao()) }
        factory<LocalBookmarkArtistRepository> { LocalBookmarkArtistRepositoryImp(database.bookmarkArtistDao()) }
        factory<LocalUserRepository> { LocalUserRepositoryImp(get()) }

        factory<UserSharedPreferences> { UserSharedPreferencesImp() }
    }
}
