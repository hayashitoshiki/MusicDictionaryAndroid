package com.example.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.BaseTestUnit
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * カテゴリ検索画面
 */
class CategorySearchViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val viewModel = CategorySearchViewModel()

    @Before
    fun setUp() {
        viewModel.isEnableSubmitButton.observeForever(observerBoolean)
    }

    @After
    fun tearDown() {
    }

    // region 各種バリデーション

    /**
     * バリデーションロジック
     *
     * 条件：初期状態
     * 期待結果：非活性状態になること
     */
    @Test
    fun onButtonValidateByInit() {
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：性別のみ選択
     * 期待結果：活性状態になること
     */
    @Test
    fun onButtonValidateByGender() {
        for (i in 1..2) {
            viewModel.checkedChangeGender(i)
            viewModel.checkedChangeLength(0)
            viewModel.checkedChangeLyric(0)
            viewModel.checkedChangeVoice(0)
            val buttonEnable = viewModel.isEnableSubmitButton.value
            assertEquals(true, buttonEnable)
        }
    }

    /**
     * バリデーションロジック
     *
     * 条件：長さのみ選択
     * 期待結果：活性状態になること
     */
    @Test
    fun onButtonValidateByLength() {
        for (i in 1..4) {
            viewModel.checkedChangeGender(0)
            viewModel.checkedChangeLength(i)
            viewModel.checkedChangeLyric(0)
            viewModel.checkedChangeVoice(0)
            val buttonEnable = viewModel.isEnableSubmitButton.value
            assertEquals(true, buttonEnable)
        }
    }

    /**
     * バリデーションロジック
     *
     * 条件：歌詞の言語のみ選択
     * 期待結果：活性状態になること
     */
    @Test
    fun onButtonValidateByLyrics() {
        for (i in 1..4) {
            viewModel.checkedChangeGender(0)
            viewModel.checkedChangeLength(0)
            viewModel.checkedChangeLyric(i)
            viewModel.checkedChangeVoice(0)
            val buttonEnable = viewModel.isEnableSubmitButton.value
            assertEquals(true, buttonEnable)
        }
    }

    /**
     * バリデーションロジック
     *
     * 条件：声の高さのみ選択
     * 期待結果：活性状態になること
     */
    @Test
    fun onButtonValidateByVoice() {
        for (i in 1..4) {
            viewModel.checkedChangeGender(0)
            viewModel.checkedChangeLength(0)
            viewModel.checkedChangeLyric(0)
            viewModel.checkedChangeVoice(i)
            val buttonEnable = viewModel.isEnableSubmitButton.value
            assertEquals(true, buttonEnable)
        }
    }

    // endregion

    // region 選択解除

    /**
     * 選択解除ロジック
     *
     * 条件：前回選択した性別と同じボタンをタップ
     * 期待結果：選択解除されること
     */
    @Test
    fun onCancelByGender() {
        for (i in 1..2) {
            viewModel.checkedChangeGender(i)
            viewModel.checkedChangeGender(i)
            val checkId = viewModel.genderValueInt.value
            assertEquals(0, checkId)
        }
    }

    /**
     * バリデーションロジック
     *
     * 条件：前回選択した曲の長さと同じボタンをタップ
     * 期待結果：選択解除されること
     */
    @Test
    fun onCancelByLength() {
        for (i in 1..4) {
            viewModel.checkedChangeLength(i)
            viewModel.checkedChangeLength(i)
            val checkId = viewModel.lengthValueInt.value
            assertEquals(0, checkId)
        }
    }

    /**
     * バリデーションロジック
     *
     * 条件：前回選択した歌詞の言語と同じボタンをタップ
     * 期待結果：選択解除されること
     */
    @Test
    fun onCancelByLyrics() {
        for (i in 1..4) {
            viewModel.checkedChangeLyric(i)
            viewModel.checkedChangeLyric(i)
            val checkId = viewModel.lyricsValueInt.value
            assertEquals(0, checkId)
        }
    }

    /**
     * バリデーションロジック
     *
     * 条件：前回選択した声の高さと同じボタンをタップ
     * 期待結果：活性状態になること
     */
    @Test
    fun onCancelByVoice() {
        for (i in 1..4) {
            viewModel.checkedChangeVoice(i)
            viewModel.checkedChangeVoice(i)
            val checkId = viewModel.voiceValueInt.value
            assertEquals(0, checkId)
        }
    }

    // endregion
}
