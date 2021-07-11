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

/**
 * アーティスト検索結果ヘッダーの詳細仕様
 */
class ResultArtistHeaderStateTest {

    // / mock
    private lateinit var resultArtistHeaderState: ResultArtistHeaderState

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
        resultArtistHeaderState = ResultArtistHeaderState(messageUtil)
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
        val result = resultArtistHeaderState.isName(allNullArtistConditions.name)
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
        val result = resultArtistHeaderState.isName(notNullArtistConditions.name)
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
        val result = resultArtistHeaderState.isName(recommendNameArtistConditions.name)
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
        val result = resultArtistHeaderState.isName(soaringNameArtistConditions.name)
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
        val result = resultArtistHeaderState.isGender(allNullArtistConditions.gender)
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
        val result = resultArtistHeaderState.isGender(notNullArtistConditions.gender)
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
        val result = resultArtistHeaderState.isLyric(allNullArtistConditions.lyrics)
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
        val result = resultArtistHeaderState.isLyric(notNullArtistConditions.lyrics)
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
        val result = resultArtistHeaderState.isLength(allNullArtistConditions.length)
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
        val result = resultArtistHeaderState.isLength(notNullArtistConditions.length)
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
        val result = resultArtistHeaderState.isVoice(allNullArtistConditions.voice)
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
        val result = resultArtistHeaderState.isVoice(notNullArtistConditions.voice)
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
        val result = resultArtistHeaderState.isGenre1(allNullArtistConditions.genre1)
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
        val result = resultArtistHeaderState.isGenre1(notNullArtistConditions.genre1)
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
        val result = resultArtistHeaderState.isGenre2(allNullArtistConditions.genre2)
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
        val result = resultArtistHeaderState.isGenre2(notNullArtistConditions.genre2)
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
        val result = resultArtistHeaderState.isSubmitButton(soaringNameArtistConditions.name)
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
        val result = resultArtistHeaderState.isSubmitButton(recommendNameArtistConditions.name)
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
        val result = resultArtistHeaderState.isSubmitButton(emptyNameArtistConditions.name)
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
        val result = resultArtistHeaderState.isSubmitButton(allNullArtistConditions.name)
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
        val result = resultArtistHeaderState.isSubmitButton(notNullArtistConditions.name)
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
        val result = resultArtistHeaderState.getTitleText(recommendNameArtistConditions.name)
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
        val result = resultArtistHeaderState.getTitleText(soaringNameArtistConditions.name)
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
        val result = resultArtistHeaderState.getTitleText(allNullArtistConditions.name)
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
        val result = resultArtistHeaderState.getTitleText(emptyNameArtistConditions.name)
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
        val result = resultArtistHeaderState.getTitleText(notNullArtistConditions.name)
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
        val result = resultArtistHeaderState.getGenderText(Gender.MAN)
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
        val result = resultArtistHeaderState.getGenderText(Gender.WOMAN)
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
        val result = resultArtistHeaderState.getGenderText(null)
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
        val result = resultArtistHeaderState.getVoiceText(Voice(1))
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
        val result = resultArtistHeaderState.getVoiceText(Voice(2))
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
        val result = resultArtistHeaderState.getVoiceText(null)
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
        val result = resultArtistHeaderState.getLengthText(Length(1))
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
        val result = resultArtistHeaderState.getLengthText(Length(2))
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
        val result = resultArtistHeaderState.getLengthText(null)
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
        val result = resultArtistHeaderState.getLyricsText(Lyrics(1))
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
        val result = resultArtistHeaderState.getLyricsText(Lyrics(2))
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
        val result = resultArtistHeaderState.getLyricsText(null)
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
        val result = resultArtistHeaderState.getGenre1Text(Genre1(1))
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
        val result = resultArtistHeaderState.getGenre1Text(Genre1(2))
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
        val result = resultArtistHeaderState.getGenre1Text(null)
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
        val result = resultArtistHeaderState.getGenre2Text(Genre1(1), Genre2(2))
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
        val result = resultArtistHeaderState.getGenre2Text(Genre1(2), Genre2(1))
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
        val result = resultArtistHeaderState.getGenre2Text(null, null)
        assertEquals(null, result)
    }

    // endregion
}
