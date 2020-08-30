package com.example.musicdictionaryandroid.model.entity

data class ArtistsForm(
    var name: String = "",
    var gender: Int = 0,
    var voice: Int = 0,
    var length: Int = 0,
    var lyrics: Int = 0,
    var genre1: String = "",
    var genre2: String = "",
    var lyricsGenre: String = ""
) : java.io.Serializable {
    fun getMapList(): Map<String, String> {
        val mutableMap: MutableMap<String, String> = mutableMapOf()
        mutableMap["name"] = name
        mutableMap["gender"] = gender.toString()
        mutableMap["genre1"] = genre1
        mutableMap["genre2"] = genre2
        mutableMap["lyrics_genre"] = lyricsGenre
        mutableMap["voice"] = voice.toString()
        mutableMap["length"] = length.toString()
        mutableMap["lyrics"] = lyrics.toString()

        return mutableMap
    }
}
