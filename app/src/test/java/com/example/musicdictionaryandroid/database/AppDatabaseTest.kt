package com.example.musicdictionaryandroid.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.database.dao.ArtistDao
import com.example.data.local.database.dao.BookmarkArtistDao
import com.example.data.local.database.entity.ArtistEntity
import com.example.data.local.database.entity.BookmarkArtistEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

/**
 * Daoテストサンプル
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class AppDatabaseTest {

    private lateinit var bookmarkDao: BookmarkArtistDao
    private lateinit var artistDao: ArtistDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        artistDao = db.artistDao()
        bookmarkDao = db.bookmarkArtistDao()
    }
    @After
    @Throws(IOException::class)
    fun tearDown() {
        stopKoin()
        db.close()
    }

    // region アーティストDao

    /**
     * 登録
     *
     * 条件：なし
     * 結果：入力した内容が登録されること
     */
    @Test
    fun insertTest() {
        runBlocking {
            val artist = ArtistEntity(1, "testArtist", 1, 1, 1, 1, 1, 1)
            artistDao.insert(artist)
            val result = artistDao.getArtistByName(artist.name)
            assertEquals(1L, result.id)
            assertEquals(artist.name, result.name)
            assertEquals(artist.gender, result.gender)
            assertEquals(artist.voice, result.voice)
            assertEquals(artist.length, result.length)
            assertEquals(artist.lyrics, result.lyrics)
            assertEquals(artist.genre1, result.genre1)
            assertEquals(artist.genre2, result.genre2)
        }
    }

    /**
     * 更新
     *
     * 条件：なし
     * 結果：入力した内容が登録されること
     */
    @Test
    fun updateTest() {
        runBlocking {
            val artist = ArtistEntity(null, "testArtist", 1, 1, 1, 1, 1, 1)
            val artist2 = ArtistEntity(1, "testArtist", 2, 2, 2, 2, 2, 2)
            artistDao.insert(artist)
            artistDao.update(artist2.name, artist2.gender, artist2.voice, artist2.length, artist2.lyrics, artist2.genre1, artist2.genre2)
            val result = artistDao.getArtistByName(artist.name)
            assertEquals(1L, result.id)
            assertEquals(artist2.name, result.name)
            assertEquals(artist2.gender, result.gender)
            assertEquals(artist2.voice, result.voice)
            assertEquals(artist2.length, result.length)
            assertEquals(artist2.lyrics, result.lyrics)
            assertEquals(artist2.genre1, result.genre1)
            assertEquals(artist2.genre2, result.genre2)
        }
    }

    /**
     * アーティスト削除
     *
     * 条件：なし
     * 結果：削除したアーティストが取得できないこと
     */
    @Test
    fun deleteByNameTest() {
        runBlocking {
            val artist = ArtistEntity(null, "testArtist", 1, 1, 1, 1, 1, 1)
            artistDao.insert(artist)
            artistDao.deleteByName(artist.name)
            val result = artistDao.getArtistByName(artist.name)
            assertEquals(null, result)
        }
    }

    /**
     * 全アーティスト削除
     *
     * 条件：なし
     * 結果：削除したアーティストが取得できないこと
     */
    @Test
    fun deleteAllTest() {
        runBlocking {
            val artist1 = ArtistEntity(null, "testArtist", 1, 1, 1, 1, 1, 1)
            val artist2 = ArtistEntity(null, "testArtist", 1, 1, 1, 1, 1, 1)
            artistDao.insert(artist1)
            artistDao.insert(artist2)
            artistDao.deleteAll()
            val result1 = artistDao.getArtistByName(artist1.name)
            val result2 = artistDao.getArtistByName(artist2.name)
            assertEquals(null, result1)
            assertEquals(null, result2)
        }
    }

    /**
     * 全アーティスト取得
     *
     * 条件：アーティスト２件登録
     * 結果：入力した内容が全て取得できること
     */
    @Test
    fun getAllTest() {
        runBlocking {
            val artist1 = ArtistEntity(null, "testArtist1", 1, 1, 1, 1, 1, 1)
            val artist2 = ArtistEntity(null, "testArtist2", 2, 2, 2, 2, 2, 2)
            val artistList = listOf(artist1, artist2)
            artistDao.insert(artist1)
            artistDao.insert(artist2)
            val result = artistDao.getAll().first()
            artistList.forEachIndexed { index, artistEntity ->
                assertEquals((index + 1).toLong(), result[index].id)
                assertEquals(artistEntity.name, result[index].name)
                assertEquals(artistEntity.gender, result[index].gender)
                assertEquals(artistEntity.voice, result[index].voice)
                assertEquals(artistEntity.length, result[index].length)
                assertEquals(artistEntity.lyrics, result[index].lyrics)
                assertEquals(artistEntity.genre1, result[index].genre1)
                assertEquals(artistEntity.genre2, result[index].genre2)
            }
        }
    }

    // endregion

    // region お気に入り登録

    /**
     * お気に入りアーティスト追加
     * 条件：なし
     * 結果：登録したお気に入りアーティストが取得できること
     */
    @Test
    @Throws(Exception::class)
    fun bookmarkInsertTest() {
        runBlocking {
            val bookmarkEntity = BookmarkArtistEntity(null, "test1", 1, 1, 1, 1, 1, 1, "1", "1", 1, 1, 1, 1, 1, 1, 1, 1)
            bookmarkDao.insert(bookmarkEntity)
            val result = bookmarkDao.getArtistByName(bookmarkEntity.name)
            assertEquals(1L, result?.id)
            assertEquals(bookmarkEntity.name, result?.name)
            assertEquals(bookmarkEntity.gender, result?.gender)
            assertEquals(bookmarkEntity.voice, result?.voice)
            assertEquals(bookmarkEntity.length, result?.length)
            assertEquals(bookmarkEntity.lyrics, result?.lyrics)
            assertEquals(bookmarkEntity.genre1, result?.genre1)
            assertEquals(bookmarkEntity.genre2, result?.genre2)
            assertEquals(bookmarkEntity.thumb, result?.thumb)
            assertEquals(bookmarkEntity.preview, result?.preview)
            assertEquals(bookmarkEntity.generation1, result?.generation1)
            assertEquals(bookmarkEntity.generation2, result?.generation2)
            assertEquals(bookmarkEntity.generation3, result?.generation3)
            assertEquals(bookmarkEntity.generation4, result?.generation4)
            assertEquals(bookmarkEntity.generation5, result?.generation5)
            assertEquals(bookmarkEntity.generation6, result?.generation6)
            assertEquals(bookmarkEntity.userMan, result?.userMan)
            assertEquals(bookmarkEntity.userWoman, result?.userWoman)
        }
    }

    /**
     * お気に入りアーティスト削除
     *
     * 条件：なし
     * 結果：登録したお気に入りアーティストが取得できること
     */
    @Test
    @Throws(Exception::class)
    fun bookmarkDeleteByNameTest() {
        runBlocking {
            val bookmarkEntity = BookmarkArtistEntity(null, "test1", 1, 1, 1, 1, 1, 1, "1", "1", 1, 1, 1, 1, 1, 1, 1, 1)
            bookmarkDao.insert(bookmarkEntity)
            bookmarkDao.deleteByName(bookmarkEntity.name)
            val result = bookmarkDao.getArtistByName(bookmarkEntity.name)
            assertEquals(null, result)
        }
    }
    /**
     * 全お気に入りアーティスト削除
     *
     * 条件：なし
     * 結果：登録したお気に入りアーティストが全て取得できること
     */
    @Test
    @Throws(Exception::class)
    fun bookmarkDeleteAllTest() {
        runBlocking {
            val bookmarkEntity1 = BookmarkArtistEntity(null, "test1", 1, 1, 1, 1, 1, 1, "1", "1", 1, 1, 1, 1, 1, 1, 1, 1)
            val bookmarkEntity2 = BookmarkArtistEntity(null, "test2", 2, 2, 2, 2, 2, 2, "2", "2", 2, 2, 2, 2, 2, 2, 2, 2)
            bookmarkDao.insert(bookmarkEntity1)
            bookmarkDao.insert(bookmarkEntity2)
            bookmarkDao.deleteAll()
            val result1 = bookmarkDao.getArtistByName(bookmarkEntity1.name)
            val result2 = bookmarkDao.getArtistByName(bookmarkEntity2.name)
            assertEquals(null, result1)
            assertEquals(null, result2)
        }
    }

    /**
     * 全お気に入りアーティスト取得
     *
     * 条件：なし
     * 結果：登録したお気に入りアーティストが全て取得できること
     */
    @Test
    @Throws(Exception::class)
    fun bookmarkIGetAllTest() {
        runBlocking {
            val bookmarkEntity1 = BookmarkArtistEntity(null, "test1", 1, 1, 1, 1, 1, 1, "1", "1", 1, 1, 1, 1, 1, 1, 1, 1)
            val bookmarkEntity2 = BookmarkArtistEntity(null, "test2", 2, 2, 2, 2, 2, 2, "2", "2", 2, 2, 2, 2, 2, 2, 2, 2)
            bookmarkDao.insert(bookmarkEntity1)
            bookmarkDao.insert(bookmarkEntity2)
            val bookmarkList = listOf(bookmarkEntity1, bookmarkEntity2)
            val result = bookmarkDao.getAll().first()
            bookmarkList.forEachIndexed { index, bookmarkArtistEntity ->
                assertEquals((index + 1).toLong(), result[index].id)
                assertEquals(bookmarkArtistEntity.name, result[index].name)
                assertEquals(bookmarkArtistEntity.gender, result[index].gender)
                assertEquals(bookmarkArtistEntity.voice, result[index].voice)
                assertEquals(bookmarkArtistEntity.length, result[index].length)
                assertEquals(bookmarkArtistEntity.lyrics, result[index].lyrics)
                assertEquals(bookmarkArtistEntity.genre1, result[index].genre1)
                assertEquals(bookmarkArtistEntity.genre2, result[index].genre2)
                assertEquals(bookmarkArtistEntity.thumb, result[index].thumb)
                assertEquals(bookmarkArtistEntity.preview, result[index].preview)
                assertEquals(bookmarkArtistEntity.generation1, result[index].generation1)
                assertEquals(bookmarkArtistEntity.generation2, result[index].generation2)
                assertEquals(bookmarkArtistEntity.generation3, result[index].generation3)
                assertEquals(bookmarkArtistEntity.generation4, result[index].generation4)
                assertEquals(bookmarkArtistEntity.generation5, result[index].generation5)
                assertEquals(bookmarkArtistEntity.generation6, result[index].generation6)
                assertEquals(bookmarkArtistEntity.userMan, result[index].userMan)
                assertEquals(bookmarkArtistEntity.userWoman, result[index].userWoman)
            }
        }
    }

    // endregion
}
