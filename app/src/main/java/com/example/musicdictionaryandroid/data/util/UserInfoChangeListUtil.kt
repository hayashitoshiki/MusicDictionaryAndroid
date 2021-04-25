package com.example.musicdictionaryandroid.data.util

import android.content.Context
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.ui.MyApplication

/**
 * 各数値項目の変換
 */
object UserInfoChangeListUtil {

    private var context: Context = MyApplication.shered.applicationContext

    // 絞り込みリスト
    private var mainGenreList: Array<String>
    private var subGenre1List: Array<String>
    private var subGenre2List: Array<String>
    private var subGenre3List: Array<String>
    private var subGenre4List: Array<String>
    private var subGenre5List: Array<String>
    private var subGenre6List: Array<String>
    private var generationList: Array<String>
    private var genderList: Array<String>
    private var areaList: Array<String>

    init {
        mainGenreList = context.resources.getStringArray(R.array.genre1_spinner_list)
        subGenre1List = context.resources.getStringArray(R.array.genre12_spinner_list)
        subGenre2List = context.resources.getStringArray(R.array.genre22_spinner_list)
        subGenre3List = context.resources.getStringArray(R.array.genre32_spinner_list)
        subGenre4List = context.resources.getStringArray(R.array.genre42_spinner_list)
        subGenre5List = context.resources.getStringArray(R.array.genre52_spinner_list)
        subGenre6List = context.resources.getStringArray(R.array.genre62_spinner_list)
        generationList = context.resources.getStringArray(R.array.birthday_spinner_list)
        genderList = context.resources.getStringArray(R.array.gender_spinner_list)
        areaList = context.resources.getStringArray(R.array.area_spinner_list)
    }

    fun changeGender(code: Int): String {

        return genderList[code]
    }

    fun changeArea(code: Int): String {

        return areaList[code]
    }

    fun getBirthday(code: Int): String {

        return generationList[code]
    }

    fun changeBirthdayString(code: String): Int {

        return generationList.indexOf(code)
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
