package com.example.musicdictionaryandroid.data.repository

import com.example.musicdictionaryandroid.data.database.entity.CallBackData
import com.example.musicdictionaryandroid.data.database.entity.User
import com.example.musicdictionaryandroid.data.net.Provider
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.data.util.Result
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.entity.ArtistContents
import com.example.musicdictionaryandroid.domain.model.value.*
import java.util.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiServerRepositoryImp(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ApiServerRepository {

    // 指定した検索条件で検索した時のアーティストリストを返す  あくまで検索結果の"アーティストリストを返す"
    override suspend fun getArtistsBy(artist: ArtistConditions): Result<List<ArtistContents>> = withContext(ioDispatcher) {
        val artistDto = convertArtistDtoFromArtistConditions(artist)
        return@withContext runCatching { Provider.api().search(artistDto.getMapList()) }.fold(
            onSuccess = {
                val artistList = it.map { artistDto ->
                    convertArtistContentsFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // おすすめアーティストリストを返す
    override suspend fun getArtistsByRecommend(email: String): Result<List<ArtistContents>> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().getRecommend(email) }.fold(
            onSuccess = {
                val artistList = it.map { artistDto ->
                    convertArtistContentsFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // 急上昇アーティストリストを返す
    override suspend fun getArtistsBySoaring(): Result<List<ArtistContents>> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().getSoaring() }.fold(
            onSuccess = {
                val artistList = it.map { artistDto ->
                    convertArtistContentsFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー登録したアーティスト取得
    override suspend fun getArtistsByEmail(email: String): Result<List<Artist>> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().findByEmail(email) }.fold(
            onSuccess = {
                val artistList = it.map { artistDto ->
                    convertArtistFromArtistDto(artistDto)
                }
                Result.Success(artistList)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // アーティスト登録
    override suspend fun addArtist(artist: Artist, email: String): Result<Artist> = withContext(ioDispatcher) {
        val artistDto = convertArtistDtoFromArtist(artist)
        return@withContext runCatching { Provider.api().addArtist(artistDto.getMapList(), email) }.fold(
            onSuccess = { artistDtoResponse ->
                val result = convertArtistFromArtistDto(artistDtoResponse)
                Result.Success(result)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // アーティスト編集
    override suspend fun updateArtist(artist: Artist, email: String): Result<Artist> = withContext(ioDispatcher) {
        val artistDto = convertArtistDtoFromArtist(artist)
        return@withContext runCatching { Provider.api().updateArtist(artistDto.getMapList(), email) }.fold(
            onSuccess = { artistDtoResponse ->
                val result = convertArtistFromArtistDto(artistDtoResponse)
                Result.Success(result)
            },
            onFailure = { Result.Error(it) }
        )
    }

    // アーティスト削除
    override suspend fun deleteArtist(name: String, email: String): Result<String> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().deleteArtist(name, email) }.fold(
            onSuccess = { Result.Success("Success") },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー取得
    override suspend fun getUserByEmail(email: String): Result<User> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().getUserByEmail(email) }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー登録
    override suspend fun createUser(user: String): Result<String> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().createUser(user) }.fold(
            onSuccess = { Result.Success("Success") },
            onFailure = { Result.Error(it) }
        )
    }

    // ユーザー情報変更
    override suspend fun changeUser(user: User, email: String): Result<CallBackData> = withContext(ioDispatcher) {
        return@withContext runCatching { Provider.api().changeUser(user, email) }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it) }
        )
    }

    // アーティストDtoからアーティストモデルへ変換
    private fun convertArtistFromArtistDto(artistFrom: ArtistsDto): Artist {
        val name = artistFrom.name
        val gender = Gender.getEnumByValue(artistFrom.gender)
        val voice = Voice(artistFrom.voice)
        val length = Length(artistFrom.length)
        val lyrics = Lyrics(artistFrom.lyrics)
        val genre1 = Genre1(artistFrom.genre1)
        val genre2 = Genre2(artistFrom.genre2)
        return Artist(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // アーティストDtoからアーティス詳細情報モデルへ変換
    private fun convertArtistContentsFromArtistDto(artistDto: ArtistsDto): ArtistContents {
        val artist = convertArtistFromArtistDto(artistDto)
        val thumb = artistDto.thumb
        val preview = artistDto.preview
        val generation1 = artistDto.generation1
        val generation2 = artistDto.generation2
        val generation3 = artistDto.generation3
        val generation4 = artistDto.generation4
        val generation5 = artistDto.generation5
        val generation6 = artistDto.generation6
        val userMan = artistDto.user_man
        val userWoman = artistDto.user_woman
        return ArtistContents(
            artist,
            thumb,
            preview,
            generation1,
            generation2,
            generation3,
            generation4,
            generation5,
            generation6,
            userMan,
            userWoman
        )
    }

    // アーティストモデルからアーティストDtoへ変換
    private fun convertArtistDtoFromArtist(artist: Artist): ArtistsDto {
        val name = artist.name
        val gender = artist.gender.value
        val voice = artist.voice.value
        val length = artist.length.value
        val lyrics = artist.lyrics.value
        val genre1 = artist.genre1.value
        val genre2 = artist.genre2.value
        return ArtistsDto(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // アーティスト検索条件からアーティストDtoへ変換
    private fun convertArtistDtoFromArtistConditions(artist: ArtistConditions): ArtistsDto {
        val name = artist.name ?: ""
        val gender = artist.gender?.value ?: 0
        val voice = artist.voice?.value ?: 0
        val length = artist.length?.value ?: 0
        val lyrics = artist.lyrics?.value ?: 0
        val genre1 = artist.genre1?.value ?: 0
        val genre2 = artist.genre2?.value ?: 0
        return ArtistsDto(name, gender, voice, length, lyrics, genre1, genre2)
    }
}
