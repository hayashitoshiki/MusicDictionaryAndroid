package com.example.musicdictionaryandroid.model.usecase

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.util.Result
import java.util.ArrayList
import retrofit2.Response

/**
 * アーティストに関するビジネスロジック
 *
 */
interface ArtistUseCase {
    /*----------------------------------------
        検索
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
     * ユーザーの登録したアーティストをAPIサーバーから取得する。
     * もしつながらない場合は、ローカルDBから取得する
     *
     * @param email ユーザーのemail
     * @return 登録済みアーティスト一覧
     */
    suspend fun getArtistsByEmail(email: String): Result<ArrayList<ArtistsForm>?>
    /**
     * アーティスト登録
     * 1. APIへ登録する（登録失敗したら終了）
     * 2. 登録できたらローカルDBへ登録　
     *
     * @param artist 登録したいアーティスト
     * @param email ユーザのemail
     * @return 登録正常完了判定結果
     */
    suspend fun addArtist(artist: ArtistsForm, email: String): Result<CallBackData?>
    /**
     * アーティスト更新
     * 1. APIのアーティスト更新（更新失敗したら終了）
     * 2. 更新成功したらローカルDB更新
     *
     * @param artist 更新したいアーティストの新しいデータ
     * @param beforeName 更新したいアーティストの元の名前
     * @param email ユーザのemail
     * @return 更新正常完了判定結果
     */
    suspend fun updateArtist(artist: ArtistsForm, beforeName: String, email: String): Result<CallBackData?>
    /**
     * アーティスト削除
     * 1. APIのアーティスト削除（削除失敗したら終了）
     * 2. 削除成功したらローカルDB削除
     *
     * @param name 削除したいアーティストの名前
     * @param email ユーザのemail
     * @return 削除正常完了判定結果
     */
    suspend fun deleteArtist(name: String, email: String): Result<CallBackData?>
}
