package com.example.musicdictionaryandroid.preferences

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.preferences.UserSharedPreferences
import com.example.domain.model.entity.User
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * ユーザ情報設定の仕様
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class UserSharedPreferencesImpTest {

    private lateinit var context: Context
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var userSharedPreferencesImp: UserSharedPreferences

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        preferenceManager = PreferenceManager(context)
        userSharedPreferencesImp = UserSharedPreferencesImp(preferenceManager)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    /**
     * ユーザ名設定
     *
     * 条件：ユーザ名を設定
     * 結果：設定したユーザ名を取得できること
     */
    @Test
    fun nameTest() {
        val userName = "testA"
        userSharedPreferencesImp.setName(userName)
        val result = userSharedPreferencesImp.getName()
        assertEquals(userName, result)
    }

    /**
     * Email設定
     *
     * 条件：Emailを設定
     * 結果：設定したEmailを取得できること
     */
    @Test
    fun emailTest() {
        val email = "test@test.test"
        userSharedPreferencesImp.setEmail(email)
        val result = userSharedPreferencesImp.getEmail()
        assertEquals(email, result)
    }

    /**
     * 性別設定
     *
     * 条件：性別を設定
     * 結果：設定した性別を取得できること
     */
    @Test
    fun genderTest() {
        val gender = 1
        userSharedPreferencesImp.setGender(gender)
        val result = userSharedPreferencesImp.getGender()
        assertEquals(gender, result)
    }

    /**
     * 地域設定
     *
     * 条件：地域を設定
     * 結果：設定した地域を取得できること
     */
    @Test
    fun areaTest() {
        val area = 1
        userSharedPreferencesImp.setArea(area)
        val result = userSharedPreferencesImp.getArea()
        assertEquals(area, result)
    }

    /**
     * 生年月日設定
     *
     * 条件：生年月日を設定
     * 結果：設定した生年月日を取得できること
     */
    @Test
    fun birthdayTest() {
        val birthday = "1996/5/12"
        userSharedPreferencesImp.setBirthday(birthday)
        val result = userSharedPreferencesImp.getBirthday()
        assertEquals(birthday, result)
    }

    /**
     * お気に入り設定
     *
     * 条件：お気に入りを設定
     * 結果：設定したお気に入りを取得できること
     */
    @Test
    fun favoriteTest() {
        val favorite = 5
        userSharedPreferencesImp.setFavorite(favorite)
        val result = userSharedPreferencesImp.getFavorite()
        assertEquals(favorite, result)
    }

    /**
     * ユーザ情報設定
     *
     * 条件：ユーザ情報を設定
     * 結果：設定したユーザ情報を取得できること
     */
    @Test
    fun userTest() {
        val user = User("test@test", "TestNamw", 1, 1, "1996-5-12", 1)
        userSharedPreferencesImp.setUser(user)
        val resultEmail = userSharedPreferencesImp.getEmail()
        val resultName = userSharedPreferencesImp.getName()
        val resultGender = userSharedPreferencesImp.getGender()
        val resultArea = userSharedPreferencesImp.getArea()
        val resultBirthday = userSharedPreferencesImp.getBirthday()
        val resultFavorite = userSharedPreferencesImp.getFavorite()
        val resultUser = userSharedPreferencesImp.getUser()
        assertEquals(resultEmail, resultUser.email)
        assertEquals(resultName, resultUser.name)
        assertEquals(resultGender, resultUser.gender)
        assertEquals(resultArea, resultUser.area)
        assertEquals(resultBirthday, resultUser.birthday)
        assertEquals(resultFavorite, resultUser.artistCount)
        assertEquals(user, resultUser)
    }

    /**
     * ユーザ情報削除
     *
     * 条件：ユーザ情報を設定後削除
     * 結果：設定したユーザ情報が全て削除されていること
     */
    @Test
    fun userDeleteTest() {
        val user = User("test@test", "TestNamw", 1, 1, "1996-5-12", 1)
        userSharedPreferencesImp.setUser(user)
        userSharedPreferencesImp.removeAll()
        val resultEmail = userSharedPreferencesImp.getEmail()
        val resultName = userSharedPreferencesImp.getName()
        val resultGender = userSharedPreferencesImp.getGender()
        val resultArea = userSharedPreferencesImp.getArea()
        val resultBirthday = userSharedPreferencesImp.getBirthday()
        val resultFavorite = userSharedPreferencesImp.getFavorite()
        val resultUser = userSharedPreferencesImp.getUser()
        assertNotEquals(user.email, resultEmail)
        assertNotEquals(user.name, resultName)
        assertNotEquals(user.gender, resultGender)
        assertNotEquals(user.area, resultArea)
        assertNotEquals(user.birthday, resultBirthday)
        assertNotEquals(user.artistCount, resultFavorite)
        assertNotEquals(user, resultUser)
    }
}
