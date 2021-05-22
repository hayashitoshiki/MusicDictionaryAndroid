package com.example.data.repository

import com.example.data.local.database.entity.ArtistEntity
import com.example.data.local.database.entity.BookmarkArtistEntity
import com.example.data.remote.network.dto.ArtistDto
import com.example.data.remote.network.dto.UserDto
import com.example.domain.model.entity.Artist
import com.example.domain.model.entity.ArtistContents
import com.example.domain.model.entity.User
import com.example.domain.model.value.*

/**
 * ドメインモデルからDto or Entityへのコンバータークラス
 */
object Converter {

    // region アーティストEntity変換

    // アーティストテーブルからアーティストモデルべ変換
    fun artistFromArtistEntity(artistEntity: ArtistEntity): Artist {
        val name = artistEntity.name
        val gender = Gender.getEnumByValue(artistEntity.gender)
        val voice = Voice(artistEntity.voice)
        val length = Length(artistEntity.length)
        val lyrics = Lyrics(artistEntity.lyrics)
        val genre1 = Genre1(artistEntity.genre1)
        val genre2 = Genre2(artistEntity.genre2)
        return Artist(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // アーティストモデルからアーティストテーブルへ変換
    fun artistEntityFromArtist(artist: Artist): ArtistEntity {
        val name = artist.name
        val gender = artist.gender.value
        val voice = artist.voice.value
        val length = artist.length.value
        val lyrics = artist.lyrics.value
        val genre1 = artist.genre1.value
        val genre2 = artist.genre2.value
        return ArtistEntity(null, name, gender, voice, length, lyrics, genre1, genre2)
    }

    // endregion

    // region ブックマークアーティストEntity変換

    // アーティスト詳細情報モデルからブックマークアーティストEntityへ変換
    fun bookmarkArtistEntityFromArtistContents(artistContents: ArtistContents): BookmarkArtistEntity {
        val id = null
        val name = artistContents.artist.name
        val gender = artistContents.artist.gender.value
        val voice = artistContents.artist.voice.value
        val length = artistContents.artist.length.value
        val lyrics = artistContents.artist.lyrics.value
        val genre1 = artistContents.artist.genre1.value
        val genre2 = artistContents.artist.genre2.value
        val thumb = artistContents.thumb
        val preview = artistContents.preview
        val generation1 = artistContents.generation1
        val generation2 = artistContents.generation2
        val generation3 = artistContents.generation3
        val generation4 = artistContents.generation4
        val generation5 = artistContents.generation5
        val generation6 = artistContents.generation6
        val userMan = artistContents.user_man
        val userWoman = artistContents.user_woman
        return BookmarkArtistEntity(
            id,
            name,
            gender,
            voice,
            length,
            lyrics,
            genre1,
            genre2,
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

    // ブックマークアーティストEntityからアーティスト詳細情報モデルへ変換
    fun artistContentsFromBookmarkArtistEntity(bookmarkArtist: BookmarkArtistEntity): ArtistContents {
        val name = bookmarkArtist.name
        val gender = Gender.getEnumByValue(bookmarkArtist.gender)
        val voice = Voice(bookmarkArtist.voice)
        val length = Length(bookmarkArtist.length)
        val lyrics = Lyrics(bookmarkArtist.lyrics)
        val genre1 = Genre1(bookmarkArtist.genre1)
        val genre2 = Genre2(bookmarkArtist.genre2)
        val artist = Artist(name, gender, voice, length, lyrics, genre1, genre2)
        val thumb = bookmarkArtist.thumb
        val preview = bookmarkArtist.preview
        val generation1 = bookmarkArtist.generation1
        val generation2 = bookmarkArtist.generation2
        val generation3 = bookmarkArtist.generation3
        val generation4 = bookmarkArtist.generation4
        val generation5 = bookmarkArtist.generation5
        val generation6 = bookmarkArtist.generation6
        val userMan = bookmarkArtist.userMan
        val userWoman = bookmarkArtist.userWoman
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

    // endregion

    // region アーティストDto変換

    // アーティストDtoからアーティストモデルへ変換
    fun artistArtistDto(artistFrom: ArtistDto): Artist {
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
    fun artistContentsFromArtistDto(artistDto: ArtistDto): ArtistContents {
        val artist = artistArtistDto(artistDto)
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
    fun artistDtoFromArtist(artist: Artist): ArtistDto {
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
    fun artistDtoFromArtistConditions(artist: ArtistConditions): ArtistDto {
        val name = artist.name ?: ""
        val gender = artist.gender?.value ?: 0
        val voice = artist.voice?.value ?: 0
        val length = artist.length?.value ?: 0
        val lyrics = artist.lyrics?.value ?: 0
        val genre1 = artist.genre1?.value ?: 0
        val genre2 = artist.genre2?.value ?: 0
        return ArtistDto(name, gender, voice, length, lyrics, genre1, genre2)
    }

    // endregion

    // region ユーザDto変換

    // ユーザDtoからユーザモデルへ変換
    fun userFromUserDto(userDto: UserDto): User {
        val email = userDto.email
        val name = userDto.name
        val gender = userDto.gender
        val area = userDto.area
        val birthday = userDto.birthday
        val artistCount = userDto.artist_count
        return User(email, name, gender, area, birthday, artistCount)
    }

    // endregion
}
