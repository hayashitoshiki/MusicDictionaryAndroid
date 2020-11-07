package com.example.musicdictionaryandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.repository.UserRepository
import com.example.musicdictionaryandroid.model.util.Status
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
     * バリデート＋ログイン（正常系）
     * 条件：１つずつバリデートをかけて最後にログインする
     * 期待結果：パスワードのエラー、メールのエラー、ログインメソッドが走る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSignIn() = runBlocking {
        // テストクラス作成
        val firebaseRepository = mockk<FireBaseRepository>().also {
            every { it.signIn("testEmail", "aaa", any(), any()) } returns Unit
            every { it.getEmail() } returns "testEmail"
        }
        val userRepository = mockk<UserRepository> ().also {
            coEvery { it.getUserByEmail("testEmail") } returns responseUser
        }
        val viewModel = SignInViewModel(firebaseRepository, userRepository)

        // 実行
        viewModel.signIn()
        assertEquals(viewModel.status.value, Status.Success("error1"))
        viewModel.emailText.value = "testEmail"
        viewModel.signIn()
        assertEquals(viewModel.status.value, Status.Success("error2"))
        viewModel.passwordText.value = "aaa"
        viewModel.signIn()
        coVerify { firebaseRepository.signIn("testEmail", "aaa", any(), any()) }
    }
}
