package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import java.util.*
import retrofit2.Response

interface ApiServerRepository {
    /*----------------------------------------
        検索タブ
     ----------------------------------------*/
    // 検索条件に一致するアーティスト取得
    fun getArtistsBy(artists: ArtistsForm): Response<ArrayList<ArtistsForm>>

    // おすすめアーティスト取得
    fun getArtistsByRecommend(email: String): Response<ArrayList<ArtistsForm>>

    // 急上昇アーティスト取得
    fun getArtistsBySoaring(): Response<ArrayList<ArtistsForm>>

    /*----------------------------------------
        設定タブ
     ----------------------------------------*/
    // ユーザーの登録したアーティスト取得
    fun getArtistsByEmail(email: String): Response<ArrayList<ArtistsForm>>

    // アーティスト登録
    fun addArtist(artist: ArtistsForm, email: String): Response<CallBackData>
    // アーティスト編集
    fun updateArtist(artist: ArtistsForm, beforeName: String, email: String): Response<CallBackData>
    // アーティスト削除
    fun deleteArtist(name: String, email: String): Response<CallBackData>
}
