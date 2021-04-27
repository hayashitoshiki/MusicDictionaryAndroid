package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.util.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FireBaseRepositoryImp : FireBaseRepository {

    @Suppress("JAVA_CLASS_ON_COMPANION")
    companion object {
        private val TAG = javaClass.name
    }

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // ユーザーのEmailを取得
    override fun getEmail(): String {
        return auth.currentUser!!.email!!
    }

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
}
