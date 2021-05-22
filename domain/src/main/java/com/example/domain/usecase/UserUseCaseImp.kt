package com.example.domain.usecase

import com.example.domain.model.value.Result
import com.example.domain.model.entity.User
import com.example.domain.repository.LocalArtistRepository
import com.example.domain.repository.LocalBookmarkArtistRepository
import com.example.domain.repository.LocalUserRepository
import com.example.domain.repository.RemoteUserRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class UserUseCaseImp(
    private val remoteUserRepository: RemoteUserRepository,
    private val localUserRepository: LocalUserRepository,
    private val localArtistRepository: LocalArtistRepository,
    private val localBookmarkArtistRepository: LocalBookmarkArtistRepository,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : UserUseCase {

    // ユーザー情報取得
    override fun getUserByCache(): User {
        return localUserRepository.getUser()
    }

    // ユーザー登録
    override suspend fun createUser(email: String, password: String, user: User): Flow<Result<String>> = flow {
        remoteUserRepository.signUp(email, password).collect { firebaseResult ->
            when (firebaseResult) {
                is Result.Success -> {
                    val json: String = Moshi.Builder().build().adapter(User::class.java).toJson(user)
                    when (val apiResult = remoteUserRepository.createUser(json)) {
                        is Result.Success -> {
                            localUserRepository.setUser(user)
                            emit(firebaseResult)
                        }
                        is Result.Error -> emit(apiResult)
                    }
                }
                is Result.Error -> emit(firebaseResult)
            }
        }
    }

    // ユーザー削除
    override fun delete(): Flow<Result<String>> {
        return remoteUserRepository.delete()
    }

    // ログイン状態チェック
    override fun firstCheck(): Boolean {
        return remoteUserRepository.firstCheck()
    }

    // ログイン
    override suspend fun signIn(email: String, password: String): Flow<Result<String>> = flow {
        remoteUserRepository.signIn(email, password).collect {
            when (it) {
                is Result.Success -> {
                    when (val result = remoteUserRepository.getUserByEmail(email)) {
                        is Result.Success -> {
                            localUserRepository.setUser(result.data)
                            emit(it)
                        }
                        is Result.Error -> {
                            remoteUserRepository.signOut()
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
        remoteUserRepository.signOut()
        localUserRepository.removeAll()
        localArtistRepository.deleteAll()
        localBookmarkArtistRepository.deleteAll()
    }
}
