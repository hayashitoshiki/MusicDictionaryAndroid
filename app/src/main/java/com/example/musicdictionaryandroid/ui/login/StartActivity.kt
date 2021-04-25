package com.example.musicdictionaryandroid.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.ActivityStartBinding
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.ui.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * ログイン・新規登録画面 BaseActivity
 */
class StartActivity : AppCompatActivity() {

    companion object {
        const val TAG = "StartActivity"
    }

    private val viewModel: StartViewModel by viewModel()

    private lateinit var signInView: SignInFragment
    private lateinit var signUpView: SignUpFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStartBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        Log.d(TAG, "onCreate")

        viewModel.status.observe(this, Observer { onStateChanged(it) })
        viewModel.firstCheck()

        signInView = SignInFragment.newInstance()
        signUpView = SignUpFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment, signInView)
            .commit()
    }

    // ステータス監視
    private fun onStateChanged(state: Status<Boolean>) {
        when (state) {
            is Status.Loading -> { }
            is Status.Success -> {
                if (state.data) {
                    startApp()
                }
            }
            is Status.Failure -> { }
        }
    }

    // ログイン切り替えボタン
    fun signInButton(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, signInView)
            .commit()
    }

    // 新規作成切り替えボタン
    fun signUpButton(view: View) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, signUpView)
            .commit()
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
