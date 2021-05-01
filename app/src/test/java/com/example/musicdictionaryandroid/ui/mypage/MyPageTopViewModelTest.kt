package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * 設定画面（トップ）
 */
class MyPageTopViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: MyPageTopViewModel
    private lateinit var userUseCase: UserUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        userUseCase = mockk<UserUseCase>().also {
            coEvery { it.signOut() } returns Unit
        }
        viewModel = MyPageTopViewModel(userUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    // region ログアウト

    /**
     * ログアウト処理
     * 条件：なし
     * 結果：ユーザ機能のログアウト処理が呼ばれること
     */
    @Test
    fun signOut() {
        viewModel.signOut()
        coVerify(exactly = 1) { (userUseCase).signOut() }
    }

    // endregion
}