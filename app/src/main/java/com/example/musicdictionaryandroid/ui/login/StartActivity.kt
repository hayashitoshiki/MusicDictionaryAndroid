package com.example.musicdictionaryandroid.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.ActivityStartBinding
import com.example.musicdictionaryandroid.ui.MainActivity
import com.example.musicdictionaryandroid.ui.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * ログイン・新規登録画面 BaseActivity
 */
class StartActivity : AppCompatActivity() {

    companion object {
        const val TAG = "StartActivity"
    }

    private val viewModel: StartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStartBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        Log.d(TAG, "onCreate")

        viewModel.status.observe(this, Observer { onStateChanged(it) })
        viewModel.firstCheck()

        val signInView = SignInFragment.newInstance()
        val signUpView = SignUpFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, signInView)
            .commit()

        // 新規作成ボタン
        binding.signUpButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, signUpView)
                .commit()
        }

        // ログインボタン
        binding.signInButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, signInView)
                .commit()
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<Boolean>) {
        when (state) {
            is Status.Loading -> {
            }
            is Status.Success -> {
                if (state.data) {
                    startApp()
                }
            }
            is Status.Failure -> {
            }
        }
    }

    // 画面遷移
    fun startApp() {
        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // ロード中制御
    fun loading() {
        viewModel.unEnableRadioButton()
    }

    // ロード終了後制御
    fun endLoading() {
        viewModel.enableRadioButton()
    }

    // 入力エラーダイアログ
    fun showErrorEmailPassword() {
        Toast.makeText(applicationContext, getString(R.string.error_email_password), Toast.LENGTH_LONG).show()
    }
}
