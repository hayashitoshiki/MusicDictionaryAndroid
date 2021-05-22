package com.example.data.repository

import com.example.data.local.database.dao.ArtistDao
import com.example.domain.model.entity.Artist
import com.example.domain.model.value.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalArtistRepositoryImp(private val dao: ArtistDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    com.example.domain.repository.LocalArtistRepository {

    // アーティスト登録
    override suspend fun addArtist(artist: Artist) = withContext(ioDispatcher) {
        val artistEntity = Converter.artistEntityFromArtist(artist)
        dao.insert(artistEntity)
    }

    // アーティスト更新
    override suspend fun updateArtist(artist: Artist) = withContext(ioDispatcher) {
        val name = artist.name
        val gender = artist.gender.value
        val voice = artist.voice.value
        val length = artist.length.value
        val lyrics = artist.lyrics.value
        val genre1 = artist.genre1.value
        val genre2 = artist.genre2.value
        dao.update(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // 全アーティスト更新
    override suspend fun updateAll(artists: List<Artist>) = withContext(ioDispatcher) {
        dao.deleteAll()
        artists.forEach { artist ->
            val artistEntity = Converter.artistEntityFromArtist(artist)
            dao.insert(artistEntity)
        }
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String) = withContext(ioDispatcher) {
        dao.deleteByName(name)
    }

    // 全アーティスト削除
    override suspend fun deleteAll() = withContext(ioDispatcher) {
        dao.deleteAll()
    }

    // アーティスト名一致取得
    override suspend fun getArtistByName(name: String): Artist = withContext(ioDispatcher) {
        val artistEntity = dao.getArtistByName(name)
        return@withContext Converter.artistFromArtistEntity(artistEntity)
    }

    // アーティスト全取得
    override fun getArtistAll(): Flow<List<Artist>> {
        return dao.getAll().map { artistEntityList ->
            artistEntityList.map { artistEntity ->
                Converter.artistFromArtistEntity(artistEntity)
            }
        }
    }

}
