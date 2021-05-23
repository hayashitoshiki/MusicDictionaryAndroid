package com.example.domain.usecase

import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.ArtistConditions
import com.example.domain.model.value.ArtistSearchContents
import com.example.domain.model.value.Result
import com.example.domain.repository.LocalArtistRepository
import com.example.domain.repository.LocalBookmarkArtistRepository
import com.example.domain.repository.LocalUserRepository
import com.example.domain.repository.RemoteArtistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ArtistUseCaseImp(
    private val remoteArtistRepository: RemoteArtistRepository,
    private val localArtistRepository: LocalArtistRepository,
    private val localUserRepository: LocalUserRepository,
    private val localBookmarkArtistRepository: LocalBookmarkArtistRepository,
    private val externalScope: CoroutineScope
) : ArtistUseCase {

    // region ホームタブ

    // 検索条件に一致するアーティスト取得
    override suspend fun getArtistsBy(artist: ArtistConditions): Result<List<ArtistContents>> {
        return when (val list = remoteArtistRepository.getArtistsBy(artist)) {
            is Result.Success -> {
                list.data.map { artistContents ->
                    val artistName = artistContents.artist.name
                    val isBookmark = localBookmarkArtistRepository.isArtistByName(artistName)
                    artistContents.bookmarkFlg = isBookmark
                }
                list
            }
            is Result.Error -> list
        }
    }

    // おすすめアーティスト検索
    override suspend fun getArtistsByRecommend(): Result<List<ArtistContents>> {
        val email = localUserRepository.getEmail()
        return when (val list = remoteArtistRepository.getArtistsByRecommend(email)) {
            is Result.Success -> {
                list.data.map { artistContents ->
                    val artistName = artistContents.artist.name
                    val isBookmark = localBookmarkArtistRepository.isArtistByName(artistName)
                    artistContents.bookmarkFlg = isBookmark
                }
                list
            }
            is Result.Error -> list
        }
    }

    // 急上昇アーティスト取得
    override suspend fun getArtistsBySoaring(): Result<List<ArtistContents>> {
        return when (val list = remoteArtistRepository.getArtistsBySoaring()) {
            is Result.Success -> {
                list.data.map { artistContents ->
                    val artistName = artistContents.artist.name
                    val isBookmark = localBookmarkArtistRepository.isArtistByName(artistName)
                    artistContents.bookmarkFlg = isBookmark
                }
                list
            }
            is Result.Error -> list
        }
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
    override suspend fun deleteArtist(name: String): Result<String> {
        val email = localUserRepository.getEmail()
        return when (val result = remoteArtistRepository.deleteArtist(name, email)) {
            is Result.Success -> {
                externalScope.launch {
                    localArtistRepository.deleteArtist(name)
                    val size = localUserRepository.getFavorite()
                    localUserRepository.setFavorite(size - 1)
                }.join()
                Result.Success("Success")
            }
            is Result.Error -> result
        }
    }

    // アーティストリスト取得
    override fun getArtistList(): Flow<List<Artist>> {
        return localArtistRepository.getArtistAll()
    }

    //endregion

    // region お気に入り設定

    // ブックマーク登録
    override suspend fun setBookmarkArtist(artistContents: ArtistContents) {
        localBookmarkArtistRepository.addArtist(artistContents)
    }

    // ブックマーク登録解除
    override suspend fun deleteBookmarkArtist(artistContents: ArtistContents) {
        localBookmarkArtistRepository.deleteArtist(artistContents.artist.name)
    }

    // 全ブックマーク登録済みアーティスト取得
    override fun getBookArkArtistAll(): Flow<List<ArtistSearchContents.Item>> {
        return localBookmarkArtistRepository.getArtistAll().map { artistContentsList ->
            artistContentsList.map { artistContents ->
                artistContents.bookmarkFlg = true
                ArtistSearchContents.Item(artistContents)
            }
        }
    }

    // endregion
}
