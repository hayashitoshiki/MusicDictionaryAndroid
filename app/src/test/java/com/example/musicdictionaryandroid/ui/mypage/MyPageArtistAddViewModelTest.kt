package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.model.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.model.usecase.UserUseCase
import com.nhaarman.mockito_kotlin.mock
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule

/**
 * アーティスト登録・更新画面
 */

class MyPageArtistAddViewModelTest {


    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

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
     * 入力バリデートロジック
     *
     * 条件：１つずつ入力していき全てのバリデートが通ること
     * 期待結果：５種類全部のバリデートが通ること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitError() {
        // テストクラス作成
        val artistUseCase = mockk<ArtistUseCase>()
        val userUseCase = mockk<UserUseCase> ().also {
            every { it.getEmail() } returns "test1"
        }
        val viewModel = MyPageArtistAddViewModel(userUseCase, artistUseCase)
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)

        // 実行
        // アーティスト名が空
        viewModel.changeArtistName("")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 性別が空
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(0)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 長さが空
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(0)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 声の高さが空
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(0)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 歌詞の言語が空
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(0)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 正常系
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, true)
    }
}
