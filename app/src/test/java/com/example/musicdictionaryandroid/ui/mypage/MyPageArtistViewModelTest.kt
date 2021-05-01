package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * 登録済みアーティスト一覧画面
 */
class MyPageArtistViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: MyPageArtistViewModel
    private lateinit var artistUseCase: ArtistUseCase

    // data
    private val successArtist = Artist("success", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val failureArtist = Artist("failure", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val successResult = Result.Success("success")
    private val failureResult = Result.Error(IllegalArgumentException(""))

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        artistUseCase = mockk<ArtistUseCase>().also {
            coEvery { it.deleteArtist(successArtist.name) } returns successResult
            coEvery { it.deleteArtist(failureArtist.name) } returns failureResult
            coEvery { it.getArtistList() } returns flow { emit(listOf(successArtist)) }
        }
        viewModel = MyPageArtistViewModel(artistUseCase)
        viewModel.isProgressBar.observeForever(observerBoolean)
        viewModel.isNoDataText.observeForever(observerBoolean)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region プログレスバーの表示制御

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除未実施
     * 結果：非表示
     */
    @Test
    fun isProgressBarByStatusNon() {
        val status = Status.Non
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        assertEquals(false, result)
    }

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除実施中
     * 結果：表示
     */
    @Test
    fun isProgressBarByStatusLoading() {
        val status = Status.Loading
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        assertEquals(true, result)
    }

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除成功
     * 結果：非表示
     */
    @Test
    fun isProgressBarByStatusSuccess() {
        val status = Status.Success("success")
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        assertEquals(false, result)
    }

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除失敗
     * 結果：非表示
     */
    @Test
    fun isProgressBarByStatusError() {
        val status = Status.Failure(IllegalArgumentException(""))
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        assertEquals(false, result)
    }
    // endregion

    // region データ０件文言の表示制御

    /**
     * データ０件文言の表示制御
     *
     * 条件：アーティストリスト０件
     * 結果：表示
     */
    @Test
    fun isNoDataTextByNothingList() {
        artistUseCase = mockk<ArtistUseCase>().also {
            coEvery { it.getArtistList() } returns flow { emit(listOf<Artist>()) }
        }
        viewModel = MyPageArtistViewModel(artistUseCase)
        viewModel.isNoDataText.observeForever(observerBoolean)
        val result = viewModel.isNoDataText.value
        assertEquals(true, result)
    }

    /**
     * データ０件文言の表示制御
     *
     * 条件：アーチhストリスト１件以上
     * 結果：非表示
     */
    @Test
    fun isNoDataTextByList() {
        val result = viewModel.isNoDataText.value
        assertEquals(false, result)
    }

    // endregion

    // region アーティスト削除

    /**
     * アーティスト削除
     *
     * 条件：成功
     * 結果：StatusがSuccessとなること
     */
    @Test
    fun deleteArtistBySuccess() {
        viewModel.deleteArtist(successArtist)
        val result = viewModel.status.value
        assertEquals(Status.Success(successResult.data), result)
    }

    /**
     * アーティスト削除
     *
     * 条件：失敗
     * 結果：StatusがErrorとなること
     */
    @Test
    fun deleteArtistByError() {
        viewModel.deleteArtist(failureArtist)
        val result = viewModel.status.value
        assertEquals(Status.Failure(failureResult.exception), result)
    }

    // endregion
}
