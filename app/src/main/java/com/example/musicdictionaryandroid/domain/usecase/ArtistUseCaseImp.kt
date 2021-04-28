package com.example.musicdictionaryandroid.domain.usecase

import androidx.lifecycle.LiveData
import com.example.musicdictionaryandroid.data.repository.ApiServerRepository
import com.example.musicdictionaryandroid.data.repository.DataBaseRepository
import com.example.musicdictionaryandroid.data.repository.PreferenceRepository
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val dataBaseRepository: DataBaseRepository,
    private val preferenceRepository: PreferenceRepository,
    private val externalScope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ArtistUseCase {

    // region ホームタブ

    // 検索条件に一致するアーティスト取得
    override suspend fun getArtistsBy(artist: Artist): Result<List<ArtistContents>> {
        return apiRepository.getArtistsBy(artist)
    }

    // おすすめアーティスト検索
    override suspend fun getArtistsByRecommend(): Result<List<ArtistContents>> {
        val email = preferenceRepository.getEmail()
        return apiRepository.getArtistsByRecommend(email!!)
    }

    // 急上昇アーティスト取得
    override suspend fun getArtistsBySoaring(): Result<List<ArtistContents>> {
        return apiRepository.getArtistsBySoaring()
    }

    //endregion

    // region 設定タブ

    // ユーザの登録したアーティスト取得
    override suspend fun getArtistsByEmail(): Result<List<Artist>> {
        val email = preferenceRepository.getEmail()
        return when (val result = apiRepository.getArtistsByEmail(email!!)) {
            is Result.Success -> {
                preferenceRepository.setFavorite(result.data.size)
                dataBaseRepository.updateAll(result.data)
                result
            }
            is Result.Error -> {
                Result.Success(dataBaseRepository.getArtistAll())
            }
        }
    }

    // アーティスト登録
    override suspend fun addArtist(artist: Artist): Result<Artist> {
        val email = preferenceRepository.getEmail()
        return when (val result = apiRepository.addArtist(artist, email!!)) {
            is Result.Success -> {
                externalScope.launch {
                    val size = preferenceRepository.getFavorite()
                    preferenceRepository.setFavorite(size + 1)
                    dataBaseRepository.addArtist(result.data)
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
        val email = preferenceRepository.getEmail()
        return when (val result = apiRepository.updateArtist(artist, email!!)) {
            is Result.Success -> {
                externalScope.launch {
                    dataBaseRepository.deleteAll()
                    dataBaseRepository.updateArtist(result.data)
                }.join()
                result
            }
            is Result.Error -> result
        }
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String): Result<List<Artist>> {
        val email = preferenceRepository.getEmail()
        return when (val result = apiRepository.deleteArtist(name, email!!)) {
            is Result.Success -> {
                externalScope.launch {
                    dataBaseRepository.deleteArtist(name)
                    val size = preferenceRepository.getFavorite()
                    preferenceRepository.setFavorite(size - 1)
                }.join()
                val artist = dataBaseRepository.getArtistAll()
                Result.Success(artist)
            }
            is Result.Error -> result
        }
    }

    // アーティストリスト取得
    override fun getArtistList(): LiveData<List<Artist>> {
        return dataBaseRepository.getArtistList()
    }

    //endregion
}
