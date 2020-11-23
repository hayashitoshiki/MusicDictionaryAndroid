package com.example.musicdictionaryandroid.model.usecase

import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Result
import java.util.ArrayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtistUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val dataBaseRepository: DataBaseRepository
) : ArtistUseCase {

    // 検索条件に一致するアーティスト取得
    override suspend fun getArtistsBy(artists: ArtistsForm): Result<ArrayList<ArtistsForm>?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.getArtistsBy(artists)
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    // おすすめアーティスト検索
    override suspend fun getArtistsByRecommend(email: String): Result<ArrayList<ArtistsForm>?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.getArtistsByRecommend(email)
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    // 急上昇アーティスト取得
    override suspend fun getArtistsBySoaring(): Result<ArrayList<ArtistsForm>?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.getArtistsBySoaring()
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    /*----------------------------------------
        設定タブ
     ----------------------------------------*/
    // ユーザの登録したアーティスト取得
     override suspend fun getArtistsByEmail(email: String): Result<ArrayList<ArtistsForm>?> {
          return withContext(Dispatchers.IO) {
              try {
                  val result = apiRepository.getArtistsByEmail(email)
                  result.body()?.let{
                      PreferenceRepositoryImp.setFavorite(it.size)
                      dataBaseRepository.updateAll(it)
                  }?: run { PreferenceRepositoryImp.setFavorite(0) }
                  return@withContext Result.Success(result.body())
              } catch (e: Exception) {
                  return@withContext Result.Success(dataBaseRepository.getArtistAll())
           }
        }
    }

    // アーティスト登録
    override suspend fun addArtist(artist: ArtistsForm, email: String): Result<ArtistsForm?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.addArtist(artist, email)
                dataBaseRepository.addArtist(result.body()!!)
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    // アーティスト更新
    override suspend fun updateArtist(artist: ArtistsForm, email: String): Result<ArtistsForm?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.updateArtist(artist, email)
                dataBaseRepository.deleteAll()
                dataBaseRepository.updateArtist(artist)
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    // アーティスト削除
    override suspend fun deleteArtist(name: String, email: String): Result<CallBackData?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.deleteArtist(name, email)
                val size = PreferenceRepositoryImp.getFavorite()
                PreferenceRepositoryImp.setFavorite(size - 1)
                dataBaseRepository.deleteArtist(name)
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
}
