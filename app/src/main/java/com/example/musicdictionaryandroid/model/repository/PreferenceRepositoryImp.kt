package com.example.musicdictionaryandroid.model.repository

import android.content.Context

object PreferenceRepositoryImp {

    private lateinit var context: Context

    fun init(context: Context){
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
        BIRTHDAY
    }

    fun setEmail(name: String) {
        setString(Key.EMAIL, name)
    }

    fun getEmail() : String? {
        return getString(Key.EMAIL)
    }

    fun setName(name: String) {
        setString(Key.NAME, name)
    }

    fun getName() : String? {
        return getString(Key.NAME)
    }

    fun setGender(gender: Int) {
        setInt(Key.GENDER, gender)
    }

    fun getGender() : String? {
        return getString(Key.GENDER)
    }

    fun setArea(area: Int) {
        setInt(Key.AREA, area)
    }

    fun getArea() : String? {
        return getString(Key.AREA)
    }

    fun setBirthday(name: String) {
        setString(Key.BIRTHDAY, name)
    }

    fun getBirthday() : String? {
        return getString(Key.BIRTHDAY)
    }

    fun removeAll() {
        remove(Key.EMAIL)
        remove(Key.NAME)
        remove(Key.BIRTHDAY)
        remove(Key.AREA)
        remove(Key.GENDER)
    }

    private fun setString(key: Key, value: String) {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key.name, value)
        editor.apply()
    }

    private fun getString(key: Key, default: String = "") : String? {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        return preferences.getString(key.name, default)
    }

    private fun setInt(key: Key, value: Int) {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(key.name, value)
        editor.apply()
    }

    private fun remove(key: Key) {
        val preferences = context.getSharedPreferences("mucisDictionary", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(key.name)
        editor.apply()
    }
}