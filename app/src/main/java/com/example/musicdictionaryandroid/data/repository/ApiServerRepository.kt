package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions

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
     * @param artist アーティスト検索条件
     * @return アーティストの検索結果一覧
     */
    suspend fun getArtistsBy(artist: ArtistConditions): Result<List<ArtistContents>>

    /**
     * おすすめアーティスト検索
     *
     * @param email ユーザのEmail
     * @return レコメンドアーティスト一覧
     */
    suspend fun getArtistsByRecommend(email: String): Result<List<ArtistContents>>

    /**
     * 急上昇アーティスト取得
     *
     * @return 急上昇アーティスト一覧
     */
    suspend fun getArtistsBySoaring(): Result<List<ArtistContents>>
    /*----------------------------------------
        設定タブ
     ----------------------------------------*/
    /**
     * ユーザの登録したアーティスト取得
     *
     * @param email ユーザーのemail
     * @return 登録済みアーティスト一覧
     */
    suspend fun getArtistsByEmail(email: String): Result<List<Artist>>

    /**
     * アーティスト登録
     *
     * @param artist 登録したいアーティスト
     * @param email ユーザのemail
     * @return 登録正常完了判定結果
     */
    suspend fun addArtist(artist: Artist, email: String): Result<Artist>

    /**
     * アーティスト更新
     *
     * @param artist 更新したいアーティストの新しいデータ
     * @param email ユーザのemail
     * @return 更新正常完了判定結果
     */
    suspend fun updateArtist(artist: Artist, email: String): Result<Artist>

    /**
     * アーティスト削除
     *
     * @param name 削除したいアーティストの名前
     * @param email ユーザのemail
     * @return 削除正常完了判定結果
     */
    suspend fun deleteArtist(name: String, email: String): Result<String>
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
    suspend fun createUser(user: String): Result<String>

    /**
     * ユーザー情報変更
     *
     * @param user 更新するユーザー情報
     * @param email 更新したユーザのEmail
     * @return 変更処理結果
     */
    suspend fun changeUser(user: User, email: String): Result<CallBackData>
}
