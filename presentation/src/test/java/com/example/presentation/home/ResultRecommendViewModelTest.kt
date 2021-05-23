package com.example.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import com.example.presentation.BaseTestUnit
import com.example.presentation.util.Status
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
 * おすすめアーティスト検索結果画面
 */
class ResultRecommendViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: ResultRecommendViewModel
    private lateinit var artistUseCase: com.example.domain.usecase.ArtistUseCase

    // data
    private val successArtist = Artist("success", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val successArtistConditions = ArtistConditions("おすすめ", null, null, null, null, null, null)
    private val artistContents = ArtistContents(successArtist, null, null, 1, 3, 4, 5, 5, 6, 7, 8)
    private val successArtistContents = ArtistSearchContents.Conditions(successArtistConditions)
    private val successResult = Result.Success(listOf(artistContents))
    private val failureResult = Result.Error(IllegalArgumentException(""))

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        artistUseCase = mockk<com.example.domain.usecase.ArtistUseCase>().also {
            coEvery { it.getArtistsByRecommend() } returns successResult
            coEvery { it.getArtistList() } returns flow { emit(listOf(successArtist)) }
        }
        init()
    }

    private fun init() {
        viewModel = ResultRecommendViewModel(artistUseCase)
        viewModel.isProgressBar.observeForever(observerBoolean)
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
        viewModel.getRecommend()
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
        artistUseCase = mockk<com.example.domain.usecase.ArtistUseCase>().also {
            coEvery { it.getArtistsByRecommend() } returns failureResult
        }
        init()
        viewModel.getRecommend()
        val result = viewModel.status.value
        assertEquals(Status.Failure(failureResult.exception), result)
    }

    // endregion
}
