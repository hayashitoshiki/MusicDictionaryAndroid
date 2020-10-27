package com.example.musicdictionaryandroid.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicdictionaryandroid.ui.MainActivity
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.util.Status

class StartActivity : AppCompatActivity(){

    val viewModel: StartViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(StartViewModel::class.java)
    }

    private lateinit var signInView: SignInFragment
    private lateinit var signUpView: SignUpFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        signInView = SignInFragment.newInstance()
        signUpView = SignUpFragment.newInstance()

        viewModel.firstCheck()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment, signInView)
                .commit()
        viewModel.status.observe(this , Observer { onStateChanged(it) })
    }


    // ステータス監視
    private fun onStateChanged(state: Status<*>) = when (state) {
        is Status.Loading -> {  }
        is Status.Success -> { startApp() }
        is Status.Failure -> { }
    }

    //ログイン切り替えボタン
    fun signInButton(view: View){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, signInView)
                .commit()
    }

    //新規作成切り替えボタン
    fun signUpButton(view: View){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, signUpView)
                .commit()
    }

    //画面遷移
    fun startApp(){
        Toast.makeText(applicationContext, getString((R.string.success_login)), Toast.LENGTH_LONG).show()
        val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    // 入力エラーダイアログ
    fun showErrorEmailPassword(){
        Toast.makeText(applicationContext, getString(R.string.error_email_password), Toast.LENGTH_LONG).show()
    }
    //通信エラーダイアログ
    fun showErrorNetwork(){
        Toast.makeText(applicationContext, getString(R.string.error_network), Toast.LENGTH_LONG).show()
    }

}
