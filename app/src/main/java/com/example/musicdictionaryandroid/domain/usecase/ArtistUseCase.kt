package com.example.musicdictionaryandroid.domain.usecase

import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions
import com.example.musicdictionaryandroid.domain.model.value.Result
import kotlinx.coroutines.flow.Flow

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
     * @param artist アーティスト検索条件
     * @return アーティストの検索結果一覧
     */
    suspend fun getArtistsBy(artist: ArtistConditions): Result<List<ArtistContents>>

    /**
     * おすすめアーティスト検索
     *
     * @return レコメンドアーティスト一覧
     */
    suspend fun getArtistsByRecommend(): Result<List<ArtistContents>>

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
     * ユーザーの登録したアーティストをAPIサーバーから取得する。
     * もしつながらない場合は、ローカルDBから取得する
     *
     * @return 登録済みアーティスト一覧
     */
    suspend fun getArtistsByEmail(): Result<List<Artist>>

    /**
     * アーティスト登録
     * 1. APIへ登録する（登録失敗したら終了）
     * 2. 登録できたらローカルDBへ登録　
     *
     * @param artist 登録したいアーティスト
     * @return 登録正常完了判定結果
     */
    suspend fun addArtist(artist: Artist): Result<Artist>

    /**
     * アーティスト更新
     * 1. APIのアーティスト更新（更新失敗したら終了）
     * 2. 更新成功したらローカルDB更新
     *
     * @param artist 更新したいアーティストの新しいデータ
     * @return 更新正常完了判定結果
     */
    suspend fun updateArtist(artist: Artist): Result<Artist>

    /**
     * アーティスト削除
     * 1. APIのアーティスト削除（削除失敗したら終了）
     * 2. 削除成功したらローカルDB削除
     *
     * @param name 削除したいアーティストの名前
     * @return 削除正常完了判定結果
     */
    suspend fun deleteArtist(name: String): Result<String>

    // アーティストリスト取得
    fun getArtistList(): Flow<List<Artist>>
}
