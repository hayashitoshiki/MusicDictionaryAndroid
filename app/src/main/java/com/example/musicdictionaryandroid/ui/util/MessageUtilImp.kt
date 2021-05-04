package com.example.musicdictionaryandroid.ui.util

import android.content.Context
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.ui.MyApplication

/**
 * 各数値項目の変換
 */
object MessageUtilImp : MessageUtil {

    private val context: Context = MyApplication.shared.applicationContext

    // 絞り込みリスト
    private val mainGenreList: List<String>
    private val subGenre0List: List<String>
    private val subGenre1List: List<String>
    private val subGenre2List: List<String>
    private val subGenre3List: List<String>
    private val subGenre4List: List<String>
    private val subGenre5List: List<String>
    private val subGenre6List: List<String>
    private val generationList: List<String>
    private val genderList: List<String>
    private val areaList: List<String>

    init {
        mainGenreList = context.resources.getStringArray(R.array.genre1_spinner_list).toList()
        subGenre0List = context.resources.getStringArray(R.array.genre02_spinner_list).toList()
        subGenre1List = context.resources.getStringArray(R.array.genre12_spinner_list).toList()
        subGenre2List = context.resources.getStringArray(R.array.genre22_spinner_list).toList()
        subGenre3List = context.resources.getStringArray(R.array.genre32_spinner_list).toList()
        subGenre4List = context.resources.getStringArray(R.array.genre42_spinner_list).toList()
        subGenre5List = context.resources.getStringArray(R.array.genre52_spinner_list).toList()
        subGenre6List = context.resources.getStringArray(R.array.genre62_spinner_list).toList()
        generationList = context.resources.getStringArray(R.array.birthday_spinner_list).toList()
        genderList = context.resources.getStringArray(R.array.gender_spinner_list).toList()
        areaList = context.resources.getStringArray(R.array.area_spinner_list).toList()
    }

    // 性別文字列取得
    override fun getGender(code: Int): String {
        return when (code) {
            1, 2 -> genderList[code]
            else -> throw IllegalArgumentException("存在しない性別です")
        }
    }

    // 地域名取得
    override fun getArea(code: Int): String {
        return areaList[code]
    }

    // 生年月日取得
    override fun getBirthday(code: Int): String {
        return generationList[code]
    }

    // 生年月日(index)取得
    override fun changeBirthdayString(code: String): Int {
        return generationList.indexOf(code)
    }

    // メインカテゴリ取得
    override fun getGenre1(index: Int): String {
        return mainGenreList[index]
    }

    // サブカテゴリ取得
    override fun getGenre2(genre1Index: Int, genre2Index: Int): String {
        return when (genre1Index) {
            1 -> subGenre1List[genre2Index]
            2 -> subGenre2List[genre2Index]
            3 -> subGenre3List[genre2Index]
            4 -> subGenre4List[genre2Index]
            5 -> subGenre5List[genre2Index]
            6 -> subGenre6List[genre2Index]
            else -> context.resources.getString(R.string.category_value_no_select)
        }
    }

    // 声の高さ取得
    override fun getVoice(index: Int): String {
        return when (index) {
            1 -> context.resources.getString(R.string.category_value_voice_1)
            2 -> context.resources.getString(R.string.category_value_voice_2)
            3 -> context.resources.getString(R.string.category_value_voice_3)
            4 -> context.resources.getString(R.string.category_value_voice_4)
            5 -> context.resources.getString(R.string.category_value_voice_5)
            else -> context.resources.getString(R.string.category_value_no_select)
        }
    }

    // 曲の長さ取得
    override fun getLength(index: Int): String {
        return when (index) {
            1 -> context.resources.getString(R.string.category_value_length_1)
            2 -> context.resources.getString(R.string.category_value_length_2)
            3 -> context.resources.getString(R.string.category_value_length_3)
            4 -> context.resources.getString(R.string.category_value_length_4)
            5 -> context.resources.getString(R.string.category_value_length_5)
            else -> context.resources.getString(R.string.category_value_no_select)
        }
    }

    // 歌詞の日本語比率取得
    override fun getLyrics(index: Int): String {
        return when (index) {
            1 -> context.resources.getString(R.string.category_value_lyric_1)
            2 -> context.resources.getString(R.string.category_value_lyric_2)
            3 -> context.resources.getString(R.string.category_value_lyric_3)
            4 -> context.resources.getString(R.string.category_value_lyric_4)
            5 -> context.resources.getString(R.string.category_value_lyric_5)
            else -> context.resources.getString(R.string.category_value_no_select)
        }
    }

    // region カテゴリ取得

    // メインカテゴリ取得
    override fun getMainCategory(): List<String> {
        return mainGenreList
    }

    // サブカテゴリ取得
    override fun getSubCategory(index: Int): List<String> {
        return when (index) {
            0 -> subGenre0List
            1 -> subGenre1List
            2 -> subGenre2List
            3 -> subGenre3List
            4 -> subGenre4List
            5 -> subGenre5List
            6 -> subGenre6List
            else -> throw IllegalArgumentException("存在しないリストです")
        }
    }

    // endregion

    // region タイトル文言

    // アーティスト追加画面タイトル取得
    override fun getArtistAddTitle(): String {
        return context.resources.getString(R.string.title_mypage_artist_add)
    }

    // アーティスト更新画面タイトル取得
    override fun getArtistChangeTitle(): String {
        return context.resources.getString(R.string.title_mypage_artist_change)
    }

    // endregion

    // region ボタン文言

    // 追加ボタン文言取得
    override fun getAddButton(): String {
        return context.resources.getString(R.string.submit_add)
    }

    // 変更ボタン文言取得
    override fun getChangeButton(): String {
        return context.resources.getString(R.string.submit_change)
    }

    // endregion
}
