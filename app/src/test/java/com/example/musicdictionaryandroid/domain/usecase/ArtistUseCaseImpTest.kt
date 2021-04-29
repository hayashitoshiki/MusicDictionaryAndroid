package com.example.musicdictionaryandroid.domain.usecase

import com.example.musicdictionaryandroid.data.repository.LocalArtistRepository
import com.example.musicdictionaryandroid.data.repository.LocalUserRepository
import com.example.musicdictionaryandroid.data.repository.RemoteArtistRepository
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.model.value.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArtistUseCaseImpTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var useCase: ArtistUseCaseImp
    private lateinit var remoteArtistRepository: RemoteArtistRepository
    private lateinit var localArtistRepository: LocalArtistRepository
    private lateinit var localUserRepository: LocalUserRepository

    private val user = User("test@com.jp", "testA", 1, 1, "2000/2/2", 1)
    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))
    private val artistConditions = ArtistConditions("test", null, null, null, null, null, null)
    private val artistContents = ArtistContents(artist, null, null, 0, 0, 0, 0, 0, 0, 0, 0)
    private val artistContentsList = listOf(artistContents)
    private val artistList = listOf(artist, artist)
    private val artistListFlow = flow { emit(listOf(artist)) }
    private val successResult = Result.Success("Success")
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
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
            coEvery { it.findByName(any()) } returns artist
            coEvery { it.getArtistAll() } returns artistList
            coEvery { it.getArtistList() } returns artistListFlow
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
            testScope,
            testDispatcher
        )
    }

    private fun reLoad() {
        useCase = ArtistUseCaseImp(
            remoteArtistRepository,
            localArtistRepository,
            localUserRepository,
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

    /**
     * アーティストリスト取得
     * 条件：なし
     * 結果：ローカルDBのアーティスト取得メソッドが呼ばれること
     */
    @Test
    fun getArtistList() {
        useCase.getArtistList()
        coVerify(exactly = 1) { (localArtistRepository).getArtistList() }
    }

    // endregion
}
