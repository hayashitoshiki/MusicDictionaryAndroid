package com.example.musicdictionaryandroid.domain.usecase

import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.repository.ApiServerRepository
import com.example.musicdictionaryandroid.data.repository.DataBaseRepository
import com.example.musicdictionaryandroid.data.repository.FireBaseRepository
import com.example.musicdictionaryandroid.data.repository.PreferenceRepository
import com.example.musicdictionaryandroid.data.util.Result
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class UserUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val fireBaseRepository: FireBaseRepository,
    private val dataBaseRepository: DataBaseRepository,
    private val preferenceRepository: PreferenceRepository,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : UserUseCase {

    // ユーザー情報取得
    override fun getUserByCache(): User {
        return preferenceRepository.getUser()
    }

    // ユーザー登録
    override suspend fun createUser(email: String, password: String, user: User): Flow<Result<String>> = flow {
        fireBaseRepository.signUp(email, password).collect { firebaseResult ->
            when (firebaseResult) {
                is Result.Success -> {
                    val json: String = Moshi.Builder().build().adapter(User::class.java).toJson(user)
                    when (val apiResult = apiRepository.createUser(json)) {
                        is Result.Success -> {
                            preferenceRepository.setUser(user)
                            emit(firebaseResult)
                        }
                        is Result.Error -> emit(apiResult)
                    }
                }
                is Result.Error -> emit(firebaseResult)
            }
        }

    }

    // ユーザー情報変更
    override suspend fun changeUser(user: User, email: String): Result<CallBackData?> {
        return apiRepository.changeUser(user, email)
    }

    // ユーザー削除
    override fun delete(): Flow<Result<String>> {
        return fireBaseRepository.delete()
    }

    // ログイン状態チェック
    override fun firstCheck(): Boolean {
        return fireBaseRepository.firstCheck()
    }

    // ログイン
    override suspend fun signIn(email: String, password: String): Flow<Result<String>> = flow {
        fireBaseRepository.signIn(email, password).collect {
            when (it) {
                is Result.Success -> {
                    when (val result = apiRepository.getUserByEmail(email)) {
                        is Result.Success -> {
                            preferenceRepository.setUser(result.data)
                            emit(it)
                        }
                        is Result.Error -> {
                            fireBaseRepository.signOut()
                            emit(result)
                        }
                    }
                }
                is Result.Error -> emit(it)
            }
        }
    }

    // ログアウト
    override suspend fun signOut() {
        fireBaseRepository.signOut()
        preferenceRepository.removeAll()
        dataBaseRepository.deleteAll()
    }
}
