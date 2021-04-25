package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.database.entity.ArtistsForm
import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.net.Provider
import com.example.musicdictionaryandroid.data.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiServerRepositoryImp(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ApiServerRepository {

    // 指定した検索条件で検索した時のアーティストリストを返す  あくまで検索結果の"アーティストリストを返す"
    override suspend fun getArtistsBy(artists: ArtistsForm): Result<List<ArtistsForm>> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().search(artists.getMapList())}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // おすすめアーティストリストを返す
    override suspend fun getArtistsByRecommend(email: String): Result<List<ArtistsForm>> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().getRecommend(email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // 急上昇アーティストリストを返す
    override suspend fun getArtistsBySoaring(): Result<List<ArtistsForm>> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().getSoaring()}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // ユーザー登録したアーティスト取得
    override suspend fun getArtistsByEmail(email: String): Result<List<ArtistsForm>> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().findByEmail(email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // アーティスト登録
    override suspend fun addArtist(artist: ArtistsForm, email: String): Result<ArtistsForm> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().addArtist(artist.getMapList(), email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // アーティスト編集
    override suspend fun updateArtist(artist: ArtistsForm, email: String): Result<ArtistsForm> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().updateArtist(artist.getMapList(), email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String, email: String): Result<CallBackData> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().deleteArtist(name, email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // ユーザー取得
    override suspend fun getUserByEmail(email: String): Result<User> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().getUserByEmail(email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }
    // ユーザー登録
    override suspend fun createUser(user: String): Result<CallBackData> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().createUser(user)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }

    // ユーザー情報変更
    override suspend fun changeUser(user: User, email: String): Result<CallBackData> {
        return withContext(ioDispatcher) {
            return@withContext runCatching{Provider.api().changeUser(user, email)}.fold(
                onSuccess = { Result.Success(it) },
                onFailure = { Result.Error(it) })
        }
    }
}
