package com.example.musicdictionaryandroid.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.repository.ApiServerRepository
import com.example.musicdictionaryandroid.data.repository.DataBaseRepository
import com.example.musicdictionaryandroid.data.repository.PreferenceRepository
import com.example.musicdictionaryandroid.data.util.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ArtistEntityUseCaseImpTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var useCase: ArtistUseCaseImp
    private lateinit var apiRepository: ApiServerRepository
    private lateinit var dataBaseRepository: DataBaseRepository
    private lateinit var preferenceRepository: PreferenceRepository



    private val user = User("test@com.jp","testA", 1 ,1, "2000/2/2", 1)
    private val artist = ArtistEntity(null, "artistA", 0, 0, 0, 0, 0, 0)
    private val artistForm = ArtistsDto(artist.name!!, artist.gender!!, artist.voice!!, artist.length!!, artist.lyrics!!, artist.genre1!!, artist.genre2!!)
    private val artistList = listOf(artistForm)
    private val artistArray = arrayListOf(artistForm, artistForm)
    private val artistListLiveData =  MutableLiveData<List<ArtistEntity>>(listOf(artist))
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        apiRepository = mockk<ApiServerRepository>().also {
            coEvery { it.getArtistsBy(any()) } returns Result.Success(artistList)
            coEvery { it.getArtistsByRecommend(any()) } returns Result.Success(artistList)
            coEvery { it.getArtistsBySoaring() } returns Result.Success(artistList)
            coEvery { it.getArtistsByEmail(successEmail) } returns Result.Success(artistList)
            coEvery { it.getArtistsByEmail(failureEmail) } returns failureResult
            coEvery { it.addArtist(any(), successEmail) } returns Result.Success(artistForm)
            coEvery { it.addArtist(any(), failureEmail) } returns failureResult
            coEvery { it.updateArtist(any(), successEmail) } returns Result.Success(artistForm)
            coEvery { it.updateArtist(any(), failureEmail) } returns failureResult
            coEvery { it.deleteArtist(any(), successEmail) } returns Result.Success(CallBackData())
            coEvery { it.deleteArtist(any(), failureEmail) } returns failureResult
            coEvery { it.getUserByEmail(any()) } returns Result.Success(user)
            coEvery { it.createUser(any()) } returns Result.Success(CallBackData())
            coEvery { it.changeUser(any(), any()) } returns Result.Success(CallBackData())
        }
        dataBaseRepository = mockk<DataBaseRepository>().also {
            coEvery { it.addArtist(any()) } returns Unit
            coEvery { it.deleteArtist(any()) } returns Unit
            coEvery { it.deleteAll() } returns Unit
            coEvery { it.updateArtist(any()) } returns Unit
            coEvery { it.updateAll(any()) } returns Unit
            coEvery { it.findByName(any()) } returns artist
            coEvery { it.getArtistAll() } returns artistArray
            coEvery { it.getArtistList() } returns artistListLiveData
        }
        preferenceRepository = mockk<PreferenceRepository>().also {
            every { it.setFavorite(any()) } returns Unit
            every { it.getFavorite() } returns 1
        }
        useCase = ArtistUseCaseImp(apiRepository, dataBaseRepository, preferenceRepository, testScope, testDispatcher)
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
            val result = useCase.getArtistsBy(artistForm)
            assertEquals(Result.Success(artistList), result)
            coVerify(exactly = 1) { (apiRepository).getArtistsBy(artistForm) }
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
            val result = useCase.getArtistsByRecommend("test@com.jp")
            assertEquals(Result.Success(artistList), result)
            coVerify(exactly = 1) { (apiRepository).getArtistsByRecommend("test@com.jp") }
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
            assertEquals(Result.Success(artistList), result)
            coVerify(exactly = 1) { (apiRepository).getArtistsBySoaring() }
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
            val result = useCase.getArtistsByEmail(successEmail)
            assertEquals(Result.Success(artistList), result)
            coVerify(exactly = 1) { (apiRepository).getArtistsByEmail(successEmail) }
            coVerify(exactly = 1) { (dataBaseRepository).updateAll(artistList) }
            coVerify(exactly = 1) { (preferenceRepository).setFavorite(artistList.size) }
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
            useCase.getArtistsByEmail(failureEmail)
            coVerify(exactly = 1) { (apiRepository).getArtistsByEmail(failureEmail) }
            coVerify(exactly = 0) { (dataBaseRepository).updateAll(artistList) }
            coVerify(exactly = 0) { (preferenceRepository).setFavorite(artistList.size) }
            coVerify(exactly = 1) { (dataBaseRepository).getArtistAll() }
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
            useCase.addArtist(artist, successEmail)
            coVerify(exactly = 1) { (apiRepository).addArtist(artistForm, successEmail) }
            coVerify(exactly = 1) { (dataBaseRepository).addArtist(artistForm) }
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
            val result = useCase.addArtist(artist, failureEmail)
            assertEquals(failureResult, result)
            coVerify(exactly = 1) { (apiRepository).addArtist(artistForm, failureEmail) }
            coVerify(exactly = 0) { (dataBaseRepository).addArtist(artistForm) }
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
            useCase.updateArtist(artist, successEmail)
            coVerify(exactly = 1) { (apiRepository).updateArtist(artistForm, successEmail) }
            coVerify(exactly = 1) { (dataBaseRepository).updateArtist(artistForm) }
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
            useCase.updateArtist(artist, failureEmail)
            coVerify(exactly = 1) { (apiRepository).updateArtist(artistForm, failureEmail) }
            coVerify(exactly = 0) { (dataBaseRepository).updateArtist(artistForm) }
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
            useCase.deleteArtist(artist.name!!, successEmail)
            coVerify(exactly = 1) { (apiRepository).deleteArtist(artist.name!!, successEmail) }
            coVerify(exactly = 1) { (dataBaseRepository).deleteArtist(artist.name!!) }
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
            useCase.deleteArtist(artist.name!!, failureEmail)
            coVerify(exactly = 1) { (apiRepository).deleteArtist(artist.name!!, failureEmail) }
            coVerify(exactly = 0) { (dataBaseRepository).deleteArtist(artist.name!!) }
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
        coVerify(exactly = 1) { (dataBaseRepository).getArtistList() }
    }

    // endregion
}