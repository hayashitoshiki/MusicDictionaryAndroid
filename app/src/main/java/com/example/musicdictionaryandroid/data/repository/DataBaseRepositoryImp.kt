package com.example.musicdictionaryandroid.data.repository

import androidx.lifecycle.LiveData
import com.example.musicdictionaryandroid.data.database.entity.Artist
import com.example.musicdictionaryandroid.data.database.entity.ArtistsForm
import com.example.musicdictionaryandroid.ui.MyApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataBaseRepositoryImp(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : DataBaseRepository {

    private val dao = MyApplication.database.artistDao()

    // アーティスト登録
    override suspend fun addArtist(artist: ArtistsForm) {
        withContext(ioDispatcher) {
            dao.insert(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
        }
    }

    // アーティスト更新
    override suspend fun updateArtist(artist: ArtistsForm) {
        withContext(ioDispatcher) {
            dao.update(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
        }
    }

    // 全アーティスト更新
    override suspend fun updateAll(artists: List<ArtistsForm>) {
        withContext(ioDispatcher) {
            dao.deleteAll()
            artists.forEach { artist ->
                dao.insert(Artist(null, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics)) }
        }
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String) {
        withContext(ioDispatcher) {
            dao.deleteByName(name)
        }
    }

    // 全アーティスト削除
    override suspend fun deleteAll() {
        withContext(ioDispatcher) {
            dao.deleteAll()
        }
    }

    // アーティスト全取得
    override suspend fun getArtistAll(): ArrayList<ArtistsForm> {
        return withContext(ioDispatcher) {
            val artistList = arrayListOf<ArtistsForm>()
            val artists = dao.getAll()
            artists.forEach {
                val artist = ArtistsForm(
                    it.name!!,
                    it.gender!!,
                    it.voice!!,
                    it.length!!,
                    it.lyrics!!,
                    it.genre1!!,
                    it.genre2!!,
                    null)
                artistList.add(artist)
            }
            return@withContext artistList
        }
    }

    // アーティスト名一致取得
    override suspend fun findByName(name: String): Artist {
        return withContext(ioDispatcher) {
            return@withContext dao.getArtistByName(name)
        }
    }

    // アーティストリスト取得
    override fun getArtistList(): LiveData<List<Artist>> {
        return dao.getArtistList()
    }
}
