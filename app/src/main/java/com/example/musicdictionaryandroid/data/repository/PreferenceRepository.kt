package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.database.entity.User

interface PreferenceRepository {

    fun setUser(user: User)

    fun getUser(): User

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
