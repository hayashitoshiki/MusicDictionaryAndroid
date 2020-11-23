package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm

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
    fun addArtist(artist: ArtistsForm)
    /**
     * アーティスト削除
     *
     * @param name 削除しするアーティスト名
     */
    fun deleteArtist(name: String)
    /**
     * 全部アーティスト削除
     *
     */
    fun deleteAll()
    /**
     * アーティスト更新
     *
     * @param artist 更新するアーティスト情報
     */
    fun updateArtist(artist: ArtistsForm)
    /**
     * 全アーティスト更新
     *
     * @param artists 更新するアーティストリスト
     */
    fun updateAll(artists: ArrayList<ArtistsForm>)
    /**
     * アーティスト取得
     *
     * @param name 取得したアーティスト名
     * @return 該当するアーティスト
     */
    fun findByName(name: String): Artist
    /**
     * 全アーティスト取得
     *
     * @return ローカルDBに登録されている全てのアーティスト取得
     */
    fun getArtistAll(): ArrayList<ArtistsForm>
}
