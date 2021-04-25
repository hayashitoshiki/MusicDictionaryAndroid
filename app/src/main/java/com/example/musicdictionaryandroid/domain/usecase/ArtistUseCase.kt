package com.example.musicdictionaryandroid.domain.usecase

import androidx.lifecycle.LiveData
import com.example.musicdictionaryandroid.data.database.entity.Artist
import com.example.musicdictionaryandroid.data.database.entity.ArtistsForm
import com.example.musicdictionaryandroid.data.util.Result

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
    suspend fun getArtistsBy(artists: ArtistsForm): Result<List<ArtistsForm>?>
    /**
     * おすすめアーティスト検索
     *
     * @param email ユーザのEmail
     * @return レコメンドアーティスト一覧
     */
    suspend fun getArtistsByRecommend(email: String): Result<List<ArtistsForm>?>
    /**
     * 急上昇アーティスト取得
     *
     * @return 急上昇アーティスト一覧
     */
    suspend fun getArtistsBySoaring(): Result<List<ArtistsForm>?>
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
    suspend fun getArtistsByEmail(email: String): Result<List<ArtistsForm>?>
    /**
     * アーティスト登録
     * 1. APIへ登録する（登録失敗したら終了）
     * 2. 登録できたらローカルDBへ登録　
     *
     * @param artist 登録したいアーティスト
     * @param email ユーザのemail
     * @return 登録正常完了判定結果
     */
    suspend fun addArtist(artist: Artist, email: String): Result<ArtistsForm?>
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
    suspend fun updateArtist(artist: Artist, email: String): Result<ArtistsForm?>
    /**
     * アーティスト削除
     * 1. APIのアーティスト削除（削除失敗したら終了）
     * 2. 削除成功したらローカルDB削除
     *
     * @param name 削除したいアーティストの名前
     * @param email ユーザのemail
     * @return 削除正常完了判定結果
     */
    suspend fun deleteArtist(name: String, email: String): Result<List<ArtistsForm>?>

    // アーティストリスト取得
    fun getArtistList(): LiveData<List<Artist>>
}
