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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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

class SignUpViewModelTest{

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val responseUser: Response<User> = Response.success<User>(User( "testEmail", "testName", 1, 1, "1999",1))

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
     * 新規館員登録（バリデート＋正常）
     * 条件：１つずつバリデートをかけて最後に新規登録する
     * 期待結果：各エラー、新規登録メソッドが走る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSignUp() = runBlocking {
        // テストクラス作成
        val firebaseRepository = mockk<FireBaseRepository>().also {
            every { it.signUp("testEmail", "aaa", any(),any()) } returns Unit
            every { it.getEmail() } returns "testEmail"
        }
        val userRepository = mockk<UserRepository> ().also {
            coEvery { it.getUserByEmail( "testEmail") } returns responseUser
        }
        val viewModel = SignUpViewModel(firebaseRepository, userRepository)

        // 実行
        viewModel.signUp()
        assertEquals(viewModel.status.value, Status.Success( "error1"))
        viewModel.emailText.value = "testEmail"
        viewModel.signUp()
        assertEquals(viewModel.status.value, Status.Success( "error2"))
        viewModel.passwordText.value = "aaa"
        viewModel.signUp()
        assertEquals(viewModel.status.value, Status.Success( "error3"))
        viewModel.nameText.value = "aaa"
        viewModel.signUp()
        assertEquals(viewModel.status.value, Status.Success( "error4"))
        viewModel.genderInt.value = 1
        viewModel.signUp()
        assertEquals(viewModel.status.value, Status.Success( "error5"))
        viewModel.areaSelectedPosition.value = 1
        viewModel.signUp()
        assertEquals(viewModel.status.value, Status.Success( "error6"))
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.signUp()
        coVerify{firebaseRepository.signUp("testEmail", "aaa", any(),any())}
    }
}