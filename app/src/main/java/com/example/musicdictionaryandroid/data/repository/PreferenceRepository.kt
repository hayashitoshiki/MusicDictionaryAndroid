package com.example.musicdictionaryandroid.data.repository

interface PreferenceRepository {

    fun setEmail(value: String)

    fun getEmail(): String?

    fun setName(value: String)

    fun getName(): String?

    fun setGender(value: Int)

    fun getGender(): Int

    fun setArea(value: Int)

    fun getArea(): Int

    fun setBirthday(value: Int)

    fun getBirthday(): Int

    fun setFavorite(value: Int)

    fun getFavorite(): Int

    fun removeAll()
}
