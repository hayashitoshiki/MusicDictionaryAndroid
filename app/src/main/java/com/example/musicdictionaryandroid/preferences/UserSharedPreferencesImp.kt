package com.example.musicdictionaryandroid.preferences

import com.example.data.local.preferences.UserSharedPreferences
import com.example.domain.model.entity.User

class UserSharedPreferencesImp(private val preferenceManager: PreferenceManager) : UserSharedPreferences {

    override fun setUser(user: User) {
        setEmail(user.email)
        setName(user.name)
        setGender(user.gender)
        setBirthday(user.birthday)
        setArea(user.area)
        setFavorite(user.artistCount)
    }

    override fun setEmail(value: String) {
        preferenceManager.setString(PreferenceKey.StringKey.EMAIL, value)
    }

    override fun getEmail(): String = preferenceManager.getString(PreferenceKey.StringKey.EMAIL)

    override fun setName(value: String) {
        preferenceManager.setString(PreferenceKey.StringKey.NAME, value)
    }

    override fun getUser(): User {
        return User(
            getEmail(),
            getName(),
            getGender(),
            getArea(),
            getBirthday(),
            getFavorite()
        )
    }

    override fun getName(): String = preferenceManager.getString(PreferenceKey.StringKey.NAME)

    override fun setGender(value: Int) {
        preferenceManager.setInt(PreferenceKey.IntKey.GENDER, value)
    }

    override fun getGender(): Int {
        return preferenceManager.getInt(PreferenceKey.IntKey.GENDER)
    }

    override fun setArea(value: Int) {
        preferenceManager.setInt(PreferenceKey.IntKey.AREA, value)
    }

    override fun getArea(): Int {
        return preferenceManager.getInt(PreferenceKey.IntKey.AREA)
    }

    override fun setBirthday(value: String) {
        preferenceManager.setString(PreferenceKey.StringKey.BIRTHDAY, value)
    }

    override fun getBirthday(): String {
        return preferenceManager.getString(PreferenceKey.StringKey.BIRTHDAY)
    }

    override fun setFavorite(value: Int) {
        preferenceManager.setInt(PreferenceKey.IntKey.FAVORITE, value)
    }

    override fun getFavorite(): Int {
        return preferenceManager.getInt(PreferenceKey.IntKey.FAVORITE)
    }

    override fun removeAll() {
        preferenceManager.remove(PreferenceKey.StringKey.EMAIL)
        preferenceManager.remove(PreferenceKey.StringKey.NAME)
        preferenceManager.remove(PreferenceKey.StringKey.BIRTHDAY)
        preferenceManager.remove(PreferenceKey.IntKey.AREA)
        preferenceManager.remove(PreferenceKey.IntKey.GENDER)
        preferenceManager.remove(PreferenceKey.IntKey.FAVORITE)
    }
}
