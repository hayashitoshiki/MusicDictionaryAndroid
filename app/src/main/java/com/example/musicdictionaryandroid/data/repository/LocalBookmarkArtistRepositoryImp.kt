package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.local.database.dao.BookmarkArtistDao
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalBookmarkArtistRepositoryImp(
    private val bookmarkArtistDao: BookmarkArtistDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalBookmarkArtistRepository {

    // お気に入りアーチスト追加
    override suspend fun addArtist(artist: ArtistContents) = withContext(ioDispatcher) {
        val artistContentsEntity = Converter.bookmarkArtistEntityFromArtistContents(artist)
        bookmarkArtistDao.insert(artistContentsEntity)
    }

    // お気に入りアーティスト削除
    override suspend fun deleteArtist(name: String) = withContext(ioDispatcher) {
        bookmarkArtistDao.deleteByName(name)
    }

    // 全お気に理アーティスト削除
    override suspend fun deleteAll() = withContext(ioDispatcher) {
        bookmarkArtistDao.deleteAll()
    }

    // お気に入りアーティスト全取得
    override fun getArtistAll(): Flow<List<ArtistContents>> {
        return bookmarkArtistDao.getAll().map { bookmarkArtistList ->
            bookmarkArtistList.map { bookmarkArtist ->
                Converter.artistContentsFromBookmarkArtistEntity(bookmarkArtist)
            }
        }
    }

    // お気に入り登録済み判定
    override suspend fun isArtistByName(name: String): Boolean = withContext(ioDispatcher) {
        val artist = bookmarkArtistDao.getArtistByName(name)
        return@withContext artist != null
    }
}
