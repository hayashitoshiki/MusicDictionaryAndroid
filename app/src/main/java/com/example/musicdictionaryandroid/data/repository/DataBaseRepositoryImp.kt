package com.example.musicdictionaryandroid.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.ui.MyApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataBaseRepositoryImp(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : DataBaseRepository {

    private val dao = MyApplication.database.artistDao()

    // アーティスト登録
    override suspend fun addArtist(artist: Artist) = withContext(ioDispatcher) {
        val artistEntity = convertArtistEntityFromArtist(artist)
        dao.insert(artistEntity)
    }

    // アーティスト更新
    override suspend fun updateArtist(artist: Artist) = withContext(ioDispatcher) {
        val artistEntity = convertArtistEntityFromArtist(artist)
        dao.update(artistEntity)
    }

    // 全アーティスト更新
    override suspend fun updateAll(artists: List<Artist>) = withContext(ioDispatcher) {
        dao.deleteAll()
        artists.forEach { artist ->
            val artistEntity = convertArtistEntityFromArtist(artist)
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

    // アーティスト全取得
    override suspend fun getArtistAll(): List<Artist> = withContext(ioDispatcher) {
        val artists = dao.getAll()
        return@withContext artists.map {
            convertArtistFromArtistEntity(it)
        }
    }

    // アーティスト名一致取得
    override suspend fun findByName(name: String): Artist = withContext(ioDispatcher) {
        val artistEntity = dao.getArtistByName(name)
        return@withContext convertArtistFromArtistEntity(artistEntity)
    }

    // アーティストリスト取得
    override fun getArtistList(): LiveData<List<Artist>> {
        val artistEntityLiveData = dao.getArtistList()
        val artistLiveData = MutableLiveData<List<Artist>>(listOf())

        artistEntityLiveData.observeForever { artistEntityList ->
            val artistList = artistEntityList.map { artistEntity ->
                convertArtistFromArtistEntity(artistEntity)
            }
            artistLiveData.postValue(artistList)
        }
        return artistLiveData
    }

    // アーティストテーブルからアーティストモデルべ変換
    private fun convertArtistFromArtistEntity(artistEntity: ArtistEntity): Artist {
        val name = artistEntity.name
        val gender = Gender.getEnumByValue(artistEntity.gender)
        val voice = Voice(artistEntity.voice)
        val length = Length(artistEntity.length)
        val lyrics = Lyrics(artistEntity.lyrics)
        val genre1 = Genre1(artistEntity.genre1)
        val genre2 = Genre2(artistEntity.genre2)
        return Artist(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // アーティストモデルからアーティストテーブルへ変換
    private fun convertArtistEntityFromArtist(artist: Artist): ArtistEntity {
        val name = artist.name
        val gender = artist.gender.value
        val voice = artist.voice.value
        val length = artist.length.value
        val lyrics = artist.lyrics.value
        val genre1 = artist.genre1.value
        val genre2 = artist.genre2.value
        return ArtistEntity(null, name, gender, voice, length, lyrics, genre1, genre2)
    }
}
