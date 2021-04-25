package com.example.musicdictionaryandroid.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.data.repository.PreferenceRepository
import com.nhaarman.mockito_kotlin.mock
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * ホーム画面
 */
class HomeViewModelTest {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件　　：全パターンのバリデートを実施
     * 期待結果：５つ全てのパターンでバリデートがかかることを確認
     */
    @Test
    fun checkValidate() {
        // モック作成
        val count0 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 0
        }
        val count1 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 1
        }
        val count2 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 2
        }
        val count3 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 3
        }
        val count4 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 4
        }
        val count5 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 5
        }
        val count6 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 6
        }
        val count7 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 7
        }
        val count9 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 9
        }
        val count10 = mockk<PreferenceRepository>().also {
            every { it.getFavorite() } returns 10
        }
        var viewModel = HomeViewModel(count0)
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)

        // テスト実施
        // 検索バー非活性
        viewModel = HomeViewModel(count0)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(!viewModel.isEnableSubmitButton.value!!)
        assert(!viewModel.isEnableSearchBar.value!!)
        // 検索バー活性
        viewModel = HomeViewModel(count1)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(!viewModel.isEnableSubmitButton.value!!)
        assert(viewModel.isEnableSearchBar.value!!)
        // カテゴリボタン非活性
        viewModel = HomeViewModel(count2)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(!viewModel.isEnableCategoryButton.value!!)
        // カテゴリボタン活性
        viewModel = HomeViewModel(count3)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(viewModel.isEnableCategoryButton.value!!)
        // 詳細検索ボタン非活性
        viewModel = HomeViewModel(count4)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(!viewModel.isEnableDetailsButton.value!!)
        // 詳細検索ボタン活性
        viewModel = HomeViewModel(count5)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(viewModel.isEnableDetailsButton.value!!)
        // 急上昇ボタン非活性
        viewModel = HomeViewModel(count6)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(!viewModel.isEnableSoaringButton.value!!)
        // 急上昇ボタン活性
        viewModel = HomeViewModel(count7)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(viewModel.isEnableSoaringButton.value!!)
        // おすすめボタン非活性
        viewModel = HomeViewModel(count9)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(!viewModel.isEnableRecommendButton.value!!)
        // おすすめボタン活性
        viewModel = HomeViewModel(count10)
        viewModel.isEnableSearchBar.observeForever(observer)
        viewModel.isEnableCategoryButton.observeForever(observer)
        viewModel.isEnableDetailsButton.observeForever(observer)
        viewModel.isEnableSoaringButton.observeForever(observer)
        viewModel.isEnableRecommendButton.observeForever(observer)
        viewModel.isEnableSubmitButton.observeForever(observer)
        assert(viewModel.isEnableRecommendButton.value!!)
    }
}
