package com.example.presentation.home

import android.graphics.Color
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.presentation.BaseTestUnit
import com.example.domain.model.value.Gender
import com.example.domain.model.value.Genre1
import com.example.domain.model.value.Genre2
import com.example.presentation.R
import com.example.presentation.util.MessageUtil
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ResultAdapterBodyStateTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    /// mock
    private lateinit var resultAdapterBodyState: ResultAdapterBodyState

    // data

    @Before
    fun setUp() {
        val messageUtil = mockk<MessageUtil>().also {
            every { it.getString(any()) } returns ""
            every { it.getGender(1) } returns "男性"
            every { it.getGender(2) } returns "女性"
            every { it.getString(R.string.page_open) } returns "▼"
            every { it.getString(R.string.page_close) } returns "▲"
            every { it.getGenre1(1) } returns "ポップス"
            every { it.getGenre1(2) } returns "ロッグ"
            every { it.getGenre2(1, 2) } returns "アニソン"
            every { it.getGenre2(2, 1) } returns "パンク"
        }
        resultAdapterBodyState = ResultAdapterBodyState(messageUtil)
        resultAdapterBodyState.playbackButtonImage.observeForever(observerInt)
        resultAdapterBodyState.playBackUrl.observeForever(observerString)
        resultAdapterBodyState.detailButtonText.observeForever(observerString)
        resultAdapterBodyState.isDetailsProfile.observeForever(observerBoolean)
        resultAdapterBodyState.isGenderPieChart.observeForever(observerBoolean)
        resultAdapterBodyState.isGenerationPieChart.observeForever(observerBoolean)
        resultAdapterBodyState.bookmarkButtonImage.observeForever(observerInt)
    }

    @After
    fun tearDown() {
    }

    // region イメージ画像の有無

    /**
     * イメージ画像の有無判定
     *
     * 条件：thumbがnull
     * 結果：falseが返ること
     */
    @Test
    fun isThumbByNull() {
        val result = resultAdapterBodyState.isThumb(null)
        assertEquals(false, result)
    }

    /**
     * イメージ画像の有無判定
     *
     * 条件：thumbが空
     * 結果：falseが返ること
     */
    @Test
    fun isThumbByEmpty() {
        val result = resultAdapterBodyState.isThumb("")
        assertEquals(false, result)
    }

    /**
     * イメージ画像の有無判定
     *
     * 条件：thumbあり
     * 結果：trueが返ること
     */
    @Test
    fun isThumbByNotEmpty() {
        val result = resultAdapterBodyState.isThumb("test")
        assertEquals(true, result)
    }

    // endregion

    // region イメージ画像の有無

    /**
     * サンプル音源の有無判定
     *
     * 条件：サンプル音源文字列がnull
     * 結果：falseが返ること
     */
    @Test
    fun isPreviewByNull() {
        val result = resultAdapterBodyState.isPreview(null)
        assertEquals(false, result)
    }

    /**
     * サンプル音源の有無判定
     *
     * 条件：サンプル音源文字列が空
     * 結果：falseが返ること
     */
    @Test
    fun isPreviewByEmpty() {
        val result = resultAdapterBodyState.isPreview("")
        assertEquals(false, result)
    }

    /**
     * サンプル音源の有無判定
     *
     * 条件：サンプル音源文字列あり
     * 結果：trueが返ること
     */
    @Test
    fun isPreviewByNotEmpty() {
        val result = resultAdapterBodyState.isPreview("test")
        assertEquals(true, result)
    }

    // endregion

    // region 音源再生

    /**
     * 音源再生
     *
     * 条件：なし
     * 結果：
     * ・音源URLが引数で渡したURLに切り替わること
     * ・再生ボタンの画像が停止ボタンに切り替わること
     */
    @Test
    fun startPlayback() {
        resultAdapterBodyState.startPlayback("test")
        val buttonResult = resultAdapterBodyState.playbackButtonImage.value
        val urlResult = resultAdapterBodyState.playBackUrl.value
        assertEquals(R.mipmap.ic_button_music_pause_32, buttonResult)
        assertEquals("test", urlResult)
    }

    // endregion

    // region 音源停止

    /**
     * 音源停止
     *
     * 条件：なし
     * 結果：
     * ・音源URLがBlankに切り替わること
     * ・再生ボタンの画像が再生ボタンに切り替わること
     */
    @Test
    fun stopPlayback() {
        resultAdapterBodyState.stopPlayback()
        val buttonResult = resultAdapterBodyState.playbackButtonImage.value
        val urlResult = resultAdapterBodyState.playBackUrl.value
        assertEquals(R.mipmap.ic_button_music_play_32, buttonResult)
        assertEquals("about:blank", urlResult)
    }

    // endregion

    // region 詳細画面表示・非表示

    /**
     * 詳細画面表示・非表示
     *
     * 条件：表示
     * 結果：
     * ・開閉ボタンの表示文言が▲になること
     * ・詳細画面の大枠が表示になること
     */
    @Test
    fun setIsDetailsProfileByTrue() {
        resultAdapterBodyState.setIsDetailsProfile(true)
        val buttonResult = resultAdapterBodyState.detailButtonText.value
        val layoutResult = resultAdapterBodyState.isDetailsProfile.value
        assertEquals("▲", buttonResult)
        assertEquals(true, layoutResult)
    }

    /**
     * 詳細画面表示・非表示
     *
     * 条件：非表示
     * 結果：
     * ・開閉ボタンの表示文言が▼になること
     * ・詳細画面の大枠が非表示になること
     */
    @Test
    fun setIsDetailsProfileByFalse() {
        resultAdapterBodyState.setIsDetailsProfile(false)
        val buttonResult = resultAdapterBodyState.detailButtonText.value
        val layoutResult = resultAdapterBodyState.isDetailsProfile.value
        assertEquals("▼", buttonResult)
        assertEquals(false, layoutResult)
    }

    // endregion

    // region 世代統計円グラフ表示・非表示

    /**
     * 世代統計円グラフ表示・非表示
     *
     * 条件：表示
     * 結果：世代統計の円グラフが表示になること
     */
    @Test
    fun setIsGenerationPieChartByTrue() {
        resultAdapterBodyState.setIsGenerationPieChart(true)
        val result = resultAdapterBodyState.isGenerationPieChart.value
        assertEquals(true, result)
    }

    /**
     * 世代統計円グラフ表示・非表示
     *
     * 条件：非表示
     * 結果：世代統計の円グラフが非表示になること
     */
    @Test
    fun setIsGenerationPieChartByFalse() {
        resultAdapterBodyState.setIsGenerationPieChart(false)
        val result = resultAdapterBodyState.isGenerationPieChart.value
        assertEquals(false, result)
    }

    // endregion

    // region 世代統計円グラフ表示・非表示

    /**
     * 性別統計円グラフ表示・非表示
     *
     * 条件：表示
     * 結果：性別統計の円グラフが表示になること
     */
    @Test
    fun setIsGenderPieChartByTrue() {
        resultAdapterBodyState.setIsGenderPieChart(true)
        val result = resultAdapterBodyState.isGenderPieChart.value
        assertEquals(true, result)
    }

    /**
     * 性別統計円グラフ表示・非表示
     *
     * 条件：非表示
     * 結果：性別統計の円グラフが非表示になること
     */
    @Test
    fun setIsGenderPieChartByFalse() {
        resultAdapterBodyState.setIsGenderPieChart(false)
        val result = resultAdapterBodyState.isGenderPieChart.value
        assertEquals(false, result)
    }

    // endregion

    // region ブックマーク画像設定

    /**
     * ブックマーク画像設定
     *
     * 条件；ブックマーク設定あり
     * 結果：ブックマーク済み画像が設定されること
     */
    @Test
    fun setBookmarkFlgByTrue() {
        resultAdapterBodyState.setBookmarkFlg(true)
        val result = resultAdapterBodyState.bookmarkButtonImage.value
        assertEquals(R.drawable.ic_star_yellow_32, result)
    }

    /**
     * ブックマーク画像設定
     *
     * 条件；ブックマーク設定なし
     * 結果：ブックマーク済み画像が設定されること
     */
    @Test
    fun setBookmarkFlgByFalse() {
        resultAdapterBodyState.setBookmarkFlg(false)
        val result = resultAdapterBodyState.bookmarkButtonImage.value
        assertEquals(R.drawable.ic_star_gray_32, result)
    }

    // endregion

    // region 性別名取得

    /**
     * 性別名取得
     *
     * 条件：男子
     * 結果：引数の性別に一致する性別名が返ること
     */
    @Test
    fun getGenderTextByMan() {
        val result = resultAdapterBodyState.getGenderText(Gender.MAN)
        assertEquals("男性", result)
    }

    /**
     * 性別名取得
     *
     * 条件：女性
     * 結果：引数の性別に一致する性別名が返ること
     */
    @Test
    fun getGenderTextByWoman() {
        val result = resultAdapterBodyState.getGenderText(Gender.WOMAN)
        assertEquals("女性", result)
    }

    // endregion

    // region 性別の色取得

    /**
     * 性別の色取得
     *
     * 条件：男性
     * 結果：青が返ること
     */
    @Test
    fun getGenderColorByMan() {
        val result = resultAdapterBodyState.getGenderColor(Gender.MAN)
        assertEquals(Color.BLUE, result)
    }

    /**
     * 性別の色取得
     *
     * 条件：女性
     * 結果：赤が返ること
     */
    @Test
    fun getGenderColorByWoman() {
        val result = resultAdapterBodyState.getGenderColor(Gender.WOMAN)
        assertEquals(Color.RED, result)
    }

    // endregion

    // region ジャンル１の文字列取得

    /**
     * ジャンル１の文字列取得
     *
     * 条件：なし
     * 結果：引数で渡したジャンルに一致する文字列が返ること
     */
    @Test
    fun getGenre1Text() {
        val result = resultAdapterBodyState.getGenre1Text(Genre1(1))
        assertEquals("ポップス", result)
    }

    // endregion

    // region ジャンル２の文字列取得

    /**
     * ジャンル２の文字列取得
     *
     * 条件：なし
     * 結果：引数で渡したジャンルに一致する文字列が返ること
     */
    @Test
    fun getGenre2Text() {
        val result = resultAdapterBodyState.getGenre2Text(Genre1(1), Genre2(2))
        assertEquals("アニソン", result)
    }

    // endregion
}
