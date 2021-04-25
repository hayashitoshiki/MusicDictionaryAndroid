package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.data.util.Result
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

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

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
    private val artistsForm = ArtistsDto()
    private val artist = ArtistEntity(1, "test", 1, 1, 1, 1, 1, 1)

    // coroutines用
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)

        // テストクラス作成
        artistUseCase = mockk<ArtistUseCase>().also {
            coEvery { it.addArtist(any(), any()) } returns Result.Success(artistsForm)
            coEvery { it.updateArtist(any(), any()) } returns Result.Success(artistsForm)
        }
        userUseCase = mockk<UserUseCase> ().also {
            every { it.getEmail() } returns "test1"
        }
        viewModel = MyPageArtistAddViewModel(userUseCase, artistUseCase)
        val observer = mock<Observer<Boolean>>()
        val observerArtist = mock<Observer<ArtistEntity>>()
        viewModel.artistEntityForm.observeForever(observerArtist)
        viewModel.isEnableSubmitButton.observeForever(observer)
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, artist)
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
        // アーティスト名未入力
        viewModel.changeArtistName("")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 性別未選択
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(0)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 長さ未選択
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(0)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 声の高さ未選択
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(0)
        viewModel.checkedChangeLyric(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 歌詞の言語未選択
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(0)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // ジャンル１未選択
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        viewModel.changeGenre1(0)
        viewModel.changeGenre2(1)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // ジャンル２未選択
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        viewModel.changeGenre1(1)
        viewModel.changeGenre2(0)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 正常系
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        viewModel.changeGenre1(1)
        viewModel.changeGenre2(1)
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
