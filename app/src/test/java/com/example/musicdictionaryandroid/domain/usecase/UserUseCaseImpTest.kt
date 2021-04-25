package com.example.musicdictionaryandroid.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.repository.ApiServerRepository
import com.example.musicdictionaryandroid.data.repository.DataBaseRepository
import com.example.musicdictionaryandroid.data.repository.FireBaseRepository
import com.example.musicdictionaryandroid.data.repository.PreferenceRepository
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
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
    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))

    private val artistList = listOf(artist)
    private val artistListLiveData = MutableLiveData(listOf(artist))
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        apiRepository = mockk<ApiServerRepository>().also {
            coEvery { it.getArtistsByEmail(successEmail) } returns Result.Success(artistList)
            coEvery { it.getArtistsByEmail(failureEmail) } returns failureResult
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
            coEvery { it.getArtistAll() } returns artistList
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