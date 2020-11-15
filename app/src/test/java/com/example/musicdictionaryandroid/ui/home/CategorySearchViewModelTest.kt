package com.example.musicdictionaryandroid.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * カテゴリ検索画面
 *
 */
class CategorySearchViewModelTest {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    /**
     * バリデーションロジック
     *
     * 条件：各カテゴリのジャンルのどれか１つを選択していれば活性化する、１つも選択していな場合、非活性となる
     * 期待結果：バリデーション条件を満たさない場合false、満たす場合trueが帰る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidate() {
        // テストクラス作成
        val viewModel = CategorySearchViewModel()
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)

        // 実行
        // 初期状態
        viewModel.checkedChangeGender(0)
        viewModel.checkedChangeLength(0)
        viewModel.checkedChangeLyric(0)
        viewModel.checkedChangeVoice(0)
        assertEquals(viewModel.isEnableSubmitButton.value!!, false)
        // 性別選択
        for (i in 1..4) {
            viewModel.checkedChangeGender(i)
            viewModel.checkedChangeLength(0)
            viewModel.checkedChangeLyric(0)
            viewModel.checkedChangeVoice(0)
            assertEquals(viewModel.isEnableSubmitButton.value!!, true)
        }
        // 長さ選択
        for (i in 1..4) {
            viewModel.checkedChangeGender(0)
            viewModel.checkedChangeLength(i)
            viewModel.checkedChangeLyric(0)
            viewModel.checkedChangeVoice(0)
            assertEquals(viewModel.isEnableSubmitButton.value!!, true)
        }
        // 歌詞の言語選択
        for (i in 1..4) {
            viewModel.checkedChangeGender(0)
            viewModel.checkedChangeLength(0)
            viewModel.checkedChangeLyric(i)
            viewModel.checkedChangeVoice(0)
            assertEquals(viewModel.isEnableSubmitButton.value!!, true)
        }
        // 声の高さ選択
        for (i in 1..4) {
            viewModel.checkedChangeGender(0)
            viewModel.checkedChangeLength(0)
            viewModel.checkedChangeLyric(0)
            viewModel.checkedChangeVoice(i)
            assertEquals(viewModel.isEnableSubmitButton.value!!, true)
        }
    }
}
