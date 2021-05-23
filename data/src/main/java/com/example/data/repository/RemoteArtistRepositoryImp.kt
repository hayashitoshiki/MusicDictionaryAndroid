package com.example.data.repository

import com.example.data.remote.network.Provider
import com.example.data.remote.network.ProviderImp
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import com.example.domain.repository.RemoteArtistRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteArtistRepositoryImp(
    private val provider: Provider = ProviderImp,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteArtistRepository {

    // region アーティスト検索

    // 指定した検索条件で検索した時のアーティストリストを返す
    override suspend fun getArtistsBy(artist: ArtistConditions): Result<List<ArtistContents>> = withContext(ioDispatcher) {
        val artistDto = Converter.artistDtoFromArtistConditions(artist)
        return@withContext runCatching { provider.musicDictionaryApi().search(artistDto.getMapList()) }.fold(
            onSuccess = {
                val artistList = it.artist.map { artistDto ->
                    Converter.artistContentsFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // おすすめアーティストリストを返す
    override suspend fun getArtistsByRecommend(email: String): Result<List<ArtistContents>> = withContext(ioDispatcher) {
        return@withContext runCatching { provider.musicDictionaryApi().getRecommend(email) }.fold(
            onSuccess = {
                val artistList = it.artist.map { artistDto ->
                    Converter.artistContentsFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // 急上昇アーティストリストを返す
    override suspend fun getArtistsBySoaring(): Result<List<ArtistContents>> = withContext(ioDispatcher) {
        return@withContext runCatching { provider.musicDictionaryApi().getSoaring() }.fold(
            onSuccess = {
                val artistList = it.artist.map { artistDto ->
                    Converter.artistContentsFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー登録したアーティスト取得
    override suspend fun getArtistsByEmail(email: String): Result<List<Artist>> = withContext(ioDispatcher) {
        return@withContext runCatching { provider.musicDictionaryApi().findByEmail(email) }.fold(
            onSuccess = {
                val artistList = it.artist.map { artistDto ->
                    Converter.artistArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // endregion

    // region アーティスト編集

    // アーティスト登録
    override suspend fun addArtist(artist: Artist, email: String): Result<Artist> = withContext(ioDispatcher) {
        val artistDto = Converter.artistDtoFromArtist(artist)
        return@withContext runCatching { provider.musicDictionaryApi().addArtist(artistDto.getMapList(), email) }.fold(
            onSuccess = { response ->
                val result = Converter.artistArtistDto(response.artist)
                Result.Success(result)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // アーティスト編集
    override suspend fun updateArtist(artist: Artist, email: String): Result<Artist> = withContext(ioDispatcher) {
        val artistDto = Converter.artistDtoFromArtist(artist)
        return@withContext runCatching { provider.musicDictionaryApi().updateArtist(artistDto.getMapList(), email) }.fold(
            onSuccess = { response ->
                val result = Converter.artistArtistDto(response.artist)
                Result.Success(result)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String, email: String): Result<String> = withContext(ioDispatcher) {
        return@withContext runCatching { provider.musicDictionaryApi().deleteArtist(name, email) }.fold(
            onSuccess = { Result.Success(it.status.message) },
            onFailure = { Result.Error(it) }
        )
    }

    // endregion
}
