package com.example.domain.usecase

import com.example.domain.BaseTestUnit
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import com.example.domain.repository.LocalArtistRepository
import com.example.domain.repository.LocalBookmarkArtistRepository
import com.example.domain.repository.LocalUserRepository
import com.example.domain.repository.RemoteArtistRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArtistUseCaseImpTest : BaseTestUnit() {

    private lateinit var useCase: ArtistUseCaseImp
    private lateinit var remoteArtistRepository: RemoteArtistRepository
    private lateinit var localArtistRepository: LocalArtistRepository
    private lateinit var localBookmarkArtistRepository: LocalBookmarkArtistRepository
    private lateinit var localUserRepository: LocalUserRepository

    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val artistConditions = ArtistConditions("test", null, null, null, null, null, null)
    private val artistContents = ArtistContents(artist, null, null, 0, 0, 0, 0, 0, 0, 0, 0)
    private val artistContentsList = listOf(artistContents)
    private val artistList = listOf(artist, artist)
    private val artistListFlow = flow { emit(listOf(artist)) }
    private val successResult = Result.Success("Success")
    private val artistContentsListFlow = flow { emit(artistContentsList) }
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        localBookmarkArtistRepository = mockk<LocalBookmarkArtistRepository>().also {
            coEvery { it.addArtist(any()) } returns Unit
            coEvery { it.deleteArtist(any()) } returns Unit
            coEvery { it.isArtistByName(any()) } returns false
            coEvery { it.getArtistAll() } returns artistContentsListFlow
        }
        remoteArtistRepository = mockk<RemoteArtistRepository>().also {
            coEvery { it.getArtistsBy(any()) } returns Result.Success(artistContentsList)
            coEvery { it.getArtistsByRecommend(any()) } returns Result.Success(artistContentsList)
            coEvery { it.getArtistsBySoaring() } returns Result.Success(artistContentsList)
            coEvery { it.getArtistsByEmail(successEmail) } returns Result.Success(artistList)
            coEvery { it.getArtistsByEmail(failureEmail) } returns failureResult
            coEvery { it.addArtist(any(), successEmail) } returns Result.Success(artist)
            coEvery { it.addArtist(any(), failureEmail) } returns failureResult
            coEvery { it.updateArtist(any(), successEmail) } returns Result.Success(artist)
            coEvery { it.updateArtist(any(), failureEmail) } returns failureResult
            coEvery { it.deleteArtist(any(), successEmail) } returns successResult
            coEvery { it.deleteArtist(any(), failureEmail) } returns failureResult
        }
        localArtistRepository = mockk<LocalArtistRepository>().also {
            coEvery { it.addArtist(any()) } returns Unit
            coEvery { it.deleteArtist(any()) } returns Unit
            coEvery { it.deleteAll() } returns Unit
            coEvery { it.updateArtist(any()) } returns Unit
            coEvery { it.updateAll(any()) } returns Unit
            coEvery { it.getArtistByName(any()) } returns artist
            coEvery { it.getArtistAll() } returns artistListFlow
        }
        localUserRepository = mockk<LocalUserRepository>().also {
            every { it.getEmail() } returns successEmail
            every { it.setFavorite(any()) } returns Unit
            every { it.getFavorite() } returns 1
        }
        useCase = ArtistUseCaseImp(
            remoteArtistRepository,
            localArtistRepository,
            localUserRepository,
            localBookmarkArtistRepository,
            testScope,
            testDispatcher
        )
    }

    private fun reLoad() {
        useCase = ArtistUseCaseImp(
            remoteArtistRepository,
            localArtistRepository,
            localUserRepository,
            localBookmarkArtistRepository,
            testScope,
            testDispatcher
        )
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // region 検索条件に一致するアーティスト取得

    /**
     * 検索条件に一致するアーティスト取得
     * 条件：なし
     * 結果：
     * ・アーティスト検索Repositoryが呼ばれること
     * ・戻り値がアーティスト検索Repositoryの戻り値と同じであること
     */
    @Test
    fun getArtistsBy() {
        runBlocking {
            val result = useCase.getArtistsBy(artistConditions)
            assertEquals(Result.Success(artistContentsList), result)
            coVerify(exactly = 1) { (remoteArtistRepository).getArtistsBy(artistConditions) }
        }
    }

    // endregion

    // region おすすめアーティスト検索

    /**
     * おすすめアーティスト取得
     * 条件：なし
     * 結果：
     * ・おすすめアーティスト取得Repositoryが呼ばれること
     * ・戻り値がおすすめアーティスト取得Repositoryの戻り値と同じであること
     */
    @Test
    fun getArtistsByRecommend() {
        runBlocking {
            val result = useCase.getArtistsByRecommend()
            assertEquals(Result.Success(artistContentsList), result)
            coVerify(exactly = 1) { (remoteArtistRepository).getArtistsByRecommend(any()) }
        }
    }

    // endregion

    // region 急上昇アーティスト取得

    /**
     * 急上昇アーティスト取得
     * 条件：なし
     * 結果：
     * ・急上昇アーティスト取得Repositoryが呼ばれること
     * ・戻り値が急上昇アーティスト取得Repositoryの戻り値と同じであること
     */
    @Test
    fun getArtistsBySoaring() {
        runBlocking {
            val result = useCase.getArtistsBySoaring()
            assertEquals(Result.Success(artistContentsList), result)
            coVerify(exactly = 1) { (remoteArtistRepository).getArtistsBySoaring() }
        }
    }

    // endregion

    // 登録アーティスト取得

    /**
     * 登録しているアーティスト取得
     * 条件：通信取得成功時
     * 結果：
     * ・戻り値が取得したアーティストリストであること
     * ・ローカルDBで保持しているデータが更新されること
     * ・登録しているアーティストの件数が更新されること
     */
    @Test
    fun getArtistsByEmailSuccess() {
        runBlocking {
            val result = useCase.getArtistsByEmail()
            assertEquals(Result.Success(artistList), result)
            coVerify(exactly = 1) { (remoteArtistRepository).getArtistsByEmail(successEmail) }
            coVerify(exactly = 1) { (localArtistRepository).updateAll(artistList) }
            coVerify(exactly = 1) { (localUserRepository).setFavorite(artistList.size) }
        }
    }

    /**
     * 登録しているアーティスト取得
     * 条件：通信取得失敗時
     * 結果：
     * ・戻り値がローカルDBから取得したアーティストリストであること
     * ・ローカルDBで保持しているデータが更新されないこと
     * ・登録しているアーティストの件数が更新されないこと
     * ・ローカルDBで保持している登録済みアーティストリストから取得すること
     */
    @Test
    fun getArtistsByEmailFailure() {
        runBlocking {
            localUserRepository = mockk<LocalUserRepository>().also {
                every { it.getEmail() } returns failureEmail
            }
            reLoad()
            useCase.getArtistsByEmail()
            coVerify(exactly = 1) { (remoteArtistRepository).getArtistsByEmail(failureEmail) }
            coVerify(exactly = 0) { (localArtistRepository).updateAll(artistList) }
            coVerify(exactly = 0) { (localUserRepository).setFavorite(artistList.size) }
        }
    }

    // endregion

    // アーティスト登録

    /**
     * アーティスト登録
     * 条件：通信取得成功時
     * 結果：
     * ・APIに登録するアーティストを送信するメソッドが呼ばれること
     * ・ローカルDBに登録したを追加するメソッドが呼ばれること
     */
    @Test
    fun addArtistSuccess() {
        runBlocking {
            useCase.addArtist(artist)
            coVerify(exactly = 1) { (remoteArtistRepository).addArtist(artist, successEmail) }
            coVerify(exactly = 1) { (localArtistRepository).addArtist(artist) }
        }
    }

    /**
     * アーティスト登録
     * 条件：通信取得失敗時
     * 結果：
     * ・APIに登録するアーティストを送信するメソッドが呼ばれること
     * ・ローカルDBに登録したを追加するメソッドが呼ばれること
     */
    @Test
    fun addArtistFailure() {
        runBlocking {
            localUserRepository = mockk<LocalUserRepository>().also {
                every { it.getEmail() } returns failureEmail
            }
            reLoad()
            val result = useCase.addArtist(artist)
            assertEquals(failureResult, result)
            coVerify(exactly = 1) { (remoteArtistRepository).addArtist(artist, failureEmail) }
            coVerify(exactly = 0) { (localArtistRepository).addArtist(artist) }
        }
    }

    // endregion

    // アーティスト更新

    /**
     * アーティスト更新
     * 条件：通信取得成功時
     * 結果：
     * ・APIに更新するアーティストを送信するメソッドが呼ばれること
     * ・ローカルDBに更新したアーティストを更新するメソッドが呼ばれること
     */
    @Test
    fun updateArtistSuccess() {
        runBlocking {
            useCase.updateArtist(artist)
            coVerify(exactly = 1) { (remoteArtistRepository).updateArtist(artist, successEmail) }
            coVerify(exactly = 1) { (localArtistRepository).updateArtist(artist) }
        }
    }

    /**
     * アーティスト更新
     * 条件：通信取得失敗時
     * 結果：
     * ・APIに更新するアーティストを送信するメソッドが呼ばれること
     * ・ローカルDBに更新したアーティストを更新するメソッドが呼ばれること
     */
    @Test
    fun updateArtistFailure() {
        runBlocking {
            localUserRepository = mockk<LocalUserRepository>().also {
                every { it.getEmail() } returns failureEmail
            }
            reLoad()
            useCase.updateArtist(artist)
            coVerify(exactly = 1) { (remoteArtistRepository).updateArtist(artist, failureEmail) }
            coVerify(exactly = 0) { (localArtistRepository).updateArtist(artist) }
        }
    }

    // endregion

    /**
     * アーティスト更新
     * 条件：通信取得成功時
     * 結果：
     * ・APIに削除するアーティストを送信するメソッドが呼ばれること
     * ・ローカルDBに削除したアーティストを削除するメソッドが呼ばれること
     */
    @Test
    fun deleteArtistSuccess() {
        runBlocking {
            useCase.deleteArtist(artist.name)
            coVerify(exactly = 1) { (remoteArtistRepository).deleteArtist(artist.name, successEmail) }
            coVerify(exactly = 1) { (localArtistRepository).deleteArtist(artist.name) }
        }
    }

    /**
     * アーティスト削除
     * 条件：通信取得失敗時
     * 結果：
     * ・APIに削除するアーティストを送信するメソッドが呼ばれること
     * ・ローカルDBに削除したアーティストを削除するメソッドが呼ばれること
     */
    @Test
    fun deleteArtistFailure() {
        runBlocking {
            localUserRepository = mockk<LocalUserRepository>().also {
                every { it.getEmail() } returns failureEmail
            }
            reLoad()
            useCase.deleteArtist(artist.name)
            coVerify(exactly = 1) { (remoteArtistRepository).deleteArtist(artist.name, failureEmail) }
            coVerify(exactly = 0) { (localArtistRepository).deleteArtist(artist.name) }
        }
    }

    // endregion

    // region アーティストリスト取得

    /**
     * アーティストリスト取得
     * 条件：なし
     * 結果：ローカルDBのアーティスト取得メソッドが呼ばれること
     */
    @Test
    fun getArtistList() {
        useCase.getArtistList()
        coVerify(exactly = 1) { (localArtistRepository).getArtistAll() }
    }

    // endregion

    // region ブックマーク登録

    /**
     * ブックマーク登録
     * 条件：なし
     * 結果：ローカルDBのブックマークアーティスト登録メソッドが呼ばれること
     */
    @Test
    fun setBookmarkArtist() {
        runBlocking {
            useCase.setBookmarkArtist(artistContents)
            coVerify(exactly = 1) { (localBookmarkArtistRepository).addArtist(artistContents) }
        }
    }

    // endregion

    // region ブックマーク登録解除

    /**
     * ブックマーク登録解除
     * 条件：なし
     * 結果：ローカルDBのブックマークアーティスト削除メソッドが呼ばれること
     */
    @Test
    fun deleteBookmarkArtist() {
        runBlocking {
            useCase.deleteBookmarkArtist(artistContents)
            coVerify(exactly = 1) { (localBookmarkArtistRepository).deleteArtist(artistContents.artist.name) }
        }
    }

    // endregion

    // region ブックマークリスト取得

    /**
     * ブックマークリスト取得
     * 条件：なし
     * 結果：ローカルDBのブックマークアーティストリスト取得メソッドが呼ばれること
     * ・取得した内容がそのままドメインモデル形式に変換されて返ること
     */
    @Test
    fun getBookArkArtistAll() {
        runBlocking {
            val result = useCase.getBookArkArtistAll().first()
            coVerify(exactly = 1) { (localBookmarkArtistRepository).getArtistAll() }
            result.forEachIndexed { index, item ->
                assertEquals(artistContentsList[index], item.value)
            }
        }
    }

    // endregion
}
