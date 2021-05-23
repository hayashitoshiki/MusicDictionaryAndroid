package com.example.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.login.StartActivity
import com.example.presentation.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * スプラッシュ画面
 */
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.status.observe(this, { onStateChanged(it) })
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
            is Status.Failure -> {
            }
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
