package com.example.musicdictionaryandroid.model.usecase

import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.repository.ApiServerRepository
import com.example.musicdictionaryandroid.model.repository.DataBaseRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.repository.PreferenceRepository
import com.example.musicdictionaryandroid.model.util.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class UserUseCaseImpTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var useCase: UserUseCaseImp
    private lateinit var apiRepository: ApiServerRepository
    private lateinit var dataBaseRepository: DataBaseRepository
    private lateinit var preferenceRepository: PreferenceRepository
    private lateinit var fireBaseRepository: FireBaseRepository

    private val user = User("test@com.jp", "testA", 1, 1, "2000/2/2", 1)
    private val artist = Artist(null, "artistA", 0, 0, 0, 0, 0, 0)
    private val artistForm = ArtistsForm(
        artist.name!!,
        artist.gender!!,
        artist.voice!!,
        artist.length!!,
        artist.lyrics!!,
        artist.genre1!!,
        artist.genre2!!
    )
    private val artistList = listOf(artistForm)
    private val artistArray = arrayListOf(artistForm, artistForm)
    private val artistListLiveData =  MutableLiveData<List<Artist>>(listOf(artist))
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
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
        fireBaseRepository = mockk<FireBaseRepository>().also {
            every { it.signIn(any(), any(), any(), any()) } returns Unit
            every { it.signOut() } returns Unit
            every { it.firstCheck() } returns true
            every { it.signUp(any(), any(), any(), any()) } returns Unit
            every { it.delete(any(), any()) } returns Unit
            every { it.getEmail() } returns "testA"
        }
        preferenceRepository = mockk<PreferenceRepository>().also {
            every { it.setFavorite(any()) } returns Unit
            every { it.getFavorite() } returns 1
            every { it.getEmail() } returns "testA"
            every { it.getName() } returns "tesetAname"
            every { it.getGender() } returns 1
            every { it.getArea() } returns 1
            every { it.getBirthday() } returns 1
            every { it.removeAll() } returns Unit
        }
        useCase = UserUseCaseImp(
            apiRepository, fireBaseRepository, dataBaseRepository, preferenceRepository, testScope,
            testDispatcher
        )
    }

    @After
    fun tearDown() {
    }

    // region 登録したユーザーの情報取得

    /**
     * 登録したユーザ情報取得
     * 条件：なし
     * 結果：APIからユーザ情報取得するメソッドが呼ばれること
     */
    @Test
    fun getUserByEmail() {
        runBlocking {
            val result = useCase.getUserByEmail("")
            coVerify(exactly = 1) { (apiRepository).getUserByEmail("") }
            assertEquals(Result.Success(user), result)
        }
    }

    // endregion

    // TODO: Flow導入時にテスト記載
    @Test
    fun createUser() {
    }

    // region ユーザ情報変更
    /**
     * ユーザ情報変更
     * 条件：なし
     * 結果：APIのユーザ情報変更のメソッドが呼ばれること
     */
    @Test
    fun changeUser() {
        runBlocking {
            useCase.changeUser(user, "testA")
            coVerify(exactly = 1) { (apiRepository).changeUser(user, "testA") }
        }
    }

    // endregion

    // region ユーザ削除

    /**
     * ユーザ削除
     * 条件：なし
     * 結果：Firebaseのユーザ削除のメソッドば呼ばれること
     */
    @Test
    fun delete() {
        useCase.delete({}, {})
        coVerify(exactly = 1) { (fireBaseRepository).delete(any(), any()) }
    }

    // endregion

    // region ログイン状態チェック
    /**
     * ログイン状態チェック
     * 条件：なし
     * 結果：firebaseのログイン状態チェックのメソッドが呼ばれること
     */
    @Test
    fun firstCheck() {
        useCase.firstCheck()
        coVerify(exactly = 1) { (fireBaseRepository).firstCheck() }
    }

    // endregion

    // TODO: Flow導入時にテスト記載
    @Test
    fun signIn() {
    }

    // region ログアウト

    /**
     * ログアウト
     * 条件：なし
     * 結果：
     * ・Firebaseからログアウトするメソッドが呼ばれること
     * ・ユーザ情報を削除するメソッドが呼ばれること
     * ・ローカルDBの全ての情報を削除するメソッドが呼ばれること
     */
    @Test
    fun signOut() {
        runBlocking {
            useCase.signOut()
            coVerify(exactly = 1) { (fireBaseRepository).signOut() }
            coVerify(exactly = 1) { (preferenceRepository).removeAll() }
            coVerify(exactly = 1) { (dataBaseRepository).deleteAll() }
        }
    }

    // endregion

    // region ユーザのメールアドレス取得

    /**
     * ユーザのEmailを取得する
     * 条件：なし
     * 結果：Firebaseからユーザのメールアドレスを取得するメソッドが呼ばれること
     */
    @Test
    fun getEmail() {
        useCase.getEmail()
        coVerify(exactly = 1) { (fireBaseRepository).getEmail() }
    }

    // endregion
}