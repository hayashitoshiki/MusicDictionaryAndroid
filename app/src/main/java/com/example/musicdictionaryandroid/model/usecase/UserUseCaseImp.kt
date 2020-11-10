package com.example.musicdictionaryandroid.model.usecase

import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Result
import com.example.musicdictionaryandroid.model.util.UserInfoChangeListUtil
import com.google.gson.Gson
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val fireBaseRepository: FireBaseRepository,
    private val dataBaseRepository: DataBaseRepository
) : UserUseCase {

    // 登録したユーザーの情報取得
    override fun getUserByEmail(email: String): Response<User> {
        return apiRepository.getUserByEmail(email)
    }

    // ユーザー登録
    override suspend fun createUser(email: String, password: String, user: User, onSuccess: (result: CallBackData?) -> Unit, onError: (error: Exception?) -> Unit) {
        withContext(Dispatchers.IO) {
            fireBaseRepository.signUp(email, password, {
                try {
                    val json: String = Gson().toJson(user)
                    val result = apiRepository.createUser(json)
                    PreferenceRepositoryImp.setEmail(user.email)
                    PreferenceRepositoryImp.setName(user.name)
                    PreferenceRepositoryImp.setGender(user.gender)
                    PreferenceRepositoryImp.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                    PreferenceRepositoryImp.setArea(user.area)
                    PreferenceRepositoryImp.setFavorite(0)
                    onSuccess(result.body())
                } catch (e: Exception) { onError(e) }
            }, { onError(it) })
        }
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
    override fun delete(onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        fireBaseRepository.delete({ onSuccess() }, { onError(it) })
    }

    // ログイン状態チェック
    override fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit) {
        fireBaseRepository.firstCheck({ onSuccess() }, { onError() })
    }

    // ログイン
    override suspend fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        withContext(Dispatchers.IO) {
            fireBaseRepository.signIn(email, password, {
                try {
                val result = apiRepository.getUserByEmail(email)
                    result.body()?.let { user ->
                        PreferenceRepositoryImp.setEmail(user.email)
                        PreferenceRepositoryImp.setName(user.name)
                        PreferenceRepositoryImp.setGender(user.gender)
                        PreferenceRepositoryImp.setBirthday(UserInfoChangeListUtil.changeBirthdayString(user.birthday))
                        PreferenceRepositoryImp.setArea(user.area)
                        PreferenceRepositoryImp.setFavorite(user.artist_count)
                    }
                    onSuccess()
                } catch (e: Exception) { onError(e) }
            }, { onError(it) })
        }
    }

    // ログアウト
    override suspend fun signOut(onSuccess: () -> Unit, onError: () -> Unit) {
        withContext(Dispatchers.IO) {
            fireBaseRepository.signOut({
                PreferenceRepositoryImp.removeAll()
                dataBaseRepository.deleteAll()
                onSuccess()
            }, { onError() })
        }
    }

    // ユーザーのEmail取得
    override fun getEmail(): String {
        return fireBaseRepository.getEmail()
    }
}
