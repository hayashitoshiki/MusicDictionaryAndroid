package com.example.musicdictionaryandroid.ui.home

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.domain.model.value.Gender
import com.example.musicdictionaryandroid.ui.util.MessageUtilImp

/**
 * ResultAdapterのアーティスト要素以外のView要素保持クラス
 */
class ResultAdapterState {
    // Viewの状態保持
    private val _isGenerationPieChart = MutableLiveData(false)
    val isGenerationPieChart: LiveData<Boolean> = _isGenerationPieChart
    private val _isGenderPieChart = MutableLiveData(false)
    val isGenderPieChart: LiveData<Boolean> = _isGenderPieChart
    private val _isDetailsProfile = MutableLiveData(false)
    val isDetailsProfile: LiveData<Boolean> = _isDetailsProfile
    private val _playbackButtonImage = MutableLiveData(0)
    val playbackButtonImage: LiveData<Int> = _playbackButtonImage
    private val _bookmarkButtonImage = MutableLiveData(0)
    val bookmarkButtonImage: LiveData<Int> = _bookmarkButtonImage
    private val _playBackUrl = MutableLiveData("about:blank")
    val playBackUrl: LiveData<String> = _playBackUrl
    private val _detailButtonText = MutableLiveData("")
    val detailButtonText: LiveData<String> = _detailButtonText

    init {
        _detailButtonText.value = MessageUtilImp.getString(R.string.page_open)
        _playbackButtonImage.value = R.mipmap.ic_button_music_play_32
    }

    fun startPlayback(url: String) {
        _playbackButtonImage.value = R.mipmap.ic_button_music_pause_32
        _playBackUrl.value = url
    }

    fun stopPlayback() {
        _playbackButtonImage.value = R.mipmap.ic_button_music_play_32
        _playBackUrl.value = "about:blank"
    }

    fun closeDetailsLayout() {
        _isGenerationPieChart.value = false
        _isGenderPieChart.value = false
        _isDetailsProfile.value = false
        _detailButtonText.value = MessageUtilImp.getString(R.string.page_open)
    }

    fun setIsDetailsProfile(bool: Boolean) {
        _isDetailsProfile.value = bool
        _detailButtonText.value = MessageUtilImp.getString(R.string.page_close)
    }

    fun setIsGenerationPieChart(bool: Boolean) {
        _isGenerationPieChart.value = bool
    }

    fun setIsGenderPieChart(bool: Boolean) {
        _isGenderPieChart.value = bool
    }

    fun setBookmarkFlg(bool: Boolean) {
        when (bool) {
            true -> _bookmarkButtonImage.value = R.drawable.ic_star_yellow_32
            false -> _bookmarkButtonImage.value = R.drawable.ic_star_gray_32
        }
    }


    // 性別名取得
    fun getGenderText(artistSearchContents: ArtistSearchContents.Item): String {
        return MessageUtilImp.getGender(artistSearchContents.value.artist.gender.value)
    }

    // 性別の色取得
    fun getGenderColor(artistSearchContents: ArtistSearchContents.Item): Int {
        return when (artistSearchContents.value.artist.gender) {
            Gender.MAN -> Color.BLUE
            Gender.WOMAN -> Color.RED
        }
    }

    fun getGenre1Text(artistSearchContents: ArtistSearchContents.Item): String {
        return MessageUtilImp.getGenre1(artistSearchContents.value.artist.genre1.value)
    }

    fun getGenre2Text(artistSearchContents: ArtistSearchContents.Item): String {
        return MessageUtilImp.getGenre2(
            artistSearchContents.value.artist.genre1.value,
            artistSearchContents.value.artist.genre2.value
        )
    }


    // region ヘッダー

    fun isName(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return !artistSearchContents.value.name.isNullOrEmpty() && !(artistSearchContents.value.name == "急上昇" || artistSearchContents.value.name == "おすすめ")
    }

    fun isGender(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return artistSearchContents.value.gender != null && artistSearchContents.value.gender.value != 0
    }

    fun isLyric(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return artistSearchContents.value.lyrics != null && artistSearchContents.value.lyrics.value != 0
    }

    fun isLength(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return artistSearchContents.value.length != null && artistSearchContents.value.length.value != 0
    }

    fun isVoice(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return artistSearchContents.value.voice != null && artistSearchContents.value.voice.value != 0
    }

    fun isGenre1(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return artistSearchContents.value.genre1 != null && artistSearchContents.value.genre1.value != 0
    }

    fun isGenre2(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return artistSearchContents.value.genre2 != null && artistSearchContents.value.genre2.value != 0
    }

    fun isSubmitButton(artistSearchContents: ArtistSearchContents.Conditions): Boolean {
        return !(artistSearchContents.value.name == "急上昇" || artistSearchContents.value.name == "おすすめ")
    }

    fun isThumb(artistSearchContents: ArtistSearchContents.Item): Boolean {
        return !artistSearchContents.value.thumb.isNullOrEmpty()
    }

    fun isPlay(artistSearchContents: ArtistSearchContents.Item): Boolean {
        return !artistSearchContents.value.preview.isNullOrEmpty()
    }

    fun getTitleText(artistSearchContents: ArtistSearchContents.Conditions): String {
        return if (artistSearchContents.value.name != null && (artistSearchContents.value.name == "急上昇" || artistSearchContents.value.name == "おすすめ")) {
            artistSearchContents.value.name
        } else MessageUtilImp.getString(R.string.search_label_title)
    }

    fun getGenderTextByHeader(artistSearchContents: ArtistSearchContents.Conditions): String? {
        return if (artistSearchContents.value.gender != null) {
            MessageUtilImp.getString(R.string.search_label_gender) + MessageUtilImp.getGender(artistSearchContents.value.gender.value)
        } else null
    }

    fun getVoiceText(artistSearchContents: ArtistSearchContents.Conditions): String? {
        return if (artistSearchContents.value.voice != null) {
            MessageUtilImp.getString(R.string.search_label_voice) + MessageUtilImp.getVoice(artistSearchContents.value.voice.value)
        } else null
    }

    fun getLengthText(artistSearchContents: ArtistSearchContents.Conditions): String? {
        return if (artistSearchContents.value.length != null) {
            MessageUtilImp.getString(R.string.search_label_length) + MessageUtilImp.getLength(artistSearchContents.value.length.value)
        } else null
    }

    fun getLyricsText(artistSearchContents: ArtistSearchContents.Conditions): String? {
        return if (artistSearchContents.value.lyrics != null) {
            MessageUtilImp.getString(R.string.search_label_lyrics) + MessageUtilImp.getLength(artistSearchContents.value.lyrics.value)
        } else null
    }

    fun getGenre1Text(artistSearchContents: ArtistSearchContents.Conditions): String? {
        return if (artistSearchContents.value.genre1 != null) {
            MessageUtilImp.getString(R.string.search_label_genre1) + MessageUtilImp.getLength(artistSearchContents.value.genre1.value)
        } else null
    }

    fun getGenre2Text(artistSearchContents: ArtistSearchContents.Conditions): String? {
        return if (artistSearchContents.value.genre2 != null) {
            MessageUtilImp.getString(R.string.search_label_genre2) + MessageUtilImp.getLength(artistSearchContents.value.genre2.value)
        } else null
    }
}
