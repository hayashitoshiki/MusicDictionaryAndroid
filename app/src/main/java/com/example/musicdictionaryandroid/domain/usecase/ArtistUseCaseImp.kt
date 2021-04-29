package com.example.musicdictionaryandroid.domain.usecase

import com.example.musicdictionaryandroid.data.repository.LocalArtistRepository
import com.example.musicdictionaryandroid.data.repository.LocalUserRepository
import com.example.musicdictionaryandroid.data.repository.RemoteArtistRepository
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions
import com.example.musicdictionaryandroid.domain.model.value.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ArtistUseCaseImp(
    private val remoteArtistRepository: RemoteArtistRepository,
    private val localArtistRepository: LocalArtistRepository,
    private val localUserRepository: LocalUserRepository,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ArtistUseCase {

    // region ホームタブ

    // 検索条件に一致するアーティスト取得
    override suspend fun getArtistsBy(artist: ArtistConditions): Result<List<ArtistContents>> {
        return remoteArtistRepository.getArtistsBy(artist)
    }

    // おすすめアーティスト検索
    override suspend fun getArtistsByRecommend(): Result<List<ArtistContents>> {
        val email = localUserRepository.getEmail()
        return remoteArtistRepository.getArtistsByRecommend(email)
    }

    // 急上昇アーティスト取得
    override suspend fun getArtistsBySoaring(): Result<List<ArtistContents>> {
        return remoteArtistRepository.getArtistsBySoaring()
    }

    //endregion

    // region 設定タブ

    // ユーザの登録したアーティスト取得
    override suspend fun getArtistsByEmail(): Result<List<Artist>> {
        val email = localUserRepository.getEmail()
        return when (val result = remoteArtistRepository.getArtistsByEmail(email)) {
            is Result.Success -> {
                localUserRepository.setFavorite(result.data.size)
                localArtistRepository.updateAll(result.data)
                result
            }
            is Result.Error -> {
                result
            }
        }
    }

    // アーティスト登録
    override suspend fun addArtist(artist: Artist): Result<Artist> {
        val email = localUserRepository.getEmail()
        return when (val result = remoteArtistRepository.addArtist(artist, email)) {
            is Result.Success -> {
                externalScope.launch {
                    val size = localUserRepository.getFavorite()
                    localUserRepository.setFavorite(size + 1)
                    localArtistRepository.addArtist(result.data)
                }.join()
                result
            }
            is Result.Error -> {
                result
            }
        }
    }

    // アーティスト更新
    override suspend fun updateArtist(artist: Artist): Result<Artist> {
        val email = localUserRepository.getEmail()
        return when (val result = remoteArtistRepository.updateArtist(artist, email)) {
            is Result.Success -> {
                externalScope.launch {
                    localArtistRepository.updateArtist(result.data)
                }.join()
                result
            }
            is Result.Error -> result
        }
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String): Result<List<Artist>> {
        val email = localUserRepository.getEmail()
        return when (val result = remoteArtistRepository.deleteArtist(name, email)) {
            is Result.Success -> {
                externalScope.launch {
                    localArtistRepository.deleteArtist(name)
                    val size = localUserRepository.getFavorite()
                    localUserRepository.setFavorite(size - 1)
                }.join()
                val artist = localArtistRepository.getArtistAll()
                Result.Success(artist)
            }
            is Result.Error -> result
        }
    }

    // アーティストリスト取得
    override fun getArtistList(): Flow<List<Artist>> {
        return localArtistRepository.getArtistList()
    }

    //endregion
}
