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
    var genre1: String? = "",
    var genre2: String? = "",
    var lyricsGenre: String? = "",
    var thumb: String? = "",
    var preview: String? = ""
) : java.io.Serializable {
    fun getMapList(): Map<String, String> {
        val mutableMap: MutableMap<String, String> = mutableMapOf()
        mutableMap["name"] = name
        mutableMap["gender"] = gender.toString()
        genre1?.let {
            mutableMap["genre1"] = it
        }
        genre2?.let {
            mutableMap["genre2"] = it
        }
        lyricsGenre?.let {
            mutableMap["lyrics_genre"] = it
        }
        mutableMap["voice"] = voice.toString()
        mutableMap["length"] = length.toString()
        mutableMap["lyrics"] = lyrics.toString()

        return mutableMap
    }
}
