package com.example.musicdictionaryandroid.model.entity

/**
 * API連絡用アーティスト
 *
 * @property name アーティスト名
 * @property gender 性別
 * @property voice 声の高さ
 * @property length 曲の長さ
 * @property lyrics 歌詞の言語比率
 * @property genre1 ジャンル１
 * @property genre2 ジャンル２
 */
data class ArtistsForm(
    var name: String = "",
    var gender: Int = 0,
    var voice: Int = 0,
    var length: Int = 0,
    var lyrics: Int = 0,
    var genre1: Int = 0,
    var genre2: Int = 0,
    var thumb: String? = "",
    var preview: String? = ""
) : java.io.Serializable {
    fun getMapList(): Map<String, String> {
        val mutableMap: MutableMap<String, String> = mutableMapOf()
        mutableMap["name"] = name
        mutableMap["gender"] = gender.toString()
        mutableMap["voice"] = voice.toString()
        mutableMap["length"] = length.toString()
        mutableMap["lyrics"] = lyrics.toString()
        mutableMap["genre1"] = genre1.toString()
        mutableMap["genre2"] = genre2.toString()
        return mutableMap
    }
}
