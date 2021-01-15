package com.example.musicdictionaryandroid.model.usecase

import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import com.google.gson.Gson
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val fireBaseRepository: FireBaseRepository,
    private val dataBaseRepository: DataBaseRepository
) : UserUseCase {

    // ユーザー情報取得(SharedPreferences)
    override fun getUserByCache(): User {
       return User(
            PreferenceRepositoryImp.getEmail()!!,
            PreferenceRepositoryImp.getName()!!,
            PreferenceRepositoryImp.getGender(),
            PreferenceRepositoryImp.getArea(),
            UserInfoChangeListUtil.getBirthday(PreferenceRepositoryImp.getBirthday()),
            PreferenceRepositoryImp.getFavorite()
        )
    }

    // 登録したユーザーの情報取得
    override suspend fun getUserByEmail(email: String): Result<User?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.getUserByEmail(email)
                return@withContext Result.Success(result.body())
            } catch (e: Exception) { return@withContext Result.Error(e) }
        }
    }

    // ユーザー登録
    override suspend fun createUser(email: String, password: String, user: User, onSuccess: (result: CallBackData?) -> Unit, onError: (error: Throwable?) -> Unit) {
        fireBaseRepository.signUp(email, password, {
            val json: String = Gson().toJson(user)
            GlobalScope.launch(Dispatchers.IO) {
                runCatching { apiRepository.createUser(json) }
                    .onSuccess {
                        PreferenceRepositoryImp.setEmail(user.email)
                        PreferenceRepositoryImp.setName(user.name)
                        PreferenceRepositoryImp.setGender(user.gender)
                        PreferenceRepositoryImp.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                        PreferenceRepositoryImp.setArea(user.area)
                        PreferenceRepositoryImp.setFavorite(0)
                        onSuccess(it.body())
                    }
                    .onFailure { onError(it) }
            } },
            { onError(it) }
        )
    }

    // ユーザー情報変更
    override suspend fun changeUser(user: User, email: String): Result<CallBackData?> {
        return withContext(Dispatchers.IO) {
                try {
                    val result = apiRepository.changeUser(user, email)
                    return@withContext Result.Success(result.body())
                } catch (e: Exception) { return@withContext Result.Error(e) }
        }
    }

    // ユーザー削除
    override fun delete(onSuccess: () -> Unit, onError: (error: Throwable?) -> Unit) {
        fireBaseRepository.delete({ onSuccess() }, { onError(it) })
    }

    // ログイン状態チェック
    override fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit) {
        fireBaseRepository.firstCheck({ onSuccess() }, { onError() })
    }

    // ログイン
    override suspend fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Throwable?) -> Unit) {
            fireBaseRepository.signIn(email, password, {
                GlobalScope.launch(Dispatchers.IO) {
                    runCatching { apiRepository.getUserByEmail(email) }
                        .onSuccess {
                            it.body()?.let { user ->
                                PreferenceRepositoryImp.setEmail(user.email)
                                PreferenceRepositoryImp.setName(user.name)
                                PreferenceRepositoryImp.setGender(user.gender)
                                PreferenceRepositoryImp.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                                PreferenceRepositoryImp.setArea(user.area)
                                PreferenceRepositoryImp.setFavorite(user.artist_count)
                            }
                            onSuccess()
                        }
                        .onFailure { onError(it.cause) }
                }
            }, { onError(it) })
    }

    // ログアウト
    override suspend fun signOut() {
        withContext(Dispatchers.IO) {
            fireBaseRepository.signOut()
            PreferenceRepositoryImp.removeAll()
            dataBaseRepository.deleteAll()
        }
    }

    // ユーザーのEmail取得
    override fun getEmail(): String {
        return fireBaseRepository.getEmail()
    }
}
