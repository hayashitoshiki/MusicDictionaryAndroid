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
import com.squareup.moshi.Moshi
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Assert.assertEquals
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

    private val successUser = User("test@com.jp", "testSuccess", 1, 1, "2000/2/2", 1)
    private val failureUser = User("test@com.jp", "testFailure", 1, 1, "2000/2/2", 1)

    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))

    private val successJson: String = Moshi.Builder().build().adapter(User::class.java).toJson(successUser)
    private val failureJson: String = Moshi.Builder().build().adapter(User::class.java).toJson(failureUser)
    private val artistList = listOf(artist)
    private val artistListLiveData = MutableLiveData(listOf(artist))
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"
    private val successResult = Result.Success("Success")
    private val createSuccessResult = Result.Success(successUser)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        apiRepository = mockk<ApiServerRepository>().also {
            coEvery { it.getArtistsByEmail(successEmail) } returns Result.Success(artistList)
            coEvery { it.getArtistsByEmail(failureEmail) } returns failureResult
            coEvery { it.getUserByEmail(successEmail) } returns createSuccessResult
            coEvery { it.getUserByEmail(failureEmail) } returns failureResult
            coEvery { it.createUser(successJson) } returns successResult
            coEvery { it.createUser(failureJson) } returns failureResult
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
            every { it.signIn(any(), successEmail) } returns flow { emit(successResult) }
            every { it.signIn(any(), failureEmail) } returns flow { emit(failureResult) }
            every { it.signOut() } returns Unit
            every { it.firstCheck() } returns true
            every { it.signUp(successEmail, any()) } returns flow { emit(successResult) }
            every { it.signUp(failureEmail, any()) } returns flow { emit(failureResult) }
            every { it.delete() } returns flow { emit(successResult) }
            every { it.getEmail() } returns "testA"
        }
        preferenceRepository = mockk<PreferenceRepository>().also {
            every { it.setUser(any()) } returns Unit
            every { it.getUser() } returns successUser
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

    // region ユーザ情報取得

    /**
     * ユーザ情報取得
     * 条件：なし
     * 結果：preferenceRepositoryのユーザ情報取得メソッドが呼ばれること
     */
    @Test
    fun getUserByCache() {
        val result = useCase.getUserByCache()
        verify(exactly = 1) { preferenceRepository.getUser() }
        assertEquals(successUser, result)
    }

    // endregion

    // region 登録したユーザーの情報取得

    /**
     * 登録したユーザ情報取得
     * 条件：なし
     * 結果：APIからユーザ情報取得するメソッドが呼ばれること
     */
    @Test
    fun getUserByEmail() {
        runBlocking {
            val result = useCase.getUserByEmail(successEmail)
            coVerify(exactly = 1) { (apiRepository).getUserByEmail(successEmail) }
            assertEquals(createSuccessResult, result)
        }
    }

    // endregion

    // region ユーザ登録

    /**
     * ユーザ登録
     * 条件：全て成功
     * 結果：成功Resultが帰ること
     */
    @Test
    fun createUserSuccess() {
        runBlocking {
            val result = useCase.createUser(successEmail, successEmail, successUser).first()
            coVerify(exactly = 1) { (apiRepository).createUser(successJson) }
            assertEquals(successResult, result)
        }
    }

    /**
     * ユーザ登録
     * 条件：自作Apiでエラー
     * 結果：ApiのエラーResultが帰ること
     */
    @Test
    fun createUserApiError() {
        runBlocking {
            val result = useCase.createUser(successEmail, successEmail, failureUser).first()
            coVerify(exactly = 1) { (apiRepository).createUser(failureJson) }
            assertEquals(failureResult, result)
        }
    }

    /**
     * ユーザ登録
     * 条件：Firebase認証でエラー
     * 結果：FireBaseのエラーResultが帰ること
     */
    @Test
    fun createUserFirebaseError() {
        runBlocking {
            val result = useCase.createUser(failureEmail, failureEmail, successUser).first()
            coVerify(exactly = 0) { (apiRepository).createUser(any()) }
            assertEquals(failureResult, result)
        }
    }

    // endregion

    // region ユーザ情報変更

    /**
     * ユーザ情報変更
     * 条件：なし
     * 結果：APIのユーザ情報変更のメソッドが呼ばれること
     */
    @Test
    fun changeUser() {
        runBlocking {
            useCase.changeUser(successUser, "testA")
            coVerify(exactly = 1) { (apiRepository).changeUser(successUser, "testA") }
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
        runBlocking {
            val result = useCase.delete().first()
            coVerify(exactly = 1) { (fireBaseRepository).delete() }
            assertEquals(successResult, result)
        }
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

    // region ログイン

    // TODO: Flow導入時にテスト記載
    /**
     * ログイン処理
     * 条件：FirebaseとApiのログインが成功
     * 結果：成功Resultが返ること
     */
    @Test
    fun signIn() {
        runBlocking {
            val result = useCase.signIn(successEmail, successEmail).first()
            coVerify(exactly = 1) { (apiRepository).getUserByEmail(successEmail) }
            assertEquals(successResult, result)
        }
    }

    /**
     * ログイン処理
     * 条件：Apiのログインが失敗
     * 結果：失敗Resultが返ること
     */
    @Test
    fun signInApiError() {
        runBlocking {
            val result = useCase.signIn(failureEmail, successEmail).first()
            coVerify(exactly = 1) { (apiRepository).getUserByEmail(failureEmail) }
            assertEquals(failureResult, result)
        }
    }

    /**
     * ログイン処理
     * 条件：Firebaseのログインが失敗
     * 結果：失敗Resultが返ること
     */
    @Test
    fun signInFirebaseError() {
        runBlocking {
            val result = useCase.signIn(failureEmail, failureEmail).first()
            coVerify(exactly = 0) { (apiRepository).getUserByEmail(successEmail) }
            assertEquals(failureResult, result)
        }
    }

    // endregion

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