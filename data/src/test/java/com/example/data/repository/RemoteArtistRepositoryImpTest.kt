package com.example.data.repository

import com.example.data.BaseTestUnit
import com.example.data.remote.network.Provider
import com.example.data.remote.network.dto.*
import com.example.data.remote.network.service.MusicDictionaryService
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.value.*
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteArtistRepositoryImpTest : BaseTestUnit() {

    // mock
    private lateinit var repository: RemoteArtistRepositoryImp
    private lateinit var provider: Provider
    private lateinit var musicDictionaryApi: MusicDictionaryService

    // data
    private val successArtistConditions = ArtistConditions("success", null, null, null, null, null, null)
    private val failureArtistConditions = ArtistConditions("failure", null, null, null, null, null, null)
    private val successArtistDto = convertArtistDtoFromArtistConditions(successArtistConditions).getMapList()
    private val artist = Artist("artist1", Gender.MAN, Voice(1), Length(1), Lyrics(1), Genre1(1), Genre2(1))
    private val artistDto = convertArtistDtoFromArtist(artist)
    private val statusDto = StatusDto(200, "Success")
    private val statusResponseDto = StatusResponseDto(statusDto)
    private val artistContentsDtoList = listOf(artistDto)
    private val successArtistResponseDto = ArtistResponseDto(statusDto, artistDto)
    private val successArtistsResponseDto = ArtistsResponseDto(statusDto, artistContentsDtoList)
    private val artistContents = convertArtistContentsFromArtistDto(artistDto)
    private val artistContentsList = listOf(artistContents)
    private val successEmail = "success"
    private val failureEmail = "failure"
    private val successArtistResult = Result.Success(artist)
    private val successArtistListResult = Result.Success(listOf(artist))
    private val successResult = Result.Success("Success")
    private val successArtistContentsListResult = Result.Success(artistContentsList)

    @Before
    fun setUp() {
        musicDictionaryApi = mockk<MusicDictionaryService>().also {
            coEvery { it.search(successArtistDto) } returns successArtistsResponseDto
            coEvery { it.getRecommend(successEmail) } returns successArtistsResponseDto
            coEvery { it.getSoaring() } returns successArtistsResponseDto
            coEvery { it.findByEmail(successEmail) } returns successArtistsResponseDto
            coEvery { it.addArtist(any(), successEmail) } returns successArtistResponseDto
            coEvery { it.updateArtist(any(), successEmail) } returns successArtistResponseDto
            coEvery { it.deleteArtist(any(), successEmail) } returns statusResponseDto
        }
        provider = mockk<Provider>().also {
            every { it.musicDictionaryApi() } returns musicDictionaryApi
        }
        repository = RemoteArtistRepositoryImp(provider, testDispatcher)
    }


    // アーティストDtoからアーティストモデルへ変換
    private fun convertArtistFromArtistDto(artistFrom: ArtistDto): Artist {
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
    private fun convertArtistContentsFromArtistDto(artistDto: ArtistDto): ArtistContents {
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
    private fun convertArtistDtoFromArtist(artist: Artist): ArtistDto {
        val name = artist.name
        val gender = artist.gender.value
        val voice = artist.voice.value
        val length = artist.length.value
        val lyrics = artist.lyrics.value
        val genre1 = artist.genre1.value
        val genre2 = artist.genre2.value
        return ArtistDto(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // アーティスト検索条件からアーティストDtoへ変換
    private fun convertArtistDtoFromArtistConditions(artist: ArtistConditions): ArtistDto {
        val name = artist.name ?: ""
        val gender = artist.gender?.value ?: 0
        val voice = artist.voice?.value ?: 0
        val length = artist.length?.value ?: 0
        val lyrics = artist.lyrics?.value ?: 0
        val genre1 = artist.genre1?.value ?: 0
        val genre2 = artist.genre2?.value ?: 0
        return ArtistDto(name, gender, voice, length, lyrics, genre1, genre2)
    }


    @After
    fun tearDown() {
    }

    // region 検索条件に一致するアーティスト検索

    /**
     * アーティスト検索
     *
     * 条件：APIが成功を返すこと
     * 結果：APIから取得したアーティストリストがドメインモデル形式に変換されて返ること
     */
    @Test
    fun getArtistByConditionBySuccess() {
        runBlocking {
            val result = repository.getArtistsBy(successArtistConditions)
            assertEquals(successArtistContentsListResult, result)
        }
    }

    /**
     * アーティスト検索
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun getArtistByConditionByFailure() {
        runBlocking {
            val result = repository.getArtistsBy(failureArtistConditions) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region おすすめアーティスト検索

    /**
     * おすすめアーティスト取得
     *
     * 条件：APIが成功を返すこと
     * 結果：APIから取得したアーティストリストがドメインモデル形式に変換されて返ること
     */
    @Test
    fun getArtistByRecommendBySuccess() {
        runBlocking {
            val result = repository.getArtistsByRecommend(successEmail)
            assertEquals(successArtistContentsListResult, result)
        }
    }

    /**
     * おすすめアーティスト取得
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun getArtistByRecommendByFailure() {
        runBlocking {
            val result = repository.getArtistsByRecommend(failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region 急上昇アーティスト検索

    /**
     * 急上昇アーティスト取得
     *
     * 条件：APIが成功を返すこと
     * 結果：APIから取得したアーティストリストがドメインモデル形式に変換されて返ること
     */
    @Test
    fun getArtistBySoaringBySuccess() {
        runBlocking {
            val result = repository.getArtistsBySoaring()
            assertEquals(successArtistContentsListResult, result)
        }
    }

    /**
     * 急上昇アーティスト取得
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun getArtistBySoaringByFailure() {
        musicDictionaryApi = mockk<MusicDictionaryService>()
        provider = mockk<Provider>().also {
            every { it.musicDictionaryApi() } returns musicDictionaryApi
        }
        repository = RemoteArtistRepositoryImp(provider, testDispatcher)
        runBlocking {
            val result = repository.getArtistsBySoaring() is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region ユーザの登録したアーティスト取得

    /**
     * ユーザの登録したアーティスト取得
     *
     * 条件：APIが成功を返すこと
     * 結果：APIから取得したアーティストリストがドメインモデル形式に変換されて返ること
     */
    @Test
    fun getArtistByEmailBySuccess() {
        runBlocking {
            val result = repository.getArtistsByEmail(successEmail)
            assertEquals(successArtistListResult, result)
        }
    }

    /**
     * ユーザの登録したアーティスト取得
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun getArtistByEmailByFailure() {
        runBlocking {
            val result = repository.getArtistsByEmail(failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region アーティスト登録

    /**
     * アーティスト登録
     *
     * 条件：APIが成功を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun addArtistBySuccess() {
        runBlocking {
            val result = repository.addArtist(artist, successEmail)
            assertEquals(successArtistResult, result)
        }
    }

    /**
     * アーティスト登録
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun addArtistByFailure() {
        runBlocking {
            val result = repository.addArtist(artist, failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region アーティスト更新

    /**
     * アーティスト更新
     *
     * 条件：APIが成功を返すこと
     * 結果：APIから取得したアーティストリストがドメインモデル形式に変換されて返ること
     */
    @Test
    fun updateArtistBySuccess() {
        runBlocking {
            val result = repository.updateArtist(artist, successEmail)
            assertEquals(successArtistResult, result)
        }
    }

    /**
     * アーティスト更新
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果そのまま返ること
     */
    @Test
    fun updateArtistByFailure() {
        runBlocking {
            val result = repository.updateArtist(artist, failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion

    // region アーティスト削除

    /**
     * アーティスト削除
     *
     * 条件：APIが成功を返すこと
     * 結果：APIからの結果そのまま返ること
     */
    @Test
    fun deleteArtistBySuccess() {
        runBlocking {
            val result = repository.deleteArtist(artist.name, successEmail)
            assertEquals(successResult, result)
        }
    }

    /**
     * アーティスト削除
     *
     * 条件：APIが失敗を返すこと
     * 結果：APIからの結果がそのまま返ること
     */
    @Test
    fun deleteArtistByFailure() {
        runBlocking {
            val result = repository.deleteArtist(artist.name, failureEmail) is Result.Error
            assertEquals(true, result)
        }
    }

    // endregion
}
