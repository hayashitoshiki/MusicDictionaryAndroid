package com.example.presentation.home

import com.example.domain.model.value.*
import com.example.presentation.R
import com.example.presentation.util.MessageUtil

/**
 * ResultAdapterのヘッダーView要素保持クラス
 */
class ResultAdapterHeaderState(private val messageUtil: MessageUtil) {

    // 名前検索の有無
    fun isName(name: String?): Boolean {
        return !name.isNullOrEmpty() && !(name == "急上昇" || name == "おすすめ")
    }

    // 性別検索の有無
    fun isGender(gender: Gender?): Boolean {
        return gender != null
    }

    // 歌詞の割合検索の有無
    fun isLyric(lyrics: Lyrics?): Boolean {
        return lyrics != null
    }

    // 曲の長さ検索の有無
    fun isLength(length: Length?): Boolean {
        return length != null
    }

    // 声の高さ検索の有無
    fun isVoice(voice: Voice?): Boolean {
        return voice != null
    }

    // ジャンル１検索の有無
    fun isGenre1(genre1: Genre1?): Boolean {
        return genre1 != null
    }

    // ジャンル2検索の有無
    fun isGenre2(genre2: Genre2?): Boolean {
        return genre2 != null && genre2.value != 0
    }

    // 再検索ボタンの表示の有無
    fun isSubmitButton(name: String?): Boolean {
        return !(name == "急上昇" || name == "おすすめ")
    }

    // タイトル文言取得
    fun getTitleText(name: String?): String {
        return if (name != null && (name == "急上昇" || name == "おすすめ")) {
            name
        } else messageUtil.getString(R.string.search_label_title)
    }

    // 検索した性別（文字列）取得
    fun getGenderText(gender: Gender?): String? {
        return if (gender != null) {
            messageUtil.getString(R.string.search_label_gender) + messageUtil.getGender(gender.value)
        } else null
    }

    // 検索した声の高さ（文字列）取得
    fun getVoiceText(voice: Voice?): String? {
        return if (voice != null) {
            messageUtil.getString(R.string.search_label_voice) + messageUtil.getVoice(voice.value)
        } else null
    }

    // 検索した曲の長さ（文字列）取得
    fun getLengthText(length: Length?): String? {
        return if (length != null) {
            messageUtil.getString(R.string.search_label_length) + messageUtil.getLength(length.value)
        } else null
    }

    // 検索した歌詞の割合（文字列）取得
    fun getLyricsText(lyrics: Lyrics?): String? {
        return if (lyrics != null) {
            messageUtil.getString(R.string.search_label_lyrics) + messageUtil.getLyrics(lyrics.value)
        } else null
    }

    // 検索したジャンル１（文字列）取得
    fun getGenre1Text(genre1: Genre1?): String? {
        return if (genre1 != null) {
            messageUtil.getString(R.string.search_label_genre1) + messageUtil.getGenre1(genre1.value)
        } else null
    }

    // 検索したジャンル２（文字列）取得
    fun getGenre2Text(genre1: Genre1?, genre2: Genre2?): String? {
        return if (genre1 != null && genre2 != null) {
            messageUtil.getString(R.string.search_label_genre2) + messageUtil.getGenre2(genre1.value, genre2.value)
        } else null
    }
}
