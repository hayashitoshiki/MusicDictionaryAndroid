package com.example.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.presentation.BaseTestUnit
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * ホーム画面
 */
class HomeViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: HomeViewModel
    private lateinit var localUserRepository: com.example.domain.repository.LocalUserRepository

    @Before
    fun setUp() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 0
        }
        init()
    }

    // 初期化
    private fun init() {
        viewModel = HomeViewModel(localUserRepository)
        initObserver()
    }

    // Observer設定
    private fun initObserver() {
        viewModel.isEnableSearchBar.observeForever(observerBoolean)
        viewModel.isEnableCategoryButton.observeForever(observerBoolean)
        viewModel.isEnableDetailsButton.observeForever(observerBoolean)
        viewModel.isEnableSoaringButton.observeForever(observerBoolean)
        viewModel.isEnableRecommendButton.observeForever(observerBoolean)
        viewModel.isEnableSubmitButton.observeForever(observerBoolean)
    }

    // region 検索項目バリデート制御

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録しているアーティストが０件
     * 結果：検索バーが非活性になること
     */
    @Test
    fun checkValidateByArtist0() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 0
        }
        init()
        assert(!viewModel.isEnableSubmitButton.value!!)
        assert(!viewModel.isEnableSearchBar.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが１件以上
     * 結果：検索バーが活性状態になること
     */
    @Test
    fun checkValidateByArtist1() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 1
        }
        init()
        assert(!viewModel.isEnableSubmitButton.value!!)
        assert(viewModel.isEnableSearchBar.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが２件以下
     * 結果：カテゴリボタンが非活性状態になること
     */
    @Test
    fun checkValidateByArtist2() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 2
        }
        init()
        assert(!viewModel.isEnableCategoryButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが３件以上
     * 結果：カテゴリボタンが活性状態になること
     */
    @Test
    fun checkValidateByArtist3() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 3
        }
        init()
        assert(viewModel.isEnableCategoryButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが４件以下
     * 結果：詳細検索ボタンが非活性状態になること
     */
    @Test
    fun checkValidateByArtist4() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 4
        }
        init()
        assert(!viewModel.isEnableDetailsButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが５件以上
     * 結果：詳細検索ボタンが活性状態になること
     */
    @Test
    fun checkValidateByArtist5() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 5
        }
        init()
        assert(viewModel.isEnableDetailsButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが６件以下
     * 結果：急上昇ボタンが非活性状態になること
     */
    @Test
    fun checkValidateByArtist6() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 6
        }
        init()
        assert(!viewModel.isEnableSoaringButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが７件以上
     * 結果：急上昇ボタンが活性状態になること
     */
    @Test
    fun checkValidateByArtist7() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 7
        }
        init()
        assert(viewModel.isEnableSoaringButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが９件以下
     * 結果：おすすめボタンが非活性状態になること
     */
    @Test
    fun checkValidateByArtist9() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 9
        }
        init()
        assert(!viewModel.isEnableRecommendButton.value!!)
    }

    /**
     * アーティストの登録数によってバリデートが掛かるか
     *
     * 条件：登録済みアーティストが１０件以上
     * 結果：おすすめボタンが活性状態になること
     */
    @Test
    fun checkValidateByArtist10() {
        localUserRepository = mockk<com.example.domain.repository.LocalUserRepository>().also {
            every { it.getFavorite() } returns 10
        }
        init()
        assert(viewModel.isEnableRecommendButton.value!!)
    }
}
