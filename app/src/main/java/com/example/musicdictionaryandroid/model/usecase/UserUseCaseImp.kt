package com.example.musicdictionaryandroid.model.usecase

import android.util.Log
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import com.squareup.moshi.Moshi
import java.lang.Exception
import kotlinx.coroutines.*

class UserUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val fireBaseRepository: FireBaseRepository,
    private val dataBaseRepository: DataBaseRepository,
    private val preferenceRepository: PreferenceRepository,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : UserUseCase {

    // ユーザー情報取得(SharedPreferences)
    override fun getUserByCache(): User {
        return User(
            preferenceRepository.getEmail()!!,
            preferenceRepository.getName()!!,
            preferenceRepository.getGender(),
            preferenceRepository.getArea(),
            UserInfoChangeListUtil.getBirthday(preferenceRepository.getBirthday()),
            preferenceRepository.getFavorite()
        )
    }

    // 登録したユーザーの情報取得
    override suspend fun getUserByEmail(email: String): Result<User?> {
        return apiRepository.getUserByEmail(email)
    }

    // ユーザー登録
    override suspend fun createUser(
        email: String,
        password: String,
        user: User,
        onSuccess: (result: CallBackData?) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        fireBaseRepository.signUp(email, password, {
                val json: String = Moshi.Builder().build().adapter(User::class.java).toJson(user)
                externalScope.launch(defaultDispatcher) {
                    when (val result = apiRepository.createUser(json)) {
                        is Result.Success -> {
                            preferenceRepository.setEmail(user.email)
                            preferenceRepository.setName(user.name)
                            preferenceRepository.setGender(user.gender)
                            preferenceRepository.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                            preferenceRepository.setArea(user.area)
                            preferenceRepository.setFavorite(0)
                            onSuccess(result.data)
                        }
                        is Result.Error -> onError(result.exception)
                    }
                }
            },
            { onError(throw Exception("firebase アカウント作成失敗")) }
        )
    }

    // ユーザー情報変更
    override suspend fun changeUser(user: User, email: String): Result<CallBackData?> {
        return apiRepository.changeUser(user, email)
    }

    // ユーザー削除
    override fun delete(onSuccess: () -> Unit, onError: (error: Throwable?) -> Unit) {
        fireBaseRepository.delete({ onSuccess() }, { onError(it) })
    }

    // ログイン状態チェック
    override fun firstCheck(): Boolean {
        return fireBaseRepository.firstCheck()
    }

    // ログイン
    override suspend fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (error: Throwable?) -> Unit
    ) {
        fireBaseRepository.signIn(email, password, {
            Log.d("TAG", "signIn　signIn")
            externalScope.launch(defaultDispatcher) {
                when (val result = apiRepository.getUserByEmail(email)){
                    is Result.Success -> {
                        Log.d("TAG", "API　ログイン成功")
                        result.data.let { user ->
                            preferenceRepository.setEmail(user.email)
                            preferenceRepository.setName(user.name)
                            preferenceRepository.setGender(user.gender)
                            preferenceRepository.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                            preferenceRepository.setArea(user.area)
                            preferenceRepository.setFavorite(user.artist_count)
                        }
                        onSuccess()
                    }
                    is Result.Error -> {
                        Log.d("TAG", "API　ログイン失敗")
                        fireBaseRepository.signOut()
                        onError(result.exception)
                    }
                }
            }
        }, { onError(it) }
        )
    }

    // ログアウト
    override suspend fun signOut() {
        fireBaseRepository.signOut()
        preferenceRepository.removeAll()
        dataBaseRepository.deleteAll()
    }

    // ユーザーのEmail取得
    override fun getEmail(): String {
        return fireBaseRepository.getEmail()
    }
}
