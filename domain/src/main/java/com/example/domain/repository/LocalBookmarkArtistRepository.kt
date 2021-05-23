package com.example.domain.repository

import com.example.domain.model.entity.ArtistContents
import kotlinx.coroutines.flow.Flow

/**
 * ローカル保持のブックマークアーティスト情報に関するアクセスRepository
 *
 */
interface LocalBookmarkArtistRepository {

    /**
     * アーティスト追加
     *
     * @param artist 追加するアーティスト
     */
    suspend fun addArtist(artist: ArtistContents)

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
     * 全登録済みアーティス取得
     *
     * @return アーティストリスト
     */
    fun getArtistAll(): Flow<List<ArtistContents>>

    /**
     * ブックマークアーティストに登録されているか判定
     *
     * @param name 取得したアーティスト名
     * @return 該当するアーティスト
     */
    suspend fun isArtistByName(name: String): Boolean
}
