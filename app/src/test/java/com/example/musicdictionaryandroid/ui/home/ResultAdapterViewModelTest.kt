package com.example.musicdictionaryandroid.ui.home

import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.MessageUtil
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field

class ResultAdapterViewModelTest : BaseTestUnit() {

    // mock
    private lateinit var artistUseCase: ArtistUseCase
    private lateinit var viewModel: ResultAdapterViewModel
    private lateinit var resultAdapterBodyState1: ResultAdapterBodyState
    private lateinit var resultAdapterBodyState2: ResultAdapterBodyState

    // data
    private val messageUtil = mockk<MessageUtil>()

    private val artist = Artist("artist", Gender.MAN, Voice(1), Length(1), Lyrics(1), Genre1(1), Genre2(1))
    private val artistContentsBookmarkFalse = ArtistContents(artist, null, "aaa", 1, 2, 3, 4, 5, 6, 1, 1, false)
    private val artistContentsBookmarkTrue = ArtistContents(artist, null, "bbb", 1, 2, 3, 4, 5, 6, 1, 1, true)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        artistUseCase = mockk<ArtistUseCase>().also {
            coEvery { it.setBookmarkArtist(any()) } returns Unit
            coEvery { it.deleteBookmarkArtist(any()) } returns Unit
        }
        resultAdapterBodyState1 = mockk<ResultAdapterBodyState>().also {
            coEvery { it.startPlayback(any()) } returns Unit
            coEvery { it.stopPlayback() } returns Unit
        }
        resultAdapterBodyState2 = mockk<ResultAdapterBodyState>().also {
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
        viewModel.onClickPlayBack(resultAdapterBodyState1, artistContentsBookmarkFalse)
        verify(exactly = 1) { (resultAdapterBodyState1).startPlayback(artistContentsBookmarkFalse.preview!!) }
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
        turn.set(viewModel, resultAdapterBodyState1)
        viewModel.onClickPlayBack(resultAdapterBodyState1, artistContentsBookmarkFalse)
        verify(exactly = 1) { (resultAdapterBodyState1).stopPlayback() }
        verify(exactly = 0) { (resultAdapterBodyState1).startPlayback(any()) }
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
        turn.set(viewModel, resultAdapterBodyState2)

        viewModel.onClickPlayBack(resultAdapterBodyState1, artistContentsBookmarkFalse)
        verify(exactly = 1) { (resultAdapterBodyState2).stopPlayback() }
        verify(exactly = 1) { (resultAdapterBodyState1).startPlayback(artistContentsBookmarkFalse.preview!!) }
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