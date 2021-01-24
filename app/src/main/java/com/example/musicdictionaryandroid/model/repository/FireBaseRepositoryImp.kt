package com.example.musicdictionaryandroid.model.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

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
    override suspend fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        Log.d("TAG", "repository")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                when {
                    task.isSuccessful -> {
                        Log.d(TAG, "ログイン成功：" + task.isSuccessful)
                        onSuccess()
                    }
                    task.isCanceled -> {
                        Log.d(TAG, "ログインキャンセル：" + task.exception)
                    }
                    else -> {
                        Log.d(TAG, "ログイン失敗：" + task.exception)
                        onError(task.exception)
                    }
                }
            }
    }

    // ログアウト
    override fun signOut() {
        auth.signOut()
    }

    // アカウント作成
    override fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "アカウント作成成功")
                    onSuccess()
                } else {
                    Log.w(TAG, "アカウント作成失敗", task.exception)
                    onError(task.exception)
                }
            }
    }

    // ユーザー削除
    override fun delete(onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        auth.currentUser!!.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "ユーザーを削除しました")
                    onSuccess()
                } else {
                    Log.d(TAG, "予期せぬエラーが発生しました")
                    onError(task.exception)
                }
            }
    }
 }
