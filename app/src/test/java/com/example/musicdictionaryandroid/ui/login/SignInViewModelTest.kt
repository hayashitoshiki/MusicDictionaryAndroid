package com.example.musicdictionaryandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.nhaarman.mockito_kotlin.mock
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import retrofit2.Response

/**
 * ログイン画面
 */

class SignInViewModelTest {

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
     * 条件：Emailが６文字以上、passwordが６文字以上でなければfalseを返す
     * 期待結果：バリデーション条件を満たさない場合false、満たす場合trueが帰る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidate() {
        // テストクラス作成
        val userUseCase = mockk<UserUseCase> ().also {
            coEvery { it.getEmail() } returns "responseUser"
        }
        val viewModel = SignInViewModel(userUseCase)
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)

        // 実行
        // 初期状態
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email５文字、Password６文字
        viewModel.emailText.value = "12345"
        viewModel.passwordText.value = "123456"
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email６文字、Password５文字
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "12345"
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // Email６文字、Password６文字
        viewModel.emailText.value = "123456"
        viewModel.passwordText.value = "123456"
        assertEquals(viewModel.isEnableSubmitButton.value!!, true)
    }
}
