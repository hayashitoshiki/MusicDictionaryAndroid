package com.example.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * ログイン画面
 */
class SignInViewModelTest : BaseTestUnit() {

    // mock
    private lateinit var viewModel: com.example.presentation.login.SignInViewModel

    // data
    private val successEmail = "successEmail"
    private val successResult = Result.Success("Success")
    private val successStatus = Status.Success("Success")
    private val failureEmail = "failureEmail"
    private val failure = IllegalArgumentException("")
    private val failureResult = Result.Error(failure)
    private val failureStatus = Status.Failure(failure)

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        val userUseCase = mockk<com.example.domain.usecase.UserUseCase>().also() {
            coEvery { it.signIn(successEmail, any()) } returns flow { emit(successResult) }
            coEvery { it.signIn(failureEmail, any()) } returns flow { emit(failureResult) }
        }
        viewModel = com.example.presentation.login.SignInViewModel(userUseCase, testScope)
        viewModel.isEnableSubmitButton.observeForever(observerBoolean)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    // region メールアドレス入力欄のエラー文言制御

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件：初期表示
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailByInit() {
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertEquals(null, emailError)
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件：メールアドレス入力済み & ５文字
     * 結果：エラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailByEmail5() {
        viewModel.emailText.value = "12345"
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertNotEquals(null, emailError)
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件：メールアドレス入力済み & メールアドレスの形式ではない
     * 結果：エラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailByEmailAndNotEmail() {
        viewModel.emailText.value = "123456"
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertNotEquals(null, emailError)
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件：メールアドレス入力済み & メールアドレスの形式である
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailByEmailAndEmail() {
        viewModel.emailText.value = "abc@aaa.ne.jp"
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertEquals(null, emailError)
    }

    // endregion

    // region パスワード入力欄のエラー文言制御

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：初期状態
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByInit() {
        viewModel.focusChangePassword()
        val passwordError = viewModel.passwordErrorText.value
        assertEquals(null, passwordError)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：パスワード入力済み && ５文字
     * 結果：エラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByPassword5() {
        viewModel.passwordText.value = "12345"
        viewModel.focusChangePassword()
        val passwordError = viewModel.passwordErrorText.value
        assertNotEquals(null, passwordError)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：パスワード入力済み && ６文字
     * 結果：エラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByPassword6() {
        viewModel.passwordText.value = "123456"
        viewModel.focusChangePassword()
        val passwordError = viewModel.passwordErrorText.value
        assertEquals(null, passwordError)
    }

    // endregion

    // region 送信ボタンのバリデーション制御

    /**
     * バリデーションロジック
     *
     * 条件：初期状態
     * 結果：非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidateByInit() {
        val buttonValidate = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonValidate)
    }

    /**
     * バリデーションロジック
     *
     * 条件：メールアドレスが５文字
     * 結果：非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidateByEmail5() {
        viewModel.emailText.value = "12345"
        viewModel.passwordText.value = "12@4.6"
        val buttonValidate = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonValidate)
    }

    /**
     * バリデーションロジック
     *
     * 条件：パスワードが５文字
     * 結果：非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidateByPassword5() {
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "12345"
        val buttonValidate = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonValidate)
    }

    /**
     * バリデーションロジック
     *
     * 条件：メールアドレスがメール形式ではない
     * 結果：非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidateByEmailAndNotEmail() {
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        val buttonValidate = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonValidate)
    }

    /**
     * バリデーションロジック
     *
     * 条件：正常系
     * 結果：活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidateBySuccess() {
        viewModel.emailText.value = "aaa@ezweb.ne.jp"
        viewModel.passwordText.value = "123456"
        val buttonValidate = viewModel.isEnableSubmitButton.value!!
        assertEquals(true, buttonValidate)
    }

    // endregion

    // region ログイン処理

    /**
     * ログイン処理
     *
     * 条件：ログイン成功
     * 結果：ログインのステータスが成功になること
     */
    @Test
    fun signUpBySuccess() {
        viewModel.emailText.value = successEmail
        viewModel.passwordText.value = "123456"
        viewModel.signIn()
        val result = viewModel.status.value
        assertEquals(successStatus, result)
    }

    /**
     * ログイン処理
     *
     * 条件：ログイン失敗
     * 結果：ログインのステータスが失敗になること
     */
    @Test
    fun signUpByError() {
        viewModel.emailText.value = failureEmail
        viewModel.passwordText.value = "123456"
        viewModel.signIn()
        val result = viewModel.status.value
        assertEquals(failureStatus, result)
    }

    // endregion
}
