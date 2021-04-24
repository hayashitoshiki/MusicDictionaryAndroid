package com.example.musicdictionaryandroid.model.usecase

import androidx.lifecycle.LiveData
import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ArtistUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val dataBaseRepository: DataBaseRepository,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ArtistUseCase {

    // region ホームタブ

    // 検索条件に一致するアーティスト取得
    override suspend fun getArtistsBy(artists: ArtistsForm): Result<List<ArtistsForm>?> {
        return apiRepository.getArtistsBy(artists)
    }

    // おすすめアーティスト検索
    override suspend fun getArtistsByRecommend(email: String): Result<List<ArtistsForm>?> {
        return apiRepository.getArtistsByRecommend(email)
    }

    // 急上昇アーティスト取得
    override suspend fun getArtistsBySoaring(): Result<List<ArtistsForm>?> {
        return apiRepository.getArtistsBySoaring()
    }

    //endregion

    // region 設定タブ

    // ユーザの登録したアーティスト取得
    override suspend fun getArtistsByEmail(email: String): Result<List<ArtistsForm>?> {
        return when (val result = apiRepository.getArtistsByEmail(email)) {
            is Result.Success -> {
                PreferenceRepositoryImp.setFavorite(result.data.size)
                dataBaseRepository.updateAll(result.data)
                result
            }
            is Result.Error -> Result.Success(dataBaseRepository.getArtistAll())
        }

    }

    // アーティスト登録
    override suspend fun addArtist(artist: Artist, email: String): Result<ArtistsForm?> {
        val artistsFrom = ArtistsForm(
            artist.name!!,
            artist.gender!!,
            artist.voice!!,
            artist.length!!,
            artist.lyrics!!,
            artist.genre1!!,
            artist.genre2!!
        )
        return when (val result = apiRepository.addArtist(artistsFrom, email)) {
            is Result.Success -> {
                dataBaseRepository.addArtist(result.data)
                result
            }
            is Result.Error -> {
                result
            }
        }
    }

    // アーティスト更新
    override suspend fun updateArtist(artist: Artist, email: String): Result<ArtistsForm?> {
        val artistsFrom = ArtistsForm(
            artist.name!!,
            artist.gender!!,
            artist.voice!!,
            artist.length!!,
            artist.lyrics!!,
            artist.genre1!!,
            artist.genre2!!
        )
        return when (val result = apiRepository.updateArtist(artistsFrom, email)) {
            is Result.Success -> {
                dataBaseRepository.deleteAll()
                dataBaseRepository.updateArtist(result.data)
                result
            }
            is Result.Error -> result
        }
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String, email: String): Result<List<ArtistsForm>> {
        return try {
            apiRepository.deleteArtist(name, email)
            dataBaseRepository.deleteArtist(name)
            val size = PreferenceRepositoryImp.getFavorite()
            PreferenceRepositoryImp.setFavorite(size - 1)
            val artist = dataBaseRepository.getArtistAll()
            Result.Success(artist)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    // アーティストリスト取得
    override fun getArtistList(): LiveData<List<Artist>> {
        return dataBaseRepository.getArtistList()
    }

    //endregion
}
