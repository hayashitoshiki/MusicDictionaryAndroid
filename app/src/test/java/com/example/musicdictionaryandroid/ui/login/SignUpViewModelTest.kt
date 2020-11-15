package com.example.musicdictionaryandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.nhaarman.mockito_kotlin.mock
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

/**
 * 新規登録画面
 */

class SignUpViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val responseUser: Response<User> = Response.success<User>(User("testEmail", "testName", 1, 1, "1999", 1))

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // coroutines用
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
    }
    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    /**
     * バリデーションロジック
     *
     * 条件：１つずつバリデートをかけて最後に正常系を実施する
     * 期待結果：バリデーション条件を満たさない場合false、満たす場合trueが帰る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSignUp()  {
        // テストクラス作成
        val userUseCase = mockk<UserUseCase> ()
        val viewModel = SignUpViewModel(userUseCase)
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)

        // 実行
        // 初期状態
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Emailが５文字
        viewModel.emailText.value = "12345"
        viewModel.passwordText.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // パスワードが５文字
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "12345"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 名前が５文字
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        viewModel.nameText.value = "12345"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 性別が未入力
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 0
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 地域が未入力
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 0
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 生年月日が未入力
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 0
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 全て入力済み
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 1
        viewModel.areaSelectedPosition.value = 1
        viewModel.birthdaySelectedPosition.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, true)
    }
}
