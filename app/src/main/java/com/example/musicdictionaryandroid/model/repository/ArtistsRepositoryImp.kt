package com.example.musicdictionaryandroid.model.repository

import android.util.Log
import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.DataStock
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
        Log.d(TAG, "データ更新")

        for (artist in artists) {
            realm.executeTransaction {
                var art = realm.createObject(Artist::class.java, getAutoIncrementId(realm))
                art.name = artist.name
                art.gender = artist.gender
                art.voice = artist.voice
                art.length = artist.length
                art.lyrics = artist.lyrics
                realm.copyToRealm(art)
            }
        }
        realm.close()
    }

    // データリセット
    override fun deleteAll() {
        updateRealm()
        val count = getAutoIncrementId(realm) - 1
        Log.d(TAG, "データリセット" + count)

        if (count != 0) {
            Log.d(TAG, "データリセット開始")
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
            val art = realm.createObject(Artist::class.java, getAutoIncrementId(realm))
            art.name = artist.name
            art.gender = artist.gender
            art.voice = artist.voice
            art.length = artist.length
            art.lyrics = artist.lyrics
            realm.copyToRealm(art)
        }
        DataStock.count++
    }

    // アーティスト更新
    override fun updateArtist(artist: ArtistsForm, beforeName: String) {
        updateRealm()

        Log.d(TAG, "アーティスト更新開始")
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

        Log.d(TAG, "データリセット開始")
        realm.executeTransaction {
            val book = realm.where(Artist::class.java).equalTo("name", name).findFirst()
            book!!.deleteFromRealm()
        }
    }

    // アーティスト全取得
    override fun getArtistAll(): Array<Artist> {
        updateRealm()
        Log.d(TAG, "全アーティスト取得")
        val artistList = realm.where(Artist::class.java).findAll()
        val artists: Array<Artist> = artistList.toTypedArray()

        return artists
    }

    // アーティスト名一致取得
    override fun findByName(name: String): Artist {
        updateRealm()
        Log.d(TAG, "全アーティスト取得")
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
