package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.database.entity.ArtistsForm
import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.util.Result
import java.util.*

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
    suspend fun getArtistsBy(artists: ArtistsForm): Result<List<ArtistsForm>>
    /**
     * おすすめアーティスト検索
     *
     * @param email ユーザのEmail
     * @return レコメンドアーティスト一覧
     */
    suspend fun getArtistsByRecommend(email: String): Result<List<ArtistsForm>>
    /**
     * 急上昇アーティスト取得
     *
     * @return 急上昇アーティスト一覧
     */
    suspend fun getArtistsBySoaring(): Result<List<ArtistsForm>>
    /*----------------------------------------
        設定タブ
     ----------------------------------------*/
    /**
     * ユーザの登録したアーティスト取得
     *
     * @param email ユーザーのemail
     * @return 登録済みアーティスト一覧
     */
    suspend fun getArtistsByEmail(email: String): Result<List<ArtistsForm>>
    /**
     * アーティスト登録
     *
     * @param artist 登録したいアーティスト
     * @param email ユーザのemail
     * @return 登録正常完了判定結果
     */
    suspend fun addArtist(artist: ArtistsForm, email: String): Result<ArtistsForm>
    /**
     * アーティスト更新
     *
     * @param artist 更新したいアーティストの新しいデータ
     * @param beforeName 更新したいアーティストの元の名前
     * @param email ユーザのemail
     * @return 更新正常完了判定結果
     */
    suspend fun updateArtist(artist: ArtistsForm, email: String): Result<ArtistsForm>
    /**
     * アーティスト削除
     *
     * @param name 削除したいアーティストの名前
     * @param email ユーザのemail
     * @return 削除正常完了判定結果
     */
    suspend fun deleteArtist(name: String, email: String): Result<CallBackData>
    /*----------------------------------------
        ユーザー情報
     ----------------------------------------*/
    /**
     * 登録したユーザーの情報取得
     *
     * @param email ユーザーのEmail
     * @return 登録したユーザー情報取得
     */
    suspend fun getUserByEmail(email: String): Result<User>
    /**
     * ユーザー登録
     *
     * @param user 登録数ユーザー情報
     * @return 登録処理結果
     */
    suspend fun createUser(user: String): Result<CallBackData>
    /**
     * ユーザー情報変更
     *
     * @param user 更新するユーザー情報
     * @param email 更新したユーザのEmail
     * @return 変更処理結果
     */
    suspend fun changeUser(user: User, email: String): Result<CallBackData>
}
