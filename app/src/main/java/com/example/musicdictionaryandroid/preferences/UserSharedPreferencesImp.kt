package com.example.musicdictionaryandroid.preferences

import com.example.domain.model.entity.User
import com.example.musicdictionaryandroid.preferences.PreferenceManager.remove

class UserSharedPreferencesImp : com.example.data.local.preferences.UserSharedPreferences {

    override fun setUser(user: User) {
        setEmail(user.email)
        setName(user.name)
        setGender(user.gender)
        setBirthday(user.birthday)
        setArea(user.area)
        setFavorite(user.artistCount)
    }

    override fun setEmail(value: String) {
        PreferenceManager.setString(PreferenceKey.StringKey.EMAIL, value)
    }

    override fun getEmail(): String = PreferenceManager.getString(PreferenceKey.StringKey.EMAIL)

    override fun setName(value: String) {
        PreferenceManager.setString(PreferenceKey.StringKey.NAME, value)
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

    override fun getName(): String = PreferenceManager.getString(PreferenceKey.StringKey.NAME)

    override fun setGender(value: Int) {
        PreferenceManager.setInt(PreferenceKey.IntKey.GENDER, value)
    }

    override fun getGender(): Int {
        return PreferenceManager.getInt(PreferenceKey.IntKey.GENDER)
    }

    override fun setArea(value: Int) {
        PreferenceManager.setInt(PreferenceKey.IntKey.AREA, value)
    }

    override fun getArea(): Int {
        return PreferenceManager.getInt(PreferenceKey.IntKey.AREA)
    }

    override fun setBirthday(value: String) {
        PreferenceManager.setString(PreferenceKey.StringKey.BIRTHDAY, value)
    }

    override fun getBirthday(): String {
        return PreferenceManager.getString(PreferenceKey.StringKey.BIRTHDAY)
    }

    override fun setFavorite(value: Int) {
        PreferenceManager.setInt(PreferenceKey.IntKey.FAVORITE, value)
    }

    override fun getFavorite(): Int {
        return PreferenceManager.getInt(PreferenceKey.IntKey.FAVORITE)
    }

    override fun removeAll() {
        remove(PreferenceKey.StringKey.EMAIL)
        remove(PreferenceKey.StringKey.NAME)
        remove(PreferenceKey.StringKey.BIRTHDAY)
        remove(PreferenceKey.IntKey.AREA)
        remove(PreferenceKey.IntKey.GENDER)
        remove(PreferenceKey.IntKey.FAVORITE)
    }
}
