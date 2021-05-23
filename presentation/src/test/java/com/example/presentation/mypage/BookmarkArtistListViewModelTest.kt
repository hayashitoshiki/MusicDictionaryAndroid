package com.example.presentation.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import com.example.presentation.BaseTestUnit
import io.mockk.every
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

class BookmarkArtistListViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var artistUseCase: com.example.domain.usecase.ArtistUseCase
    private lateinit var viewModel: BookmarkArtistListViewModel

    // data
    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val artistContents = ArtistContents(artist, null, null, 0, 0, 0, 0, 0, 0, 0, 0)
    private val artistSearchContents1 = ArtistSearchContents.Item(artistContents)
    private val artistSearchContentsList = listOf(artistSearchContents1)
    private val artistSearchContentsListFlow = flow { emit(artistSearchContentsList) }

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        artistUseCase = mockk<com.example.domain.usecase.ArtistUseCase>().also {
            every { it.getBookArkArtistAll() } returns artistSearchContentsListFlow
        }
        viewModel = BookmarkArtistListViewModel(artistUseCase)
        viewModel.bookmarkArtistList.observeForever(observerArtistSearchContents)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region ブックマーク済みアーティストリスト取得

    /**
     * ブックマーク済みアーティスト取得
     *
     * 条件：なし
     * 結果：artistUseCaseから返ってきたアーティストリストがそのまま格納されていること
     */
    @Test
    fun getBookmarkArtist() {
        viewModel.bookmarkArtistList.value!!.forEachIndexed { index, item ->
            assertEquals(artistSearchContentsList[index], item)
        }
    }

    // endregion
}
