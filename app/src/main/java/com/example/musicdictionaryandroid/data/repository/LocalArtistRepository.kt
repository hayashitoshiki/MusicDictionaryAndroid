package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.domain.model.entity.Artist
import kotlinx.coroutines.flow.Flow

/**
 * ローカル保持のアーティスト情報に関するアクセスRepository
 *
 */
interface LocalArtistRepository {

    /**
     * アーティスト追加
     *
     * @param artist 追加するアーティスト
     */
    suspend fun addArtist(artist: Artist)

    /**
     * アーティスト削除
     *
     * @param name 削除しするアーティスト名
     */
    suspend fun deleteArtist(name: String)

    /**
     * 全部アーティスト削除
     *
     */
    suspend fun deleteAll()

    /**
     * アーティスト更新
     *
     * @param artist 更新するアーティスト情報
     */
    suspend fun updateArtist(artist: Artist)

    /**
     * 全アーティスト更新
     *
     * @param artists 更新するアーティストリスト
     */
    suspend fun updateAll(artists: List<Artist>)

    /**
     * 登録済みアーティスト取得
     *
     * @param name 取得したアーティスト名
     * @return 該当するアーティスト
     */
    suspend fun getArtistByName(name: String): Artist

    /**
     * 全登録済みアーティス取得
     *
     * @return アーティストリスト
     */
    fun getArtistAll(): Flow<List<Artist>>
}
