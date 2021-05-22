package com.example.data.repository

import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.data.local.preferences.UserSharedPreferences
import com.example.domain.model.entity.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalUserRepositoryImpTest : BaseTestUnit() {

    // mock
    private lateinit var repository: LocalUserRepositoryImp
    private lateinit var userSharedPreferences: com.example.data.local.preferences.UserSharedPreferences

    // data
    private val successEmailUser = User("success@cc.ne.jp", "successEmailUser", 1, 1, "", 1)

    @Before
    fun setUp() {
        userSharedPreferences = mockk<com.example.data.local.preferences.UserSharedPreferences>().also {
            every { it.getUser() } returns successEmailUser
            every { it.setUser(any()) } returns Unit
            every { it.getEmail() } returns successEmailUser.email
            every { it.getName() } returns successEmailUser.name
            every { it.getGender() } returns successEmailUser.gender
            every { it.getArea() } returns successEmailUser.area
            every { it.getBirthday() } returns successEmailUser.birthday
            every { it.getFavorite() } returns successEmailUser.artistCount
            every { it.setFavorite(any()) } returns Unit
            every { it.removeAll() } returns Unit
        }
        repository = LocalUserRepositoryImp(userSharedPreferences)
    }

    @After
    fun tearDown() {
    }

    // region ユーザ情報設定

    /**
     * ユーザ情報設定
     *
     * 条件：なし
     * 結果：
     * ・Preferencesにユーザ情報を保存するメソッドが呼ばれること
     * ・引数で指定したユーザ情報がそのまま渡されること
     */
    @Test
    fun setUser() {
        repository.setUser(successEmailUser)
        verify(exactly = 1) { (userSharedPreferences).setUser(successEmailUser) }
    }

    // endregion

    // region ユーザ情報取得

    /**
     * ユーザ情報取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存しているユーザを取得するメソッドが呼ばれること
     * ・Preferencesに保存しているユーザが返ること
     */
    @Test
    fun getUser() {
        val result = repository.getUser()
        assertEquals(successEmailUser, result)
    }

    // endregion

    // region ユーザのメールアドレス取得

    /**
     * ユーザ情報取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存しているメールアドレスを取得するメソッドが呼ばれること
     * ・Preferencesに保存しているメールアドレスが返ること
     */
    @Test
    fun getEmail() {
        val result = repository.getEmail()
        assertEquals(successEmailUser.email, result)
    }

    // endregion

    // region ユーザ名取得

    /**
     * ユーザ名取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存しているユーザ名を取得するメソッドが呼ばれること
     * ・Preferencesに保存しているユーザ名が返ること
     */
    @Test
    fun getName() {
        val result = repository.getName()
        assertEquals(successEmailUser.name, result)
    }

    // endregion

    // region 性別取得

    /**
     * 性別取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存している性別を取得するメソッドが呼ばれること
     * ・Preferencesに保存している性別が返ること
     */
    @Test
    fun getGender() {
        val result = repository.getGender()
        assertEquals(successEmailUser.gender, result)
    }

    // endregion

    // region 地域取得

    /**
     * 地域取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存している地域を取得するメソッドが呼ばれること
     * ・Preferencesに保存している地域が返ること
     */
    @Test
    fun getArea() {
        val result = repository.getArea()
        assertEquals(successEmailUser.area, result)
    }

    // endregion

    // region 生年月日取得

    /**
     * 生年月日取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存している生年月日を取得するメソッドが呼ばれること
     * ・Preferencesに保存している生年月日が返ること
     */
    @Test
    fun getBirthday() {
        val result = repository.getBirthday()
        assertEquals(successEmailUser.birthday, result)
    }

    // endregion

    // region 登録済みアーティスト件数取得

    /**
     * 登録済みアーティスト件数取得
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存している登録済みアーティスト件数を取得するメソッドが呼ばれること
     * ・Preferencesに保存している登録済みアーティスト件数が返ること
     */
    @Test
    fun getArtistCount() {
        val result = repository.getFavorite()
        assertEquals(successEmailUser.artistCount, result)
    }

    // endregion

    // region 登録済みアーティスト件数設定

    /**
     * 登録済みアーティスト件数設定
     *
     * 条件：なし
     * 結果：
     * ・Preferencesに保存している登録済みアーティスト件数を設定するメソッドが呼ばれること
     * ・引数で私た登録済みアーティスト件数がそのまま設定されること
     */
    @Test
    fun setArtistCount() {
        repository.setFavorite(successEmailUser.artistCount)
        verify(exactly = 1) { (userSharedPreferences).setFavorite(successEmailUser.artistCount) }
    }

    // endregion

    // region ユーザ情報削除

    /**
     * ユーザ情報削除
     *
     * 条件：なし
     * 結果：Preferencesに保存しているユーザを削除するメソッドが呼ばれること
     */
    @Test
    fun remove() {
        repository.removeAll()
        verify(exactly = 1) { (userSharedPreferences).removeAll() }
    }

    // endregion
}
