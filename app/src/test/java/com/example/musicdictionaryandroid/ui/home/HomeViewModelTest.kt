package com.example.musicdictionaryandroid.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * ホーム画面
 */

class HomeViewModelTest{

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    /**
     * アーティストの登録数によってバリデートが掛かるか
     * 条件　　：全パターンのバリデートを実施
     * 期待結果：５つ全てのパターンでバリデートがかかることを確認
     */
    @Test
    fun checkValidate() {
        // モック作成
        val count10 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 10
        }
        val count9 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 9
        }
        val count7 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 7
        }
        val count6 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 6
        }
        val count5 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 5
        }
        val count4 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 4
        }
        val count3 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 3
        }
        val count2 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 2
        }
        val count1 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 1
        }
        val count0 = mockk<PreferenceRepositoryImp>().also {
            every { it.getFavorite() } returns 0
        }
        val viewModel11 = HomeViewModel(count0)
        val viewModel12 = HomeViewModel(count1)
        val viewModel21 = HomeViewModel(count2)
        val viewModel22 = HomeViewModel(count3)
        val viewModel31 = HomeViewModel(count4)
        val viewModel32 = HomeViewModel(count5)
        val viewModel41 = HomeViewModel(count6)
        val viewModel42 = HomeViewModel(count7)
        val viewModel51 = HomeViewModel(count9)
        val viewModel52 = HomeViewModel(count10)

        // テスト実施
        assert(!viewModel11.isSubmitButton.value!!)
        assert(!viewModel11.isSearchBar.value!!)
        assert(viewModel11.isEnableSubmitButton.value!!)
        assert(viewModel12.isSubmitButton.value!!)
        assert(viewModel12.isSearchBar.value!!)
        assert(!viewModel12.isEnableSubmitButton.value!!)

        assert(!viewModel21.isCategoryButton.value!!)
        assert(viewModel21.isEnableCategoryButton.value!!)
        assert(viewModel22.isCategoryButton.value!!)
        assert(!viewModel22.isEnableCategoryButton.value!!)

        assert(!viewModel31.isDetailsButton.value!!)
        assert(viewModel31.isEnableDetailsButton.value!!)
        assert(viewModel32.isDetailsButton.value!!)
        assert(!viewModel32.isEnableDetailsButton.value!!)

        assert(!viewModel41.isSoaringButton.value!!)
        assert(viewModel41.isEnableSoaringButton.value!!)
        assert(viewModel42.isSoaringButton.value!!)
        assert(!viewModel42.isEnableSoaringButton.value!!)

        assert(!viewModel51.isRecommendButton.value!!)
        assert(viewModel51.isEnableRecommendButton.value!!)
        assert(viewModel52.isRecommendButton.value!!)
        assert(!viewModel52.isEnableRecommendButton.value!!)
    }
}