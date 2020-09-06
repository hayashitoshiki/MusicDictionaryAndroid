package com.example.musicdictionaryandroid.model.util

object BirthdayList {

    fun getBirthday(code: Int) : String{
        return when (code) {
            1 -> "1996"
            2 -> "1997"
            3 -> "1998"
            4 -> "1999"
            5 -> "2000"
            else -> ""
        }
    }
}