package com.example.musicdictionaryandroid.model.util

object UserInfoChangeListUtil {

    fun changeGender(code: Int): String {
        return when (code) {
            1 -> "男"
            2 -> "女"
            else -> "未選択"
        }
    }

    fun changeArea(code: Int): String {
        return when (code) {
            1 -> "東京"
            2 -> "神奈川"
            3 -> "千葉"
            4 -> "静岡"
            else -> "未選択"
        }
    }

    fun getBirthday(code: Int): String {
        return when (code) {
            1 -> "1996"
            2 -> "1997"
            3 -> "1998"
            4 -> "1999"
            5 -> "2000"
            else -> ""
        }
    }
    fun changeBirthdayString(code: String): Int {
        return when (code) {
            "1996" -> 1
            "1997" -> 2
            "1998" -> 3
            "1999" -> 4
            "2000" -> 5
            else -> 0
        }
    }
}
