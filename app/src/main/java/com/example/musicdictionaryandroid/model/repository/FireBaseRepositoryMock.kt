package com.example.musicdictionaryandroid.model.repository

import android.util.Log
import java.lang.Exception

class FireBaseRepositoryMock : FireBaseRepository {

    companion object {
        private const val TAG = "FireBaseRepositoryImp"
    }

    // ユーザーのEmailを取得
    override fun getEmail(): String {
        return "toshikihayashi4004@ezweb.ne.jp"
    }

    // 自動ログイン認証
    override fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit) {
        onSuccess()
    }

    // ログイン機能
    override fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        Log.d(TAG, "ログイン成功")
        onSuccess()
    }

    // ログアウト
    override fun signOut(onSuccess: () -> Unit, onError: () -> Unit) {
        onSuccess()
    }

    // アカウント作成
    override fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        Log.d(TAG, "アカウント作成成功")
        onSuccess()
    }

    // ユーザー削除
    override fun delete(onSuccess: () -> Unit, onError: (error: Exception?) -> Unit) {
        Log.d(TAG, "ユーザーを削除しました")
        onSuccess()
    }
}
