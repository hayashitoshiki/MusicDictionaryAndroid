package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.data.remote.firebase.FireBaseService
import com.example.musicdictionaryandroid.data.remote.network.Provider
import com.example.musicdictionaryandroid.data.remote.network.dto.StatusDto
import com.example.musicdictionaryandroid.data.remote.network.dto.StatusResponseDto
import com.example.musicdictionaryandroid.data.remote.network.dto.UserDto
import com.example.musicdictionaryandroid.data.remote.network.dto.UserResponseDto
import com.example.musicdictionaryandroid.data.remote.network.service.MusicDictionaryService
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.squareup.moshi.Moshi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteUserRepositoryImpTest : BaseTestUnit() {

    // mock
    private lateinit var repository: RemoteUserRepositoryImp
    private lateinit var provider: Provider
    private lateinit var fireBaseService: FireBaseService
    private lateinit var musicDictionaryApi: MusicDictionaryService

    // data
    private val successEmail = "success"
    private val successPassword = "successPassword"
    private val successUserDto = UserDto(successEmail, "TestA", 1, 1, "", 1)
    private val successUser = convertUserFromUserDto(successUserDto)
    private val failureEmail = "failure"
    private val successUserJson: String = Moshi.Builder().build().adapter(User::class.java).toJson(successUser)
    private val statusDto = StatusDto(200, "Success")
    private val statusResponseDto = StatusResponseDto(statusDto)
    private val userResponseDto = UserResponseDto(statusDto, successUserDto)
    private val successUserResult = Result.Success(successUser)
    private val successResult = Result.Success("Success")
    private val successFlow = flow { emit(successResult) }

    @Before
    fun setUp() {
        musicDictionaryApi = mockk<MusicDictionaryService>().also {
            coEvery { it.getUserByEmail(successEmail) } returns userResponseDto
            coEvery { it.createUser(successUserJson) } returns statusResponseDto
        }
        fireBaseService = mockk<FireBaseService>().also {
            coEvery { it.firstCheck() } returns true
            coEvery { it.signIn(any(), any()) } returns successFlow
            coEvery { it.signUp(any(), any()) } returns successFlow
            coEvery { it.signOut() } returns Unit
            coEvery { it.delete() } returns successFlow
        }
        provider = mockk<Provider>().also {
            every { it.musicDictionaryApi() } returns musicDictionaryApi
        }
        repository = RemoteUserRepositoryImp(provider, fireBaseService, testDispatcher)
    }

    @After
    fun tearDown() {
    }

    // region ユーザー取得

    /**
     * ユーザ取得
     *
     * 条件：APIが成功を返すこと
     * 結果：APIから取得したユーザ情報がドメインモデル形式に変換されて返ること
     */
    @Test
    fun getUserByEmailBySuccess() {
        runBlocking {
            val result = repository.getUserByEmail(successEmail)
            assertEquals(successUserResult, result)
        }
    }

    /**
     * アーティスト検索
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun getUserByEmailByFailure() {
        runBlocking {
            val result = repository.getUserByEmail(failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region ユーザー登録

    /**
     * ユーザ登録
     *
     * 条件：APIが成功を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun createUserBySuccess() {
        runBlocking {
            val result = repository.createUser(successUserJson)
            assertEquals(successResult, result)
        }
    }

    /**
     * ユーザ情報取得
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun createUserByFailure() {
        runBlocking {
            val result = repository.createUser(failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region 自動ログイン認証

    /**
     * 自動ログイン認証
     *
     * 条件：なし
     * 結果：Firebaseの自動ロウグイン認証メソッドが呼ばれること
     */
    @Test
    fun firstCheck() {
        repository.firstCheck()
        verify(exactly = 1) { (fireBaseService).firstCheck() }
    }

    // endregion

    // regionログイン機能

    /**
     * ログイン認証
     *
     * 条件：なし
     * 結果：Firebaseのロウグイン認証メソッドが呼ばれること
     */
    @Test
    fun signIn() {
        runBlocking {
            val result = repository.signIn(successEmail, successPassword).first()
            verify(exactly = 1) { (fireBaseService).signIn(successEmail, successPassword) }
            assertEquals(successFlow.first(), result)
        }
    }

    // endregion

    // region ログアウト

    /**
     * ログアウト
     *
     * 条件：なし
     * 結果：Firebaseのログアウトメソッドが呼ばれること
     */
    @Test
    fun signOut() {
        repository.signOut()
        verify(exactly = 1) { (fireBaseService).signOut() }
    }

    // endregion

    // region アカウント作成

    /**
     * アカウント作成
     *
     * 条件：なし
     * 結果：Firebaseのアカウント作成メソッドが呼ばれること
     */
    @Test
    fun signUp() {
        runBlocking {
            val result = repository.signUp(successEmail, successPassword).first()
            verify(exactly = 1) { (fireBaseService).signUp(successEmail, successPassword) }
            assertEquals(successFlow.first(), result)
        }
    }

    // endregion

    // region アカウント削除

    /**
     * アカウント削除
     *
     * 条件：なし
     * 結果：Firebaseのアカウント削除メソッドが呼ばれること
     */
    @Test
    fun delete() {
        runBlocking {
            val result = repository.delete().first()
            verify(exactly = 1) { (fireBaseService).delete() }
            assertEquals(successFlow.first(), result)
        }
    }

    // endregion

    // region converter

    // ユーザDtoからユーザモデルへ変換
    private fun convertUserFromUserDto(userDto: UserDto): User {
        val email = userDto.email
        val name = userDto.name
        val gender = userDto.gender
        val area = userDto.area
        val birthday = userDto.birthday
        val artistCount = userDto.artist_count
        return User(email, name, gender, area, birthday, artistCount)
    }

    // endregion
}