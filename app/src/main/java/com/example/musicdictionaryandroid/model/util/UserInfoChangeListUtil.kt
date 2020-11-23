package com.example.musicdictionaryandroid.model.util

import android.content.Context
import com.example.musicdictionaryandroid.R

/**
 * 各数値項目の変換
 */
object UserInfoChangeListUtil {

    private lateinit var context: Context

    // 絞り込みリスト
    private lateinit var mainGenreList: Array<String>
    private lateinit var subGenre1List: Array<String>
    private lateinit var subGenre2List: Array<String>
    private lateinit var subGenre3List: Array<String>
    private lateinit var subGenre4List: Array<String>
    private lateinit var subGenre5List: Array<String>
    private lateinit var subGenre6List: Array<String>

    fun init(context: Context) {
        this.context = context
        mainGenreList = this.context.resources.getStringArray(R.array.genre1_spinner_list)
        subGenre1List = this.context.resources.getStringArray(R.array.genre12_spinner_list)
        subGenre2List = this.context.resources.getStringArray(R.array.genre22_spinner_list)
        subGenre3List = this.context.resources.getStringArray(R.array.genre32_spinner_list)
        subGenre4List = this.context.resources.getStringArray(R.array.genre42_spinner_list)
        subGenre5List = this.context.resources.getStringArray(R.array.genre52_spinner_list)
        subGenre6List = this.context.resources.getStringArray(R.array.genre62_spinner_list)
    }

    fun changeGender(code: Int): String {
        return when (code) {
            1 -> "男性"
            2 -> "女性"
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

    fun changeGenre1(index: Int): String {
        return mainGenreList[index]
    }

    fun changeGenre2(genre1Index: Int, genre2Index: Int): String {
        return when (genre1Index) {
            1 -> subGenre1List[genre2Index]
            2 -> subGenre2List[genre2Index]
            3 -> subGenre3List[genre2Index]
            4 -> subGenre4List[genre2Index]
            5 -> subGenre5List[genre2Index]
            6 -> subGenre6List[genre2Index]
            else -> "未選択"
        }
    }

    fun changeVoice(index: Int): String {
        return when (index) {
            1 -> "声が低い"
            2 -> "やや声が低い"
            3 -> "中間の声"
            4 -> "やや声が高い"
            5 -> "声が高い"
            else -> "未選択"
        }
    }

    fun changeLength(index: Int): String {
        return when (index) {
            1 -> "曲が短い"
            2 -> "曲がやや短め"
            3 -> "中間"
            4 -> "曲がやや長め"
            5 -> "曲が長い"
            else -> "未選択"
        }
    }

    fun changeLyrics(index: Int): String {
        return when (index) {
            1 -> "外国語が多い"
            2 -> "外国語がやや多い"
            3 -> "外国語と日本語が半々"
            4 -> "少し外国語がある"
            5 -> "日本語が多い"
            else -> "未選択"
        }
    }
}
