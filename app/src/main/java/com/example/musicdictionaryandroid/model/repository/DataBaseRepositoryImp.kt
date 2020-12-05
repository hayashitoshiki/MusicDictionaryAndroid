package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.ui.MyApplication

class DataBaseRepositoryImp : DataBaseRepository {

    private val dao = MyApplication.database.artistDao()

    // アーティスト登録
    override suspend fun addArtist(artist: ArtistsForm) {
        dao.insert(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
    }

    // アーティスト更新
    override fun updateArtist(artist: ArtistsForm) {
        dao.update(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
    }

    // 全アーティスト更新
    override suspend fun updateAll(artists: ArrayList<ArtistsForm>) {
        dao.deleteAll()
        artists.forEachIndexed { index, artist ->
            dao.insert(Artist(null, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
        }
    }

    // アーティスト削除
    override fun deleteArtist(name: String) {
        dao.delete(Artist(0, name, 0, 0, 0, 0))
    }

    // 全アーティスト削除
    override fun deleteAll() {
        dao.deleteAll()
    }

    // アーティスト全取得
    override fun getArtistAll(): ArrayList<ArtistsForm> {
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
                null
            )
            artistList.add(artist)
        }
        return artistList
    }

    // アーティスト名一致取得
    override fun findByName(name: String): Artist {
        return dao.getArtistByName(name)
    }
}
