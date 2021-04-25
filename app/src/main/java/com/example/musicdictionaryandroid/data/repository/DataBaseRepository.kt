package com.example.musicdictionaryandroid.data.repository

import androidx.lifecycle.LiveData
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.domain.model.entity.Artist

/**
 * ローカルDBへのアーティスト情報関連のRepository
 *
 */
interface DataBaseRepository {
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
     * アーティスト取得
     *
     * @param name 取得したアーティスト名
     * @return 該当するアーティスト
     */
    suspend fun findByName(name: String): Artist
    /**
     * 全アーティスト取得
     *
     * @return ローカルDBに登録されている全てのアーティスト取得
     */
    suspend fun getArtistAll(): List<Artist>

    // アーティストリスト取得
    fun getArtistList(): LiveData<List<Artist>>
}
