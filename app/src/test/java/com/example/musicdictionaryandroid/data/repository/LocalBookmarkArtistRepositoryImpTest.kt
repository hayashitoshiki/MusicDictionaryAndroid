package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.data.local.database.dao.BookmarkArtistDao
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalBookmarkArtistRepositoryImpTest : BaseTestUnit() {

    // mock
    private lateinit var repository: LocalBookmarkArtistRepositoryImp
    private lateinit var bookmarkArtistDao: BookmarkArtistDao

    // data
    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val artistContents = ArtistContents(artist, null, null, 0, 0, 0, 0, 0, 0, 0, 0)
    private val bookmarkArtistEntity = Converter.bookmarkArtistEntityFromArtistContents(artistContents)
    private val artistContentsList = listOf(artistContents, artistContents)
    private val bookmarkArtistEntityList = listOf(bookmarkArtistEntity, bookmarkArtistEntity)
    private val bookmarkArtistEntityListFlow = flow { emit(bookmarkArtistEntityList) }
    private val nullName = "Null"
    private val notNullName = "notNull"

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        bookmarkArtistDao = mockk<BookmarkArtistDao>().also {
            coEvery { it.insert(any()) } returns Unit
            coEvery { it.deleteByName(any()) } returns Unit
            coEvery { it.deleteAll() } returns Unit
            coEvery { it.getArtistByName(notNullName) } returns bookmarkArtistEntity
            coEvery { it.getArtistByName(nullName) } returns null
            coEvery { it.getAll() } returns bookmarkArtistEntityListFlow
        }
        repository = LocalBookmarkArtistRepositoryImp(bookmarkArtistDao, testDispatcher)
    }

    @After
    fun tearDown() {
    }

    // region ブックマークアーティスト登録

    /**
     * ブックマークアーティスト登録
     *
     * 条件：なし
     * 結果：ブックマーク登録メソッドが呼ばれること
     */
    @Test
    fun addArtist() {
        runBlocking {
            repository.addArtist(artistContents)
            coVerify(exactly = 1) { (bookmarkArtistDao).insert(bookmarkArtistEntity) }
        }
    }

    // endregion

    // region ブックマークアーティスト登録解除

    /**
     * ブックマークアーティスト登録解除
     *
     * 条件：なし
     * 結果：ブックマーク登録解除メソッドが呼ばれること
     */
    @Test
    fun deleteArtist() {
        runBlocking {
            repository.deleteArtist(artistContents.artist.name)
            coVerify(exactly = 1) { (bookmarkArtistDao).deleteByName(artistContents.artist.name) }
        }
    }

    // endregion

    // region 全ブックマーク済みアーティスト登録解除

    /**
     * 全ブックマーク済みアーティスト登録解除
     *
     * 条件：なし
     * 結果：DBから全ブックマーク済みアーティストが削除されるメソッドが呼ばれること
     */
    @Test
    fun deleteAll() {
        runBlocking {
            repository.deleteAll()
            coVerify(exactly = 1) { (bookmarkArtistDao).deleteAll() }
        }
    }

    // endregion

    // 全ブックマーク済アーティストリスト取得

    /**
     * 全ブックマーク済みアーティスト登録解除
     *
     * 条件：なし
     * 結果：DBから全ブックマーク済みアーティストが削除されるメソッドが呼ばれること
     */
    @Test
    fun getArtistAll() {
        runBlocking {
            val result = repository.getArtistAll().first()
            coVerify(exactly = 1) { (bookmarkArtistDao).getAll() }
            result.forEachIndexed { index, artistContents ->
                assertEquals(artistContentsList[index], artistContents)
            }
        }
    }

    // endregion

    // ブックマーク済チェック

    /**
     * 全ブックマーク済みアーティスト登録解除
     *
     * 条件：取得結果がnull
     * 結果：DBから全ブックマーク済みアーティストが削除されるメソッドが呼ばれること
     */
    @Test
    fun isArtistByNameByNull() {
        runBlocking {
            val result = repository.isArtistByName(nullName)
            coVerify(exactly = 1) { (bookmarkArtistDao).getArtistByName(nullName) }
            assertEquals(false, result)
        }
    }

    /**
     * 全ブックマーク済みアーティスト登録解除
     *
     * 条件：取得結果がNull以外
     * 結果：DBから全ブックマーク済みアーティストが削除されるメソッドが呼ばれること
     */
    @Test
    fun isArtistByNameByNotNull() {
        runBlocking {
            val result = repository.isArtistByName(notNullName)
            coVerify(exactly = 1) { (bookmarkArtistDao).getArtistByName(notNullName) }
            assertEquals(true, result)
        }
    }

    // endregion
}