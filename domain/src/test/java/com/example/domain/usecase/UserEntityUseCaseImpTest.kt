package com.example.domain.usecase

import com.example.domain.BaseTestUnit
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.User
import com.example.domain.model.value.*
import com.example.domain.repository.LocalArtistRepository
import com.example.domain.repository.LocalBookmarkArtistRepository
import com.example.domain.repository.LocalUserRepository
import com.example.domain.repository.RemoteUserRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserEntityUseCaseImpTest : BaseTestUnit() {

    private lateinit var useCase: UserUseCaseImp
    private lateinit var localBookmarkArtistRepository: LocalBookmarkArtistRepository
    private lateinit var localArtistRepository: LocalArtistRepository
    private lateinit var localUserRepository: LocalUserRepository
    private lateinit var remoteUserRepository: RemoteUserRepository

    private val successUser = User("test@com.jp", "testSuccess", 1, 1, "2000/2/2", 1)
    private val failureUser = User("test@com.jp", "testFailure", 1, 1, "2000/2/2", 1)

    private val artist = Artist("test", Gender.MAN, Voice(0), Length(0), Lyrics(0), Genre1(0), Genre2(0))

     private val artistListFlow = flow { emit(listOf(artist)) }
    private val failureResult = Result.Error(IllegalArgumentException(""))
    private val successEmail = "success"
    private val failureEmail = "Failure"
    private val successResult = Result.Success("Success")
    private val createSuccessResult = Result.Success(successUser)

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        localBookmarkArtistRepository = mockk<LocalBookmarkArtistRepository>().also {
            coEvery { it.deleteAll() } returns Unit
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
        remoteUserRepository = mockk<RemoteUserRepository>().also {
            every { it.signIn(any(), successEmail) } returns flow { emit(successResult) }
            every { it.signIn(any(), failureEmail) } returns flow { emit(failureResult) }
            every { it.signOut() } returns Unit
            every { it.firstCheck() } returns true
            every { it.signUp(successEmail, any()) } returns flow { emit(successResult) }
            every { it.signUp(failureEmail, any()) } returns flow { emit(failureResult) }
            every { it.delete() } returns flow { emit(successResult) }
            coEvery { it.getUserByEmail(successEmail) } returns createSuccessResult
            coEvery { it.getUserByEmail(failureEmail) } returns failureResult
            coEvery { it.createUser(successUser) } returns successResult
            coEvery { it.createUser(failureUser) } returns failureResult
        }
        localUserRepository = mockk<LocalUserRepository>().also {
            every { it.setUser(any()) } returns Unit
            every { it.getUser() } returns successUser
            every { it.setFavorite(any()) } returns Unit
            every { it.getFavorite() } returns 1
            every { it.getEmail() } returns "testA"
            every { it.getName() } returns "testAName"
            every { it.getGender() } returns 1
            every { it.getArea() } returns 1
            every { it.getBirthday() } returns successUser.birthday
            every { it.removeAll() } returns Unit
        }
        useCase = UserUseCaseImp(
            remoteUserRepository,
            localUserRepository,
            localArtistRepository,
            localBookmarkArtistRepository
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
        verify(exactly = 1) { localUserRepository.getUser() }
        assertEquals(successUser, result)
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
            coVerify(exactly = 1) { (remoteUserRepository).createUser(successUser) }
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
            coVerify(exactly = 1) { (remoteUserRepository).createUser(failureUser) }
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
            coVerify(exactly = 0) { (remoteUserRepository).createUser(any()) }
            assertEquals(failureResult, result)
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
            coVerify(exactly = 1) { (remoteUserRepository).delete() }
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
        coVerify(exactly = 1) { (remoteUserRepository).firstCheck() }
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
            coVerify(exactly = 1) { (remoteUserRepository).getUserByEmail(successEmail) }
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
            coVerify(exactly = 1) { (remoteUserRepository).getUserByEmail(failureEmail) }
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
            coVerify(exactly = 0) { (remoteUserRepository).getUserByEmail(successEmail) }
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
            coVerify(exactly = 1) { (remoteUserRepository).signOut() }
            coVerify(exactly = 1) { (localUserRepository).removeAll() }
            coVerify(exactly = 1) { (localArtistRepository).deleteAll() }
            coVerify(exactly = 1) { (localBookmarkArtistRepository).deleteAll() }
        }
    }

    // endregion
}
