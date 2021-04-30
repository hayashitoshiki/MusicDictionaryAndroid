package com.example.musicdictionaryandroid.data.local.preferences

import com.example.musicdictionaryandroid.data.local.preferences.PreferenceManager.remove
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.ui.util.UserInfoChangeListUtil

class UserSharedPreferencesImp(private val userInfoChangeListUtil: UserInfoChangeListUtil) : UserSharedPreferences {

    override fun setUser(user: User) {
        setEmail(user.email)
        setName(user.name)
        setGender(user.gender)
        setBirthday(userInfoChangeListUtil.changeBirthdayString(user.birthday))
        setArea(user.area)
        setFavorite(user.artist_count)
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
            userInfoChangeListUtil.getBirthday(getBirthday()),
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

    override fun setBirthday(value: Int) {
        PreferenceManager.setInt(PreferenceKey.IntKey.BIRTHDAY, value)
    }

    override fun getBirthday(): Int {
        return PreferenceManager.getInt(PreferenceKey.IntKey.BIRTHDAY)
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
        remove(PreferenceKey.IntKey.BIRTHDAY)
        remove(PreferenceKey.IntKey.AREA)
        remove(PreferenceKey.IntKey.GENDER)
        remove(PreferenceKey.IntKey.FAVORITE)
    }


}
