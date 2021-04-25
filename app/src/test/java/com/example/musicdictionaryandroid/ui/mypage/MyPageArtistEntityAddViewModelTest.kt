package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.nhaarman.mockito_kotlin.mock
import io.mockk.coEvery
import io.mockk.coVerify
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

class MyPageArtistEntityAddViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: MyPageArtistAddViewModel
    private lateinit var userUseCase: UserUseCase
    private lateinit var artistUseCase: ArtistUseCase
    private val array0 = arrayOf("")
    private val array1 = arrayOf("")
    private val array2 = arrayOf("")
    private val array3 = arrayOf("")
    private val array4 = arrayOf("")
    private val array5 = arrayOf("")
    private val array6 = arrayOf("")
    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))

    // coroutines用
    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)

        // テストクラス作成
        artistUseCase = mockk<ArtistUseCase>().also {
            coEvery { it.addArtist(any(), any()) } returns Result.Success(artist)
            coEvery { it.updateArtist(any(), any()) } returns Result.Success(artist)
        }
        userUseCase = mockk<UserUseCase> ().also {
            every { it.getEmail() } returns "test1"
        }
        viewModel = MyPageArtistAddViewModel(userUseCase, artistUseCase)
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, artist)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
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
        // アーティスト名未入力
        viewModel.nameText.value = ""
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        assertEquals(false, viewModel.isEnableSubmitButton.value!!)
        // 性別未選択
        viewModel.nameText.value = "test"
        viewModel.gender.value = 0
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        assertEquals(false, viewModel.isEnableSubmitButton.value!!)
        // 長さ未選択
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 0
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 声の高さ未選択
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 0
        viewModel.lyrics.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 歌詞の言語未選択
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 0
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // ジャンル１未選択
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 0
        viewModel.genre2.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // ジャンル２未選択
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 0
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 正常系
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 1
        assertEquals(viewModel.isEnableSubmitButton.value!!, true)
    }

    /**
     *　送信ロジック
     * 初期化時にアーティスト情報がなければ新規登録、
     * 初期化時にアーティスト情報があれば更新が呼ばれる
     */
    @Test
    fun submit() {
        // アーティスト新規登録
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, null)
        viewModel.submit()
        coVerify(exactly = 1) { (artistUseCase).addArtist(any(), any()) }
        coVerify(exactly = 0) { (artistUseCase).updateArtist(any(), any()) }
        // アーティスト更新
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, artist)
        viewModel.submit()
        coVerify(exactly = 1) { (artistUseCase).addArtist(any(), any()) }
        coVerify(exactly = 1) { (artistUseCase).updateArtist(any(), any()) }
    }
}
