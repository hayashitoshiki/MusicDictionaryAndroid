package com.example.presentation.home

import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import com.example.presentation.BaseTestUnit
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import java.lang.reflect.Field
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * アーティスト検索結果リストロジック部分の詳細仕様
 */
class ResultAdapterViewModelTest : BaseTestUnit() {

    // mock
    private lateinit var artistUseCase: com.example.domain.usecase.ArtistUseCase
    private lateinit var viewModel: ResultAdapterViewModel
    private lateinit var resultArtistBodyState1: ResultArtistBodyState
    private lateinit var resultArtistBodyState2: ResultArtistBodyState

    // data
    private val artist = Artist("artist", Gender.MAN, Voice(1), Length(1), Lyrics(1), Genre1(1), Genre2(1))
    private val artistContentsBookmarkFalse = ArtistContents(artist, null, "aaa", 1, 2, 3, 4, 5, 6, 1, 1, false)
    private val artistContentsBookmarkTrue = ArtistContents(artist, null, "bbb", 1, 2, 3, 4, 5, 6, 1, 1, true)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        artistUseCase = mockk<com.example.domain.usecase.ArtistUseCase>().also {
            coEvery { it.setBookmarkArtist(any()) } returns Unit
            coEvery { it.deleteBookmarkArtist(any()) } returns Unit
        }
        resultArtistBodyState1 = mockk<ResultArtistBodyState>().also {
            coEvery { it.startPlayback(any()) } returns Unit
            coEvery { it.stopPlayback() } returns Unit
        }
        resultArtistBodyState2 = mockk<ResultArtistBodyState>().also {
            coEvery { it.startPlayback(any()) } returns Unit
            coEvery { it.stopPlayback() } returns Unit
        }

        viewModel = ResultAdapterViewModel(artistUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region 再生処理

    /**
     * 再生処理
     *
     * 条件：再生中の音源なし
     * 結果：指定したアーティストの楽曲が再生されること
     */
    @Test
    fun onClickPlayBackByNull() {
        viewModel.onClickPlayBack(resultArtistBodyState1, artistContentsBookmarkFalse)
        verify(exactly = 1) { (resultArtistBodyState1).startPlayback(artistContentsBookmarkFalse.preview!!) }
    }

    /**
     * 再生処理
     *
     * 条件：再生中の音源あり(自分の曲)
     * 結果：再生が止まること
     */
    @Test
    fun onClickPlayBackBySelfArtist() {
        val turn: Field = viewModel.javaClass.getDeclaredField("holdState")
        turn.isAccessible = true
        turn.set(viewModel, resultArtistBodyState1)
        viewModel.onClickPlayBack(resultArtistBodyState1, artistContentsBookmarkFalse)
        verify(exactly = 1) { (resultArtistBodyState1).stopPlayback() }
        verify(exactly = 0) { (resultArtistBodyState1).startPlayback(any()) }
    }

    /**
     * 再生処理
     *
     * 条件：再生中の音源あり(他のアーティストの曲)
     * 結果：再生される楽曲が切り替わること
     */
    @Test
    fun onClickPlayBackByOtherArtist() {
        val turn: Field = viewModel.javaClass.getDeclaredField("holdState")
        turn.isAccessible = true
        turn.set(viewModel, resultArtistBodyState2)

        viewModel.onClickPlayBack(resultArtistBodyState1, artistContentsBookmarkFalse)
        verify(exactly = 1) { (resultArtistBodyState2).stopPlayback() }
        verify(exactly = 1) { (resultArtistBodyState1).startPlayback(artistContentsBookmarkFalse.preview!!) }
    }

    // endregion

    // region ブックマーク切り替え

    /**
     * ブックマーク切り替え
     *
     * 条件：bookmark登録アーティスト
     * 結果：アーティストUseCaseのブックマーク登録メソッドが呼ばれること
     */
    @Test
    fun setBookMarkByTrue() {
        viewModel.setBookMark(artistContentsBookmarkTrue)
        coVerify(exactly = 1) { (artistUseCase).setBookmarkArtist(artistContentsBookmarkTrue) }
        coVerify(exactly = 0) { (artistUseCase).deleteBookmarkArtist(any()) }
    }

    /**
     * ブックマーク切り替え
     *
     * 条件：bookmark登録解除アーティスト
     * 結果：アーティストUseCaseのブックマーク登録解除メソッドが呼ばれること
     */
    @Test
    fun setBookMarkByFalse() {
        viewModel.setBookMark(artistContentsBookmarkFalse)
        coVerify(exactly = 0) { (artistUseCase).setBookmarkArtist(any()) }
        coVerify(exactly = 1) { (artistUseCase).deleteBookmarkArtist(artistContentsBookmarkFalse) }
    }

    // endregion
}
