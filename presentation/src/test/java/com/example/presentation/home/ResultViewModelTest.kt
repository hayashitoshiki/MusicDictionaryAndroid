package com.example.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import com.example.domain.usecase.ArtistUseCase
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

class ResultViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: ResultViewModel
    private lateinit var artistUseCase: com.example.domain.usecase.ArtistUseCase

    // data
    private val successArtist = Artist("success", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val successArtistConditions = ArtistConditions("success", null, null, null, null, null, null)
    private val failureArtistConditions = ArtistConditions("failure", null, null, null, null, null, null)
    private val emptyArtistConditions = ArtistConditions("empty", null, null, null, null, null, null)
    private val artistContents = ArtistContents(successArtist, null, null, 1, 3, 4, 5, 5, 6, 7, 8)
    private val successArtistContents = ArtistSearchContents.Conditions(successArtistConditions)
    private val successResult = Result.Success(listOf(artistContents))
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val emptyResult = Result.Success(listOf<ArtistContents>())

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        artistUseCase = mockk<com.example.domain.usecase.ArtistUseCase>().also {
            coEvery { it.getArtistsBy(successArtistConditions) } returns successResult
            coEvery { it.getArtistsBy(failureArtistConditions) } returns failureResult
            coEvery { it.getArtistsBy(emptyArtistConditions) } returns emptyResult
            coEvery { it.getArtistList() } returns flow { emit(listOf(successArtist)) }
        }
        viewModel = ResultViewModel(artistUseCase)
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
        val status = Status.Success(listOf(successArtistContents))
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
        viewModel.getArtists(emptyArtistConditions)
        val result = viewModel.isNoDataText.value
        assertEquals(true, result)
    }

    /**
     * データ０件文言の表示制御
     *
     * 条件：アーティストリスト１件以上
     * 結果：非表示
     */
    @Test
    fun isNoDataTextByList() {
        viewModel.getArtists(successArtistConditions)
        val result = viewModel.isNoDataText.value
        assertEquals(false, result)
    }

    // endregion

    // region アーティスト検索

    /**
     * アーティスト検索
     *
     * 条件：成功
     * 結果：StatusがSuccessとなること
     */
    @Test
    fun deleteArtistBySuccess() {
        val conditions = ArtistSearchContents.Conditions(successArtistConditions)
        val arrayList = arrayListOf<ArtistSearchContents<*>>(conditions)
        arrayList.add(ArtistSearchContents.Item(artistContents))
        viewModel.getArtists(successArtistConditions)
        val result = viewModel.status.value
        assertEquals(Status.Success(arrayList), result)
    }

    /**
     * アーティスト検索
     *
     * 条件：失敗
     * 結果：StatusがErrorとなること
     */
    @Test
    fun deleteArtistByError() {
        viewModel.getArtists(failureArtistConditions)
        val result = viewModel.status.value
        assertEquals(Status.Failure(failureResult.exception), result)
    }

    // endregion
}
