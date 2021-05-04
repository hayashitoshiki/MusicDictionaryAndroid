package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.remote.firebase.FireBaseService
import com.example.musicdictionaryandroid.data.remote.firebase.FireBaseServiceImp
import com.example.musicdictionaryandroid.data.remote.network.Provider
import com.example.musicdictionaryandroid.data.remote.network.ProviderImp
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.domain.model.value.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RemoteUserRepositoryImp(
    private val provider: Provider = ProviderImp,
    private val firebaseService: FireBaseService = FireBaseServiceImp(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteUserRepository {

    // region ユーザ情報

    // ユーザー取得
    override suspend fun getUserByEmail(email: String): Result<User> = withContext(ioDispatcher) {
        return@withContext runCatching { provider.musicDictionaryApi().getUserByEmail(email) }.fold(
            onSuccess = { Result.Success(Converter.userFromUserDto(it.user)) },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー登録
    override suspend fun createUser(user: String): Result<String> = withContext(ioDispatcher) {
        return@withContext runCatching { provider.musicDictionaryApi().createUser(user) }.fold(
            onSuccess = { Result.Success(it.status.message) },
            onFailure = { Result.Error(it) }
        )
    }

    // endregion

    // region 認証

    // 自動ログイン認証
    override fun firstCheck(): Boolean = firebaseService.firstCheck()

    // ログイン機能
    override fun signIn(email: String, password: String): Flow<Result<String>> = firebaseService.signIn(email, password)

    // ログアウト
    override fun signOut() {
        firebaseService.signOut()
    }

    // アカウント作成
    override fun signUp(email: String, password: String): Flow<Result<String>> = firebaseService.signUp(email, password)

    // アカウント削除
    override fun delete(): Flow<Result<String>> = firebaseService.delete()

    // endregion


}
