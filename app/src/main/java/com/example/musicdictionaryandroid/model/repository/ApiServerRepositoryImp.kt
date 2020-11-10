package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import com.example.musicdictionaryandroid.model.net.Provider
import java.util.*
import retrofit2.Response

class ApiServerRepositoryImp : ApiServerRepository {

    // 指定した検索条件で検索した時のアーティストリストを返す  あくまで検索結果の"アーティストリストを返す"
     override fun getArtistsBy(artists: ArtistsForm): Response<ArrayList<ArtistsForm>> {
        return Provider.api().search(artists.getMapList()).execute()
    }

    // おすすめアーティストリストを返す
    override fun getArtistsByRecommend(email: String): Response<ArrayList<ArtistsForm>> {
        return Provider.api().getRecommend(email).execute()
    }

    // 急上昇アーティストリストを返す
    override fun getArtistsBySoaring(): Response<ArrayList<ArtistsForm>> {
        return Provider.api().getSoaring().execute()
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
        val delete = Provider.api().deleteArtist(name, email).execute()
        delete.body()?. let {
        }
        return Provider.api().deleteArtist(name, email).execute()
    }

    // ユーザー取得
    override fun getUserByEmail(email: String): Response<User> {
        return Provider.api().getUserByEmail(email).execute()
    }
    // ユーザー登録
    override fun createUser(user: String): Response<CallBackData> {
        return Provider.api().createUser(user).execute()
    }

    // ユーザー情報変更
    override fun changeUser(user: User, email: String): Response<CallBackData> {
        return Provider.api().changeUser(user, email).execute()
    }
}
