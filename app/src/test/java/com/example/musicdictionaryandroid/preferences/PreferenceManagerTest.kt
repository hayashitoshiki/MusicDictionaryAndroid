package com.example.musicdictionaryandroid.preferences

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Preference基盤の仕様
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class PreferenceManagerTest {

    private lateinit var context: Context
    private lateinit var preferenceManager: PreferenceManager

    private val intKey = PreferenceKey.IntKey.AREA
    private val stringKey = PreferenceKey.StringKey.EMAIL

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        preferenceManager = PreferenceManager(context)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    /**
     * Int型Key設定
     *
     * 条件：設定なし
     * 結果：デフォルト値（0）が返却されること
     */
    @Test
    fun intByDefault() {
        val result = preferenceManager.getInt(intKey)
        Assert.assertEquals(0, result)
    }

    /**
     * Int型Key設定
     *
     * 条件：Int型のキー設定
     * 結果：指定したキーのみ指定した値で設定されていること
     */
    @Test
    fun intBySet() {
        preferenceManager.setInt(intKey, 1)
        PreferenceKey.IntKey.values().forEach {
            val result = preferenceManager.getInt(it)
            if (it != intKey) {
                Assert.assertEquals(0, result)
            } else {
                Assert.assertEquals(1, result)
            }
        }
        PreferenceKey.StringKey.values().forEach {
            val result = preferenceManager.getString(it)
            Assert.assertEquals("", result)
        }
    }

    /**
     * int型Key削除
     *
     * 条件：int型のキー削除
     * 結果：指定したキーのみ値が削除されていること
     */
    @Test
    fun intByRemove() {
        val intValue = 1
        val stringValue = "test"

        PreferenceKey.StringKey.values().forEach {
            preferenceManager.setString(it, stringValue)
        }
        PreferenceKey.IntKey.values().forEach {
            preferenceManager.setInt(it, intValue)
        }
        preferenceManager.remove(intKey)

        PreferenceKey.StringKey.values().forEach {
            val result = preferenceManager.getString(it)
            Assert.assertEquals(stringValue, result)
        }
        PreferenceKey.IntKey.values().forEach {
            val result = preferenceManager.getInt(it)
            if (it == intKey) {
                Assert.assertEquals(0, result)
            } else {
                Assert.assertEquals(intValue, result)
            }
        }
    }

    /**
     * String型Key設定
     *
     * 条件：設定なし
     * 結果：デフォルト値（""）が返却されること
     */
    @Test
    fun stringByDefault() {
        val result = preferenceManager.getString(stringKey)
        Assert.assertEquals("", result)
    }

    /**
     * String型Key設定
     *
     * 条件：String型のキー設定
     * 結果：指定したキーのみ指定した値で設定されていること
     */
    @Test
    fun stringBySet() {
        preferenceManager.setString(stringKey, "test")
        PreferenceKey.StringKey.values().forEach {
            val result = preferenceManager.getString(it)
            if (it != stringKey) {
                Assert.assertEquals("", result)
            } else {
                Assert.assertEquals("test", result)
            }
        }
        PreferenceKey.IntKey.values().forEach {
            val result = preferenceManager.getInt(it)
            Assert.assertEquals(0, result)
        }
    }

    /**
     * String型Key削除
     *
     * 条件：String型のキー削除
     * 結果：指定したキーのみ値が削除されていること
     */
    @Test
    fun stringByRemove() {
        val intValue = 1
        val stringValue = "test"

        PreferenceKey.StringKey.values().forEach {
            preferenceManager.setString(it, stringValue)
        }
        PreferenceKey.IntKey.values().forEach {
            preferenceManager.setInt(it, intValue)
        }
        preferenceManager.remove(stringKey)

        PreferenceKey.StringKey.values().forEach {
            val result = preferenceManager.getString(it)
            if (it == stringKey) {
                Assert.assertEquals("", result)
            } else {
                Assert.assertEquals(stringValue, result)
            }
        }
        PreferenceKey.IntKey.values().forEach {
            val result = preferenceManager.getInt(it)
            Assert.assertEquals(intValue, result)
        }
    }
}
