package com.example.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.presentation.R
import com.example.presentation.databinding.ActivityStartBinding
import com.example.presentation.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * ログイン・新規登録画面 BaseActivity
 */
class StartActivity : AppCompatActivity() {

    private val viewModel: StartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStartBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.status.observe(this, { onStateChanged(it) })
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
        val intent = Intent(application, com.example.presentation.MainActivity::class.java)
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
