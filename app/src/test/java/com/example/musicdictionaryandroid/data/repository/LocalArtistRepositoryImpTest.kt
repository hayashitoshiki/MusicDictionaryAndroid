package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.data.local.database.dao.ArtistDao
import com.example.musicdictionaryandroid.data.local.database.entity.ArtistEntity
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalArtistRepositoryImpTest : BaseTestUnit() {

    // mock
    private lateinit var repository: LocalArtistRepositoryImp
    private lateinit var artistDao: ArtistDao

    // data
    private val artist = Artist("TestA", Gender.MAN, Voice(1), Length(1), Lyrics(1), Genre1(1), Genre2(1))
    private val artist2 = Artist("TestB", Gender.WOMAN, Voice(1), Length(1), Lyrics(1), Genre1(1), Genre2(1))
    private val artistList = listOf(artist, artist2)
    private val artistEntity = convertArtistEntityFromArtist(artist)
    private val artistEntityList = listOf(artistEntity)
    private val artistEntityListFlow = flow { emit(artistEntityList) }

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        artistDao = mockk<ArtistDao>().also {
            coEvery { it.insert(any()) } returns Unit
            coEvery { it.update(any(), any(), any(), any(), any(), any(), any()) } returns Unit
            coEvery { it.deleteByName(any()) } returns Unit
            coEvery { it.deleteAll() } returns Unit
            coEvery { it.getAll() } returns artistEntityListFlow
            coEvery { it.getArtistByName(any()) } returns artistEntity
        }
        repository = LocalArtistRepositoryImp(artistDao, testDispatcher)
    }

    // アーティストモデルからアーティストテーブルへ変換
    private fun convertArtistEntityFromArtist(artist: Artist): ArtistEntity {
        val name = artist.name
        val gender = artist.gender.value
        val voice = artist.voice.value
        val length = artist.length.value
        val lyrics = artist.lyrics.value
        val genre1 = artist.genre1.value
        val genre2 = artist.genre2.value
        return ArtistEntity(null, name, gender, voice, length, lyrics, genre1, genre2)
    }

    // アーティストテーブルからアーティストモデルべ変換
    private fun convertArtistFromArtistEntity(artistEntity: ArtistEntity): Artist {
        val name = artistEntity.name
        val gender = Gender.getEnumByValue(artistEntity.gender)
        val voice = Voice(artistEntity.voice)
        val length = Length(artistEntity.length)
        val lyrics = Lyrics(artistEntity.lyrics)
        val genre1 = Genre1(artistEntity.genre1)
        val genre2 = Genre2(artistEntity.genre2)
        return Artist(name, gender, voice, length, lyrics, genre1, genre2)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region アーティスト追加

    /**
     * アーティスト追加
     *
     * 条件：なし
     * 結果：渡した値がそのままEntityへ変換された値でRoomへのアーティスト追加メソッドが呼ばれること
     */
    @Test
    fun addArtist() {
        runBlocking {
            repository.addArtist(artist)
            coVerify(exactly = 1) { (artistDao).insert(artistEntity) }
        }
    }

    // endregion

    // region アーティスト削除

    /**
     * アーティスト削除
     *
     * 条件：なし
     * 結果：
     * ・Roomからアーティスト削除するメソッドが呼ばれること
     * ・渡した名前に紐づくアーティストデータが削除されること
     */
    @Test
    fun deleteArtist() {
        runBlocking {
            repository.deleteArtist(artist.name)
            coVerify(exactly = 1) { (artistDao).deleteByName(artist.name) }
        }

    }

    // endregion

    // region 全アーティスト削除

    /**
     * 全アーティスト削除
     *
     * 条件：なし
     * 結果：Roomから全アーティスト削除するメソッドが呼ばれること
     */
    @Test
    fun deleteArtistAll() {
        runBlocking {
            repository.deleteAll()
            coVerify(exactly = 1) { (artistDao).deleteAll() }
        }
    }

    // endregion

    // region アーティスト更新

    /**
     * アーティスト更新
     *
     * 条件：なし
     * 結果：
     * ・Roomに指定したアーティストが更新されるメソッドが呼ばれること
     * ・引数として渡したアーティスト情報をそのまま更新すること
     */
    @Test
    fun updateArtist() {
        runBlocking {
            repository.updateArtist(artist)
        }
        coVerify(exactly = 1) {
            (artistDao).update(
                artistEntity.name,
                artistEntity.gender,
                artistEntity.voice,
                artistEntity.length,
                artistEntity.lyrics,
                artistEntity.genre1,
                artistEntity.genre2
            )
        }

    }

    // endregion

    // region 全アーティスト更新

    /**
     * 全アーティスト更新
     *
     * 条件：なし
     * 結果：引数として渡した全てのアーティストで書き換わること
     */
    @Test
    fun updateArtistAll() {
        runBlocking {
            repository.updateAll(artistList)
            coVerify(exactly = 1) { (artistDao).deleteAll() }
            artistList.forEach { artist ->
                val artistEntity = convertArtistEntityFromArtist(artist)
                coVerify(exactly = 1) { (artistDao).insert(artistEntity) }
            }
        }
    }

    // endregion

    // region アーティストリスト取得

    /**
     * アーティスト取得
     *
     * 条件：なし
     * 結果：
     * ・Roomに指定したアーティストが更新されるメソッドが呼ばれること
     * ・引数として渡したアーティスト情報をそのまま更新すること
     */
    @Test
    fun getArtist() {
        runBlocking {
            val result = repository.getArtistByName(artist.name)
            coVerify(exactly = 1) { (artistDao).getArtistByName(artist.name) }
            Assert.assertEquals(artist, result)
        }
    }

    // endregion

    // region 全アーティストリスト取得

    /**
     * 全アーティスト取得
     *
     * 条件：なし
     * 結果：
     * ・Roomから登録済みアーティストを全て取得するメソッドが呼ばれること
     * ・Roomから受け取ったアーティストが全てドメインモデルに変換されて返ること
     */
    @Test
    fun getArtistAll() {
        runBlocking {
            val expect = artistEntityListFlow.first().map {
                convertArtistFromArtistEntity(it)
            }
            val result = repository.getArtistAll().first()
            coVerify(exactly = 1) { (artistDao).getAll() }
            Assert.assertEquals(expect, result)
        }
    }

    // endregion
}