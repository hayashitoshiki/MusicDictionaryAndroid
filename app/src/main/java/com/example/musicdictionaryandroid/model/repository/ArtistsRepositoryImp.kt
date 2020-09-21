package com.example.musicdictionaryandroid.model.repository

import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import io.realm.Realm
import io.realm.RealmConfiguration

class ArtistsRepositoryImp : ArtistsRepository {

    private lateinit var realm: Realm

    companion object {
        private const val TAG = "Realm"
    }

    private fun updateRealm() {
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        realm = Realm.getInstance(realmConfig)
    }

    // DB更新
    override fun updateAll(artists: ArrayList<ArtistsForm>) {
        updateRealm()
        for (artist in artists) {
            realm.executeTransaction {
                val data = realm.createObject(Artist::class.java, getAutoIncrementId(realm)).also {
                    it.name = artist.name
                    it.gender = artist.gender
                    it.voice = artist.voice
                    it.length = artist.length
                    it.lyrics = artist.lyrics
                }
                realm.copyToRealm(data)
            }
        }
        realm.close()
    }

    // データリセット
    override fun deleteAll() {
        updateRealm()
        val count = getAutoIncrementId(realm) - 1
        if (count != 0) {
            realm.executeTransaction {
                val book = realm.where(Artist::class.java).findAll()
                book.deleteAllFromRealm()
            }
        }
    }

    // アーティスト登録
    override fun addArtist(artist: ArtistsForm) {
        updateRealm()
        realm.executeTransaction {
            val data = realm.createObject(Artist::class.java, getAutoIncrementId(realm)).also {
                it.name = artist.name
                it.gender = artist.gender
                it.voice = artist.voice
                it.length = artist.length
                it.lyrics = artist.lyrics
            }
            realm.copyToRealm(data)
        }
    }

    // アーティスト更新
    override fun updateArtist(artist: ArtistsForm, beforeName: String) {
        updateRealm()
        realm.executeTransaction {
            val art = realm.where(Artist::class.java).equalTo("name", beforeName).findFirst()!!
            art.name = artist.name
            art.gender = artist.gender
            art.voice = artist.voice
            art.length = artist.length
            art.lyrics = artist.lyrics
            realm.insertOrUpdate(art)
        }
    }

    // アーティスト削除
    override fun deleteArtist(name: String) {
        updateRealm()
        realm.executeTransaction {
            val book = realm.where(Artist::class.java).equalTo("name", name).findFirst()
            book!!.deleteFromRealm()
        }
    }

    // アーティスト全取得
    override fun getArtistAll(): ArrayList<ArtistsForm> {
        updateRealm()
        val artistsCache = realm.where(Artist::class.java).findAll()
        val artistList: ArrayList<ArtistsForm> = arrayListOf()
        artistsCache.forEach {
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
        updateRealm()
        val artist = realm.where(Artist::class.java).equalTo("name", name).findFirst()

        return artist!!
    }

    // 自動連番Id取得
    private fun getAutoIncrementId(realm: Realm): Int {
        // 初期化
        var nextUserId: Int = 1
        // userIdの最大値を取得
        val maxUserId = realm.where(Artist::class.java).max("id")
        // 1度もデータが作成されていない場合はNULLが返ってくるため、NULLチェックをする
        if (maxUserId != null) {
            nextUserId = maxUserId.toInt() + 1
        }

        return nextUserId
    }
}
