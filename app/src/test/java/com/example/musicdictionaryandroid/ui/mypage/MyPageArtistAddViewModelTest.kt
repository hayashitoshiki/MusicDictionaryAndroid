package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.nhaarman.mockito_kotlin.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * アーティスト登録・更新画面
 */

class MyPageArtistAddViewModelTest {

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
            coEvery { it.addArtist(any()) } returns Result.Success(artist)
            coEvery { it.updateArtist(any()) } returns Result.Success(artist)
        }
        userUseCase = mockk()
        viewModel = MyPageArtistAddViewModel(artistUseCase)
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, artist)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    // region 送信ボタンバリデート

    /**
     * 入力バリデートロジック
     *
     * 条件：アーティスト新規登録時の初期状態
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByInitNew() {
        assertEquals(false, viewModel.isEnableSubmitButton.value!!)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：アーティスト新規更新時の初期状態
     * 期待結果：送信ボタンが活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByInitUpdate() {
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, artist)
        assertEquals(false, viewModel.isEnableSubmitButton.value!!)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：アーティスト名のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByNameNull() {
        viewModel.nameText.value = ""
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：性別のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByGenderNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 0
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：長さのみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByLengthNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 0
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：声の高さのみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByVoiceNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 0
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：歌詞情報のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByLyricsNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 0
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：ジャンル１のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByGenre1Null() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 0
        viewModel.genre2.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：ジャンル２のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByGenre2rNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 0
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 入力バリデートロジック
     *
     * 条件：全て入力済みであること
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateBySuccess() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(true, result)
    }

    // endregion

    // region 送信ボタン

    /**
     *　送信ロジック
     *
     * 条件：初期表示時にアーティスト情報なし
     * 結果：アーティスト業務ロジッククラスの新規登録メソッドが呼ばれること
     */
    @Test
    fun submitByNew() {
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, null)
        viewModel.submit()
        coVerify(exactly = 1) { (artistUseCase).addArtist(any()) }
        coVerify(exactly = 0) { (artistUseCase).updateArtist(any()) }
    }

    /**
     *　送信ロジック
     *
     * 条件：初期表示時にアーティスト情報あり
     * 結果：アーティスト業務ロジッククラスの更新メソッドが呼ばれること
     */
    @Test
    fun submitByUpdate() {
        viewModel.init(array0, array1, array2, array3, array4, array5, array6, artist)
        viewModel.submit()
        coVerify(exactly = 0) { (artistUseCase).addArtist(any()) }
        coVerify(exactly = 1) { (artistUseCase).updateArtist(any()) }
    }

    // endregion
}
