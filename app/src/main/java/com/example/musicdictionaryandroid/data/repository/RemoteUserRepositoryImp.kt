package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.remote.network.Provider
import com.example.musicdictionaryandroid.data.remote.network.dto.UserDto
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class RemoteUserRepositoryImp(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : RemoteUserRepository {

    // region ユーザ情報

    // ユーザー取得
    override suspend fun getUserByEmail(email: String): Result<User> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.musicDictionaryApi().getUserByEmail(email) }.fold(
            onSuccess = { Result.Success(convertUserFromUserDto(it.user)) },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー登録
    override suspend fun createUser(user: String): Result<String> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.musicDictionaryApi().createUser(user) }.fold(
            onSuccess = { Result.Success(it.status.message) },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー情報変更
    override suspend fun changeUser(user: User, email: String): Result<String> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.musicDictionaryApi().changeUser(user, email) }.fold(
            onSuccess = { Result.Success(it.status.message) },
            onFailure = { Result.Error(it) }
        )
    }
    // endregion

    // region 認証

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // 自動ログイン認証
    override fun firstCheck(): Boolean {
        return auth.currentUser != null
    }

    // ログイン機能
    @ExperimentalCoroutinesApi
    override fun signIn(email: String, password: String): Flow<Result<String>> = callbackFlow {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> offer(Result.Success("Success"))
                task.isCanceled -> offer(Result.Error(IllegalAccessError("ログインキャンセル")))
                else -> {
                    task.exception?.let {
                        offer(Result.Error(it))
                    } ?: run {
                        offer(Result.Error(IllegalAccessError("ログイン失敗")))
                    }
                }
            }
        }
        awaitClose()
    }

    // ログアウト
    override fun signOut() {
        auth.signOut()
    }

    // アカウント作成
    @ExperimentalCoroutinesApi
    override fun signUp(email: String, password: String): Flow<Result<String>> = callbackFlow {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> offer(Result.Success("Success"))
                task.isCanceled -> offer(Result.Error(IllegalAccessError("アカウント作成キャンセル")))
                else -> {
                    task.exception?.let {
                        offer(Result.Error(it))
                    } ?: run {
                        offer(Result.Error(IllegalAccessError("アカウント作成失敗")))
                    }
                }
            }
        }
        awaitClose()
    }

    // アカウント削除
    @ExperimentalCoroutinesApi
    override fun delete(): Flow<Result<String>> = callbackFlow {
        auth.currentUser!!.delete().addOnCompleteListener { task ->
            when {
                task.isSuccessful -> offer(Result.Success("Success"))
                task.isCanceled -> offer(Result.Error(IllegalAccessError("アカウント削除キャンセル")))
                else -> {
                    task.exception?.let {
                        offer(Result.Error(it))
                    } ?: run {
                        offer(Result.Error(IllegalAccessError("アカウント削除失敗")))
                    }
                }
            }
        }
        awaitClose()
    }

    // endregion

    // region converter

    // ユーザDtoからユーザモデルへ変換
    private fun convertUserFromUserDto(userDto: UserDto): User {
        val email = userDto.email
        val name = userDto.name
        val gender = userDto.gender
        val area = userDto.area
        val birthday = userDto.birthday
        val artistCount = userDto.artist_count
        return User(email, name, gender, area, birthday, artistCount)
    }

    // endregion
}
