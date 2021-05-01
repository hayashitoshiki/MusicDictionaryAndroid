package com.example.musicdictionaryandroid.ui.util

interface MessageUtil {

    // region ユーザ情報

    /**
     * 性別名取得
     *
     * @param code 性別index
     * @return 性別名
     */
    fun getGender(code: Int): String

    /**
     * 地域名取得
     *
     * @param code 地域index
     * @return 地域文字列
     */
    fun getArea(code: Int): String

    /**
     * 生年月日取得
     *
     * @param code 生年月日index
     * @return 生年月日
     */
    fun getBirthday(code: Int): String

    /**
     * 生年月日からindexへ変更
     *
     * @param code 生年月日文字列
     * @return 生年月日Index
     */
    fun changeBirthdayString(code: String): Int

    // endregion

    // regionアーティスト情報

    /**
     * メインカテゴリ名取得
     *
     * @param index メインカテゴリindex
     * @return メインカテゴリ名
     */
    fun getGenre1(index: Int): String

    /**
     * サブカテゴリ名取得
     *
     * @param genre1Index メインカテゴリindex
     * @param genre2Index サブカテゴリindex
     * @return サブカテゴリ名
     */
    fun getGenre2(genre1Index: Int, genre2Index: Int): String

    /**
     * 声の高さ（文字列）取得
     *
     * @param index 声の高さ(数値)
     * @return 声の高さ（文字列）
     */
    fun getVoice(index: Int): String

    /**
     * 曲の長さ（文字列）取得
     *
     * @param index 曲の長さ(数値)
     * @return 曲の長さ（文字列）
     */
    fun getLength(index: Int): String

    /**
     * 歌詞の日本語比率（文字列）取得
     *
     * @param index 歌詞の日本語比率(数値)
     * @return 歌詞の日本語比率（文字列）
     */
    fun getLyrics(index: Int): String

    // endregion

    // region カテゴリリスト取得

    /**
     * メインカテゴリ取得
     *
     * @return メインカテゴリリスト
     */
    fun getMainCategory(): List<String>

    /**
     * サブカテゴリ取得
     *
     * @param index 取得するサブカテゴリの種類
     * @return サブカテゴリリスト
     */
    fun getSubCategory(index: Int): List<String>

    // endregion

    // region タイトル文言

    /**
     * アーティスト追加画面タイトル取得
     *
     * @return アーティスト追加画面タイトル文言
     */
    fun getArtistAddTitle(): String

    /**
     * アーティスト変更画面タイトル取得
     *
     * @return アーティスト変更画面タイトル文言
     */
    fun getArtistChangeTitle(): String

    // endregion

    // region ボタン文言

    /**
     * 追加ボタン文言取得
     *
     * @return 追加ボタン文言
     */
    fun getAddButton(): String

    /**
     * 変更ボタン文言取得
     *
     * @return 変更ボタン文言
     */
    fun getChangeButton(): String

    // endregion
}
