package com.example.musicdictionaryandroid.ui.home

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.domain.model.value.Gender
import com.example.musicdictionaryandroid.domain.model.value.Genre1
import com.example.musicdictionaryandroid.domain.model.value.Genre2
import com.example.musicdictionaryandroid.ui.util.MessageUtil

/**
 * ResultAdapterのアーティスト要素以外のボティView要素保持クラス
 */
class ResultAdapterBodyState(private val messageUtil: MessageUtil) {

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
        _detailButtonText.value = messageUtil.getString(R.string.page_open)
        _playbackButtonImage.value = R.mipmap.ic_button_music_play_32
    }

    // region　メインView

    // イメージ画像の有無
    fun isThumb(thumb: String?): Boolean {
        return !thumb.isNullOrEmpty()
    }

    // サンプル音源の有無
    fun isPreview(preview: String?): Boolean {
        return !preview.isNullOrEmpty()
    }

    // 再生設定
    fun startPlayback(url: String) {
        _playbackButtonImage.value = R.mipmap.ic_button_music_pause_32
        _playBackUrl.value = url
    }

    // 停止設定
    fun stopPlayback() {
        _playbackButtonImage.value = R.mipmap.ic_button_music_play_32
        _playBackUrl.value = "about:blank"
    }


    // 詳細画面表示・非表示
    fun setIsDetailsProfile(bool: Boolean) {
        _isDetailsProfile.value = bool
        if (bool) {
            _detailButtonText.value = messageUtil.getString(R.string.page_close)
        } else {
            _detailButtonText.value = messageUtil.getString(R.string.page_open)
        }
    }

    // 世代統計円グラフ表示・非表示
    fun setIsGenerationPieChart(bool: Boolean) {
        _isGenerationPieChart.value = bool
    }

    // 性別統計円グラフ表示・非表示
    fun setIsGenderPieChart(bool: Boolean) {
        _isGenderPieChart.value = bool
    }

    // ブックマーク画像設定
    fun setBookmarkFlg(bool: Boolean) {
        when (bool) {
            true -> _bookmarkButtonImage.value = R.drawable.ic_star_yellow_32
            false -> _bookmarkButtonImage.value = R.drawable.ic_star_gray_32
        }
    }

    // 性別名取得
    fun getGenderText(gender: Gender): String {
        return messageUtil.getGender(gender.value)
    }

    // 性別の色取得
    fun getGenderColor(gender: Gender): Int {
        return when (gender) {
            Gender.MAN -> Color.BLUE
            Gender.WOMAN -> Color.RED
        }
    }

    // ジャンル１文字列取得
    fun getGenre1Text(genre1: Genre1): String {
        return messageUtil.getGenre1(genre1.value)
    }

    // ジャンル２文字列取得
    fun getGenre2Text(genre1: Genre1, genre2: Genre2): String {
        return messageUtil.getGenre2(genre1.value, genre2.value)
    }

    // endregion
}
