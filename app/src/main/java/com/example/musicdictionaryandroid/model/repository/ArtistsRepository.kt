package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

interface ArtistsRepository {

    // DB更新
    fun updateAll(artists: ArrayList<ArtistsForm>)

    // データリセット
    fun deleteAll()

    // アーティスト登録
    fun addArtist(artist: ArtistsForm)

    // アーティスト削除
    fun deleteArtist(name: String)

    // アーティスト更新
    fun updateArtist(artist: ArtistsForm, beforeName: String)

    // アーティスト名一致取得
    fun findByName(name: String): Artist

    // アーティスト全取得
    fun getArtistAll(): ArrayList<ArtistsForm>
}
