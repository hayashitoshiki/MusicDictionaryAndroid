package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.entity.User
import java.util.*
import retrofit2.Response

/**
 * API呼び出し関連のRepository
 */
interface ApiServerRepository {
    /*----------------------------------------
        検索タブ
     ----------------------------------------*/
    /**
     * 検索条件に一致するアーティスト取得
     *
     * @param artists アーティスト検索条件
     * @return アーティストの検索結果一覧
     */
    fun getArtistsBy(artists: ArtistsForm): Response<ArrayList<ArtistsForm>>
    /**
     * おすすめアーティスト検索
     *
     * @param email ユーザのEmail
     * @return レコメンドアーティスト一覧
     */
    fun getArtistsByRecommend(email: String): Response<ArrayList<ArtistsForm>>
    /**
     * 急上昇アーティスト取得
     *
     * @return 急上昇アーティスト一覧
     */
    fun getArtistsBySoaring(): Response<ArrayList<ArtistsForm>>
    /*----------------------------------------
        設定タブ
     ----------------------------------------*/
    /**
     * ユーザの登録したアーティスト取得
     *
     * @param email ユーザーのemail
     * @return 登録済みアーティスト一覧
     */
    fun getArtistsByEmail(email: String): Response<ArrayList<ArtistsForm>>
    /**
     * アーティスト登録
     *
     * @param artist 登録したいアーティスト
     * @param email ユーザのemail
     * @return 登録正常完了判定結果
     */
    fun addArtist(artist: ArtistsForm, email: String): Response<ArtistsForm>
    /**
     * アーティスト更新
     *
     * @param artist 更新したいアーティストの新しいデータ
     * @param beforeName 更新したいアーティストの元の名前
     * @param email ユーザのemail
     * @return 更新正常完了判定結果
     */
    fun updateArtist(artist: ArtistsForm, email: String): Response<ArtistsForm>
    /**
     * アーティスト削除
     *
     * @param name 削除したいアーティストの名前
     * @param email ユーザのemail
     * @return 削除正常完了判定結果
     */
    fun deleteArtist(name: String, email: String): Response<CallBackData>
    /*----------------------------------------
        ユーザー情報
     ----------------------------------------*/
    /**
     * 登録したユーザーの情報取得
     *
     * @param email ユーザーのEmail
     * @return 登録したユーザー情報取得
     */
    fun getUserByEmail(email: String): Response<User>
    /**
     * ユーザー登録
     *
     * @param user 登録数ユーザー情報
     * @return 登録処理結果
     */
    fun createUser(user: String): Response<CallBackData>
    /**
     * ユーザー情報変更
     *
     * @param user 更新するユーザー情報
     * @param email 更新したユーザのEmail
     * @return 変更処理結果
     */
    fun changeUser(user: User, email: String): Response<CallBackData>
}
