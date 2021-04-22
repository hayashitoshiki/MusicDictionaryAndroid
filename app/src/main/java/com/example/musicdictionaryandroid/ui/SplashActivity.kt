package com.example.musicdictionaryandroid.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.login.StartActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    @Suppress("JAVA_CLASS_ON_COMPANION")
    companion object {
        val TAG = javaClass.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        viewModel.status.observe(this, Observer { onStateChanged(it) })
        viewModel.firstCheck()
    }

    // ステータス監視
    private fun onStateChanged(state: Status<*>) {
        when (state) {
            is Status.Success -> {
                when (state.data) {
                    "login" -> home()
                    "logout" -> login()
                }
            }
            is Status.Failure -> { Log.i(TAG, "Failure:${state.throwable}") }
        }
    }

    // ホーム画面
    private fun home() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // ログイン画面
    private fun login() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        finish()
    }
}
