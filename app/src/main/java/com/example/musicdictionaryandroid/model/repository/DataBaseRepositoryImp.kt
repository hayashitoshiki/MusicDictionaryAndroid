package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.ui.MyApplication

class DataBaseRepositoryImp : DataBaseRepository {

    private val dao = MyApplication.database.artistDao()
    companion object {
        private const val TAG = "Realm"
    }

    // アーティスト登録
    override fun addArtist(artist: ArtistsForm) {
        dao.insert(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
    }

    // アーティスト更新
    override fun updateArtist(beforeName: String, artist: ArtistsForm) {
        dao.update(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
    }

    // 全アーティスト更新
    override fun updateAll(artists: ArrayList<ArtistsForm>) {
        for (artist in artists) {
            dao.update(Artist(0, artist.name, artist.gender, artist.voice, artist.length, artist.lyrics))
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
        dao.getAll().forEach {
            val artist = ArtistsForm(
                it.name!!,
                it.gender!!,
                it.voice!!,
                it.length!!,
                it.lyrics!!,
                it.genre1,
                it.genre2,
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
