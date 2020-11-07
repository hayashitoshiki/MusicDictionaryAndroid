package com.example.musicdictionaryandroid.model.repository

import android.content.Context

/**
 * SharedPreferences管理
 */
object PreferenceRepositoryImp {

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }
    private enum class Key {
        // Eメール
        EMAIL,
        // 名前
        NAME,
        // 性別
        GENDER,
        // 地域
        AREA,
        // 生年月日
        BIRTHDAY,
        // 登録数
        FAVORITE
    }

    fun setEmail(value: String) {
        setString(Key.EMAIL, value)
    }

    fun getEmail(): String? {
        return getString(Key.EMAIL)
    }

    fun setName(value: String) {
        setString(Key.NAME, value)
    }

    fun getName(): String? {
        return getString(Key.NAME)
    }

    fun setGender(value: Int) {
        setInt(Key.GENDER, value)
    }

    fun getGender(): Int {
        return getInt(Key.GENDER)
    }

    fun setArea(value: Int) {
        setInt(Key.AREA, value)
    }

    fun getArea(): Int {
        return getInt(Key.AREA)
    }

    fun setBirthday(value: Int) {
        setInt(Key.BIRTHDAY, value)
    }

    fun getBirthday(): Int {
        return getInt(Key.BIRTHDAY)
    }

    fun setFavorite(value: Int) {
        setInt(Key.FAVORITE, value)
    }

    fun getFavorite(): Int {
        return getInt(Key.FAVORITE)
    }

    fun removeAll() {
        remove(Key.EMAIL)
        remove(Key.NAME)
        remove(Key.BIRTHDAY)
        remove(Key.AREA)
        remove(Key.GENDER)
        remove(Key.FAVORITE)
    }

    private fun setString(key: Key, value: String) {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key.name, value)
        editor.apply()
    }

    private fun getString(key: Key, default: String = ""): String? {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        return preferences.getString(key.name, default)
    }

    private fun setInt(key: Key, value: Int) {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(key.name, value)
        editor.apply()
    }
    private fun getInt(key: Key, default: Int = 0): Int {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        return preferences.getInt(key.name, default)
    }

    private fun remove(key: Key) {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(key.name)
        editor.apply()
    }
}
