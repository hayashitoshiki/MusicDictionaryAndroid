package com.example.musicdictionaryandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.nhaarman.mockito_kotlin.mock
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
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
 * 新規登録画面
 */

class SignUpViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: SignUpViewModel

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        // テストクラス作成
        val userUseCase = mockk<UserUseCase>()
        val observer = mock<Observer<Boolean>>()
        val observerString = mock<Observer<String?>>()
        viewModel = SignUpViewModel(userUseCase, testScope)
        viewModel.isEnableSubmitButton.observeForever(observer)
        viewModel.emailText.observeForever(observerString)
        viewModel.emailErrorText.observeForever(observerString)
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
     * passwordError1Text： １つ目のエラー文言の有無
     * passwordError2Text： ２つ目のエラー文言の有無
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePassword() {
        // 初期状態
        viewModel.focusChangePassword1()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)
        // １つ目のみ入力(５文字)
        viewModel.password1Text.value = "12345"
        viewModel.focusChangePassword1()
        assertNotEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)
        // １つ目のみ入力(６文字)
        viewModel.password1Text.value = "123456"
        viewModel.focusChangePassword1()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)
        // 両方入力(６文字 && 不一致)
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "654321"
        viewModel.focusChangePassword1()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertNotEquals(viewModel.passwordError2Text.value, null)
        // 両方入力(６文字 && 一致)
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.focusChangePassword1()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)

        // 初期状態
        viewModel.password1Text.value = null
        viewModel.password2Text.value = null
        viewModel.focusChangePassword2()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)
        // ２つ目のみ入力(５文字)
        viewModel.password2Text.value = "12345"
        viewModel.focusChangePassword2()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertNotEquals(viewModel.passwordError2Text.value, null)
        // ２つ目のみ入力(６文字)
        viewModel.password2Text.value = "123456"
        viewModel.focusChangePassword2()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)
        // 両方入力(６文字 && 一致)
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "654321"
        viewModel.focusChangePassword2()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertNotEquals(viewModel.passwordError2Text.value, null)
        // 両方入力(６文字 && 一致)
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.focusChangePassword2()
        assertEquals(viewModel.passwordError1Text.value, null)
        assertEquals(viewModel.passwordError2Text.value, null)
    }

    /**
     * 名前入力欄のエラー文言制御
     * nameErrorText： エラー文言の有無
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeName() {
        // 初期状態
        viewModel.focusChangeName()
        assertEquals(viewModel.nameErrorText.value, null)
        // ５文字
        viewModel.nameText.value = "12345"
        viewModel.focusChangeName()
        assertNotEquals(viewModel.nameErrorText.value!!, null)
        // ６文字
        viewModel.nameText.value = "123456"
        viewModel.focusChangeName()
        assertEquals(viewModel.nameErrorText.value, null)
    }

    /**
     * バリデーションロジック
     *
     * 条件：１つずつバリデートをかけて最後に正常系を実施する
     * 期待結果：バリデーション条件を満たさない場合false、満たす場合trueが帰る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSignUp() {
        // 初期状態
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Emailが５文字
        viewModel.emailText.value = "12345"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // パスワードが５文字
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "12345"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 名前が５文字
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "12345"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 性別が未入力
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 0
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 地域が未入力
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 0
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 生年月日が未入力
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 0
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 全て入力済み(メールアドレス不正)
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 全て入力済み
        viewModel.emailText.value = "aaa@ezweb.ne.jp"
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, true)
    }
}
