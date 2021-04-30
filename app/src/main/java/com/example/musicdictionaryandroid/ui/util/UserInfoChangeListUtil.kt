package com.example.musicdictionaryandroid.ui.util

interface UserInfoChangeListUtil {

    fun changeGender(code: Int): String

    fun changeArea(code: Int): String

    fun getBirthday(code: Int): String

    fun changeBirthdayString(code: String): Int

    fun changeGenre1(index: Int): String

    fun changeGenre2(genre1Index: Int, genre2Index: Int): String

    fun changeVoice(index: Int): String

    fun changeLength(index: Int): String

    fun changeLyrics(index: Int): String
}
