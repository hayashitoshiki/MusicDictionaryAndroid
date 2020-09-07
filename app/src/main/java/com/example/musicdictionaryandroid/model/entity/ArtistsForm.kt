package com.example.musicdictionaryandroid.model.entity

data class ArtistsForm(
    var name: String = "",
    var gender: Int = 0,
    var voice: Int = 0,
    var length: Int = 0,
    var lyrics: Int = 0,
    var genre1: String? = "",
    var genre2: String? = "",
    var lyricsGenre: String? = ""
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
