package com.example.musicdictionaryandroid.model.usecase

import androidx.lifecycle.LiveData
import com.example.musicdictionaryandroid.model.entity.Artist
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.*
import com.example.musicdictionaryandroid.model.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtistUseCaseImp(
    private val apiRepository: ApiServerRepository,
    private val dataBaseRepository: DataBaseRepository
) : ArtistUseCase {

    // region ホームタブ

    // 検索条件に一致するアーティスト取得
    override suspend fun getArtistsBy(artists: ArtistsForm): Result<List<ArtistsForm>?> {
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
    override suspend fun getArtistsByRecommend(email: String): Result<List<ArtistsForm>?> {
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
    override suspend fun getArtistsBySoaring(): Result<List<ArtistsForm>?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = apiRepository.getArtistsBySoaring()
                return@withContext Result.Success(result.body())
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    //endregion

    // region 設定タブ

    // ユーザの登録したアーティスト取得
     override suspend fun getArtistsByEmail(email: String): Result<List<ArtistsForm>?> {
          return withContext(Dispatchers.IO) {
              try {
                  val result = apiRepository.getArtistsByEmail(email)
                  result.body()?.let {
                      PreferenceRepositoryImp.setFavorite(it.size)
                      dataBaseRepository.updateAll(it)
                  } ?: run { PreferenceRepositoryImp.setFavorite(0) }
                  return@withContext Result.Success(result.body())
              } catch (e: Exception) {
                  return@withContext Result.Success(dataBaseRepository.getArtistAll())
           }
        }
    }

    // アーティスト登録
    override suspend fun addArtist(artist: Artist, email: String): Result<ArtistsForm?> {
        return withContext(Dispatchers.IO) {
            try {
                val artistsFrom = ArtistsForm(
                    artist.name!!,
                    artist.gender!!,
                    artist.voice!!,
                    artist.length!!,
                    artist.lyrics!!,
                    artist.genre1!!,
                    artist.genre2!!
                )
                val result = apiRepository.addArtist(artistsFrom, email)
                if (result.body() != null) {
                    dataBaseRepository.addArtist(result.body()!!)
                    return@withContext Result.Success(result.body())
                } else {
                    @Suppress("UNREACHABLE_CODE")
                    return@withContext Result.Error(throw Exception("サーバーエラー: 更新できませんでした"))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    // アーティスト更新
    override suspend fun updateArtist(artist: Artist, email: String): Result<ArtistsForm?> {
        return withContext(Dispatchers.IO) {
            try {
                val artistsFrom = ArtistsForm(
                    artist.name!!,
                    artist.gender!!,
                    artist.voice!!,
                    artist.length!!,
                    artist.lyrics!!,
                    artist.genre1!!,
                    artist.genre2!!
                )
                val result = apiRepository.updateArtist(artistsFrom, email)
                if (result.body() != null) {
                    dataBaseRepository.deleteAll()
                    dataBaseRepository.updateArtist(result.body()!!)
                    return@withContext Result.Success(result.body())
                } else {
                    @Suppress("UNREACHABLE_CODE")
                    return@withContext Result.Error(throw Exception("サーバーエラー: 更新できませんでした"))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }
    // アーティスト削除
    override suspend fun deleteArtist(name: String, email: String): Result<List<ArtistsForm>> {
        return withContext(Dispatchers.IO) {
            try {
                apiRepository.deleteArtist(name, email)
                dataBaseRepository.deleteArtist(name)
                val size = PreferenceRepositoryImp.getFavorite()
                PreferenceRepositoryImp.setFavorite(size - 1)
                val artist = dataBaseRepository.getArtistAll()
                return@withContext Result.Success(artist)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }

    // アーティストリスト取得
    override fun getArtistList(): LiveData<List<Artist>> {
        return dataBaseRepository.getArtistList()
    }

    //endregion
}
