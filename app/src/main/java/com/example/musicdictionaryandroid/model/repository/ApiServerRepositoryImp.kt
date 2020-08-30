package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import com.example.tosik.musicdictionary_androlid.model.net.Provider
import java.util.*
import retrofit2.Response

class ApiServerRepositoryImp : ApiServerRepository {

    // 指定した検索条件で検索した時のアーティストリストを返す  あくまで検索結果の"アーティストリストを返す"
     override fun getArtistsBy(artists: ArtistsForm): Response<ArrayList<ArtistsForm>> {
        return Provider.api().search(artists.getMapList()).execute()
    }

    // ユーザー登録したアーティスト取得
    override fun getArtistsByEmail(email: String): Response<ArrayList<ArtistsForm>> {
        return Provider.api().findByEmail(email).execute()
    }

    // アーティスト登録
    override fun addArtist(artist: ArtistsForm, email: String): Response<CallBackData> {
        return Provider.api().addArtist(artist.getMapList(), email).execute()
    }

    // アーティスト編集
    override fun updateArtist(artist: ArtistsForm, beforeName: String, email: String): Response<CallBackData> {
        return Provider.api().updateArtist(artist.getMapList(), beforeName, email).execute()
    }

    // アーティスト削除
    override fun deleteArtist(name: String, email: String): Response<CallBackData> {
        return Provider.api().deleteArtist(name, email).execute()
    }
}
