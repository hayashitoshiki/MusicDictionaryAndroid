package com.example.musicdictionaryandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.nhaarman.mockito_kotlin.mock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.rules.TestRule

/**
 * ログイン画面
 */

class SignInViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: SignInViewModel

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        // テストクラス作成
        val userUseCase = mockk<UserUseCase> ().also {
            coEvery { it.getEmail() } returns "responseUser"
        }
        val observer = mock<Observer<Boolean>>()
        viewModel = SignInViewModel(userUseCase)
        viewModel.isEnableSubmitButton.observeForever(observer)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     * emailErrorText： エラー文言の有無
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmail() {
        // 初期状態
        viewModel.focusChangeEmail()
        assertEquals(viewModel.emailErrorText.value, null)
        // ５文字
        viewModel.emailText.value = "12345"
        viewModel.focusChangeEmail()
        assertNotEquals(viewModel.emailErrorText.value!!, null)
        // ６文字 && メールアドレス形式ではない
        viewModel.emailText.value = "123456"
        viewModel.focusChangeEmail()
        assertNotEquals(viewModel.emailErrorText.value!!, null)
        // ６文字以上 && メールアドレス形式
        viewModel.emailText.value = "abc@aaa.ne.jp"
        viewModel.focusChangeEmail()
        assertEquals(viewModel.emailErrorText.value, null)
    }

    /**
     * パスワード入力欄のエラー文言制御
     * passwordErrorText： エラー文言の有無
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePassword() {
        // 初期状態
        viewModel.focusChangePassword()
        assertEquals(viewModel.passwordErrorText.value, null)
        // ５文字
        viewModel.passwordText.value = "12345"
        viewModel.focusChangePassword()
        assertNotEquals(viewModel.passwordErrorText.value, null)
        // ６文字
        viewModel.passwordText.value = "123456"
        viewModel.focusChangePassword()
        assertEquals(viewModel.passwordErrorText.value, null)
    }

    /**
     * バリデーションロジック
     *
     * 条件：Emailが６文字以上、passwordが６文字以上でなければfalseを返す
     * 期待結果：バリデーション条件を満たさない場合false、満たす場合trueが帰る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidate() {
        // 初期状態
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email５文字、Password６文字
        viewModel.emailText.value = "12345"
        viewModel.passwordText.value = "12@4.6"
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email６文字、Password５文字
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "12345"
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email６文字、Password６文字(メール形式じゃない)
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email６文字、Password６文字(メール形式)
        viewModel.emailText.value = "aaa@ezweb.ne.jp"
        viewModel.passwordText.value = "123456"
        assertEquals(viewModel.isEnableSubmitButton.value!!, true)
    }
}
