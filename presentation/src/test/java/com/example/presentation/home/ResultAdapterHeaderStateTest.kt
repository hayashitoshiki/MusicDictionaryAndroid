package com.example.presentation.home

import com.example.domain.model.value.*
import com.example.presentation.R
import com.example.presentation.util.MessageUtil
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResultAdapterHeaderStateTest {


    /// mock
    private lateinit var resultAdapterHeaderState: ResultAdapterHeaderState

    // data
    private val allNullArtistConditions = ArtistConditions(null, null, null, null, null, null, null)
    private val emptyNameArtistConditions = ArtistConditions("", null, null, null, null, null, null)
    private val recommendNameArtistConditions = ArtistConditions("おすすめ", null, null, null, null, null, null)
    private val soaringNameArtistConditions = ArtistConditions("急上昇", null, null, null, null, null, null)
    private val notNullArtistConditions =
        ArtistConditions("test", Gender.MAN, Voice(1), Length(1), Lyrics(1), Genre1(1), Genre2(1))

    @Before
    fun setUp() {
        val messageUtil = mockk<MessageUtil>().also {
            every { it.getString(any()) } returns ""
            every { it.getString(R.string.search_label_title) } returns "検索条件"
            every { it.getString(R.string.search_label_voice) } returns "高さ："
            every { it.getString(R.string.search_label_length) } returns "長さ："
            every { it.getString(R.string.search_label_lyrics) } returns "歌詞："
            every { it.getString(R.string.search_label_gender) } returns "性別："
            every { it.getString(R.string.search_label_genre1) } returns "ジャンル１："
            every { it.getString(R.string.search_label_genre2) } returns "ジャンル２："
            every { it.getGender(1) } returns "男性"
            every { it.getGender(2) } returns "女性"
            every { it.getVoice(1) } returns "低い"
            every { it.getVoice(2) } returns "やや低い"
            every { it.getLength(1) } returns "短い"
            every { it.getLength(2) } returns "やや短い"
            every { it.getLyrics(1) } returns "日本語"
            every { it.getLyrics(2) } returns "日本語多め"
            every { it.getGenre1(1) } returns "ポップス"
            every { it.getGenre1(2) } returns "ロック"
            every { it.getGenre2(1, 2) } returns "アニソン"
            every { it.getGenre2(2, 1) } returns "パンク"
        }
        resultAdapterHeaderState = ResultAdapterHeaderState(messageUtil)
    }

    @After
    fun tearDown() {
    }

    // region 検索キーワードの有無

    /**
     * 性別検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun isNameByNull() {
        val result = resultAdapterHeaderState.isName(allNullArtistConditions.name)
        assertEquals(false, result)
    }

    /**
     * 性別検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isNameByNotNull() {
        val result = resultAdapterHeaderState.isName(notNullArtistConditions.name)
        assertEquals(true, result)
    }

    /**
     * 性別検索の有無
     *
     * 条件：検索あり（おすすめ）
     * 結果：falseが返ること
     */
    @Test
    fun isNameByRecommend() {
        val result = resultAdapterHeaderState.isName(recommendNameArtistConditions.name)
        assertEquals(false, result)
    }

    /**
     * 性別検索の有無
     *
     * 条件：検索あり（急上昇）
     * 結果：falseが返ること
     */
    @Test
    fun isNameBySoaring() {
        val result = resultAdapterHeaderState.isName(soaringNameArtistConditions.name)
        assertEquals(false, result)
    }

    // endregion

    // region 性別検索の有無

    /**
     * 性別検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun isGenderByNull() {
        val result = resultAdapterHeaderState.isGender(allNullArtistConditions.gender)
        assertEquals(false, result)
    }

    /**
     * 性別検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isGenderByNotNull() {
        val result = resultAdapterHeaderState.isGender(notNullArtistConditions.gender)
        assertEquals(true, result)
    }

    // endregion

    // region 歌詞の割合の有無

    /**
     * 歌詞の割合検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun isLyricsByNull() {
        val result = resultAdapterHeaderState.isLyric(allNullArtistConditions.lyrics)
        assertEquals(false, result)
    }

    /**
     * 歌詞の割合検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isLyricByNotNull() {
        val result = resultAdapterHeaderState.isLyric(notNullArtistConditions.lyrics)
        assertEquals(true, result)
    }

    // endregion

    // region 曲の長さ検索の有無

    /**
     * 曲の長さ検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun isLengthByNull() {
        val result = resultAdapterHeaderState.isLength(allNullArtistConditions.length)
        assertEquals(false, result)
    }

    /**
     * 曲の長さ検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isLengthByNotNull() {
        val result = resultAdapterHeaderState.isLength(notNullArtistConditions.length)
        assertEquals(true, result)
    }

    // endregion

    // region 声の高さ検索の有無

    /**
     * 声の高さ検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun isVoiceByNull() {
        val result = resultAdapterHeaderState.isVoice(allNullArtistConditions.voice)
        assertEquals(false, result)
    }

    /**
     * 声の高さ検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isVoiceByNotNull() {
        val result = resultAdapterHeaderState.isVoice(notNullArtistConditions.voice)
        assertEquals(true, result)
    }

    // endregion

    // region ジャンル１検索の有無

    /**
     * ジャンル１検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun isGenre1ByNull() {
        val result = resultAdapterHeaderState.isGenre1(allNullArtistConditions.genre1)
        assertEquals(false, result)
    }

    /**
     * ジャンル１検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isGenre1ByNotNull() {
        val result = resultAdapterHeaderState.isGenre1(notNullArtistConditions.genre1)
        assertEquals(true, result)
    }

    // endregion

    // region ジャンル２検索の有無

    /**
     * ジャンル２検索の有無
     *
     * 条件：検索なし
     * 結果：falseが返ること
     */
    @Test
    fun iisGenre2ByNull() {
        val result = resultAdapterHeaderState.isGenre2(allNullArtistConditions.genre2)
        assertEquals(false, result)
    }

    /**
     * ジャンル２検索の有無
     *
     * 条件：検索あり
     * 結果：trueが返ること
     */
    @Test
    fun isGenre2ByNotNull() {
        val result = resultAdapterHeaderState.isGenre2(notNullArtistConditions.genre2)
        assertEquals(true, result)
    }

    // endregion

    // region 再検索ボタンの表示の有無

    /**
     * 再検索ボタンの表示の有無
     *
     * 条件：キーワード検索文字列が"急上昇"
     * 結果：非表示になること
     */
    @Test
    fun isSubmitButtonBySoaring() {
        val result = resultAdapterHeaderState.isSubmitButton(soaringNameArtistConditions.name)
        assertEquals(false, result)
    }

    /**
     * 再検索ボタンの表示の有無
     *
     * 条件：キーワード検索文字列が"おすすめ"
     * 結果：非表示になること
     */
    @Test
    fun isSubmitButtonByRecommend() {
        val result = resultAdapterHeaderState.isSubmitButton(recommendNameArtistConditions.name)
        assertEquals(false, result)
    }

    /**
     * 再検索ボタンの表示の有無
     *
     * 条件：検索のnameが空
     * 結果：表示になること
     */
    @Test
    fun isSubmitButtonByEmpty() {
        val result = resultAdapterHeaderState.isSubmitButton(emptyNameArtistConditions.name)
        assertEquals(true, result)
    }

    /**
     * 再検索ボタンの表示の有無
     *
     * 条件：検索のnameがnull
     * 結果：表示になること
     */
    @Test
    fun isSubmitButtonByNull() {
        val result = resultAdapterHeaderState.isSubmitButton(allNullArtistConditions.name)
        assertEquals(true, result)
    }

    /**
     * 再検索ボタンの表示の有無
     *
     * 条件：検索のnameが上記以外の任意の文字列
     * 結果：表示になること
     */
    @Test
    fun isSubmitButtonByOther() {
        val result = resultAdapterHeaderState.isSubmitButton(notNullArtistConditions.name)
        assertEquals(true, result)
    }

    // endregion

    // region タイトル文言取得

    /**
     * タイトル文言取得
     *
     * 条件：キーワード検索文字列がが"おすすめ"
     * 結果：タイトル文言が"おすすめ"になること
     */
    @Test
    fun getTitleTextByRecommend() {
        val result = resultAdapterHeaderState.getTitleText(recommendNameArtistConditions.name)
        assertEquals("おすすめ", result)
    }

    /**
     * タイトル文言取得
     *
     * 条件：キーワード検索文字列が"急上昇"
     * 結果：タイトル文言が"急上昇"になること
     */
    @Test
    fun getTitleTextBySoaring() {
        val result = resultAdapterHeaderState.getTitleText(soaringNameArtistConditions.name)
        assertEquals("急上昇", result)
    }

    /**
     * タイトル文言取得
     *
     * 条件：キーワード検索文字列が"null
     * 結果：タイトル文言が"検索条件"になること
     */
    @Test
    fun getTitleTextByNull() {
        val result = resultAdapterHeaderState.getTitleText(allNullArtistConditions.name)
        assertEquals("検索条件", result)
    }

    /**
     * タイトル文言取得
     *
     * 条件：キーワード検索文字列が空
     * 結果：タイトル文言が"検索条件"になること
     */
    @Test
    fun getTitleTextByEmpty() {
        val result = resultAdapterHeaderState.getTitleText(emptyNameArtistConditions.name)
        assertEquals("検索条件", result)
    }

    /**
     * タイトル文言取得
     *
     * 条件：キーワード検索文字列が上記以外の文字列
     * 結果：タイトル文言が"検索条件"になること
     */
    @Test
    fun getTitleTextByOther() {
        val result = resultAdapterHeaderState.getTitleText(notNullArtistConditions.name)
        assertEquals("検索条件", result)
    }

    // endregion

    // region 検索した性別（文字列）取得

    /**
     * 検索した性別（文字列）取得
     *
     * 条件：男性
     * 結果："性別：男性"が返ること
     */
    @Test
    fun getGenderTextByMan() {
        val result = resultAdapterHeaderState.getGenderText(Gender.MAN)
        assertEquals("性別：男性", result)
    }

    /**
     * 検索した性別（文字列）取得
     *
     * 条件：女性
     * 結果："性別：女性"が返ること
     */
    @Test
    fun getGenderTextByWoman() {
        val result = resultAdapterHeaderState.getGenderText(Gender.WOMAN)
        assertEquals("性別：女性", result)
    }

    /**
     * 検索した性別（文字列）取得
     *
     * 条件：null
     * 結果：nullが返ること
     */
    @Test
    fun getGenderTextByNull() {
        val result = resultAdapterHeaderState.getGenderText(null)
        assertEquals(null, result)
    }

    // endregion

    // region 検索した声の高さ（文字列）取得

    /**
     * 検索した声の高さ（文字列）取得
     *
     * 条件：1
     * 結果："高さ：低い"が返ること
     */
    @Test
    fun getVoiceTextBy1() {
        val result = resultAdapterHeaderState.getVoiceText(Voice(1))
        assertEquals("高さ：低い", result)
    }

    /**
     * 検索した声の高さ（文字列）取得
     *
     * 条件：2
     * 結果："高さ：やや低め"が返ること
     */
    @Test
    fun getVoiceTextBy2() {
        val result = resultAdapterHeaderState.getVoiceText(Voice(2))
        assertEquals("高さ：やや低い", result)
    }

    /**
     * 検索した性別（文字列）取得
     *
     * 条件：null
     * 結果：nullが返ること
     */
    @Test
    fun getVoiceTextByNull() {
        val result = resultAdapterHeaderState.getVoiceText(null)
        assertEquals(null, result)
    }

    // endregion

    // region 検索した曲の長さ（文字列）取得

    /**
     * 検索した曲の長さ（文字列）取得
     *
     * 条件：1
     * 結果："長さ：短い"が返ること
     */
    @Test
    fun getLengthTextBy1() {
        val result = resultAdapterHeaderState.getLengthText(Length(1))
        assertEquals("長さ：短い", result)
    }

    /**
     * 検索した曲の長さ（文字列）取得
     *
     * 条件：2
     * 結果："長さ：やや短め"が返ること
     */
    @Test
    fun getLengthTextBy2() {
        val result = resultAdapterHeaderState.getLengthText(Length(2))
        assertEquals("長さ：やや短い", result)
    }

    /**
     * 検索した曲の長さ（文字列）取得
     *
     * 条件：null
     * 結果：nullが返ること
     */
    @Test
    fun getLengthTextByNull() {
        val result = resultAdapterHeaderState.getLengthText(null)
        assertEquals(null, result)
    }

    // endregion

    // region 検索した歌詞の割合（文字列）取得

    /**
     * 検索した歌詞の割合（文字列）取得
     *
     * 条件：1
     * 結果："歌詞：日本語"が返ること
     */
    @Test
    fun getLyricsTextBy1() {
        val result = resultAdapterHeaderState.getLyricsText(Lyrics(1))
        assertEquals("歌詞：日本語", result)
    }

    /**
     * 検索した歌詞の割合（文字列）取得
     *
     * 条件：2
     * 結果："歌詞：日本語多め"が返ること
     */
    @Test
    fun getLyricsTextBy2() {
        val result = resultAdapterHeaderState.getLyricsText(Lyrics(2))
        assertEquals("歌詞：日本語多め", result)
    }

    /**
     * 検索した歌詞の割合（文字列）取得
     *
     * 条件：null
     * 結果：nullが返ること
     */
    @Test
    fun getLyricsTextByNull() {
        val result = resultAdapterHeaderState.getLyricsText(null)
        assertEquals(null, result)
    }

    // endregion

    // region 検索したジャンル１（文字列）取得

    /**
     * 検索したジャンル１（文字列）取得
     *
     * 条件：1
     * 結果："ジャンル１：ポップス"が返ること
     */
    @Test
    fun getGenre1TextBy1() {
        val result = resultAdapterHeaderState.getGenre1Text(Genre1(1))
        assertEquals("ジャンル１：ポップス", result)
    }

    /**
     * 検索したジャンル１（文字列）取得
     *
     * 条件：2
     * 結果："ジャンル１：ロック"が返ること
     */
    @Test
    fun getGenre1TextBy2() {
        val result = resultAdapterHeaderState.getGenre1Text(Genre1(2))
        assertEquals("ジャンル１：ロック", result)
    }

    /**
     * 検索したジャンル１（文字列）取得
     *
     * 条件：null
     * 結果：nullが返ること
     */
    @Test
    fun getGenre1TextByNull() {
        val result = resultAdapterHeaderState.getGenre1Text(null)
        assertEquals(null, result)
    }

    // endregion


    // region 検索したジャンル２（文字列）取得

    /**
     * 検索したジャンル２（文字列）取得
     *
     * 条件：1
     * 結果："ジャンル２：アニソン"が返ること
     */
    @Test
    fun getGenre2TextBy1() {
        val result = resultAdapterHeaderState.getGenre2Text(Genre1(1), Genre2(2))
        assertEquals("ジャンル２：アニソン", result)
    }

    /**
     * 検索したジャンル２（文字列）取得
     *
     * 条件：2
     * 結果："ジャンル２：パンク"が返ること
     */
    @Test
    fun getGenre2TextBy2() {
        val result = resultAdapterHeaderState.getGenre2Text(Genre1(2), Genre2(1))
        assertEquals("ジャンル２：パンク", result)
    }

    /**
     * 検索したジャンル２（文字列）取得
     *
     * 条件：null
     * 結果：nullが返ること
     */
    @Test
    fun getGenre2TextByNull() {
        val result = resultAdapterHeaderState.getGenre2Text(null, null)
        assertEquals(null, result)
    }

    // endregion
}
