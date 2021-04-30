package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.ui.util.UserInfoChangeListUtil
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * ユーザ情報画面
 */
class MyPageUserViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: MyPageUserViewModel
    private lateinit var userInfoChangeListUtil: UserInfoChangeListUtil
    private lateinit var userUseCase: UserUseCase

    // data
    private val user = User("test@com.jp", "testSuccess", 1, 1, "2000/2/2", 1)

    @Before
    fun setUp() {
        userUseCase = mockk<UserUseCase>().also {
            every { it.getUserByCache() } returns user
        }
        userInfoChangeListUtil = mockk<UserInfoChangeListUtil>().also {
            every { it.changeGender(user.gender) } returns "男"
            every { it.changeArea(user.area) } returns "東京"
        }
        viewModel = MyPageUserViewModel(userUseCase, userInfoChangeListUtil)
    }

    @After
    fun tearDown() {
    }

    // region 初期表示

    /**
     * 初期表示
     *
     * 条件：なし
     * 結果：それぞれのユーザ項目に取得したユーザ情報が格納されていること
     */
    @Test
    fun init() {
        val resultEmail = viewModel.emailText.value
        val resultName = viewModel.nameText.value
        val resultGender = viewModel.genderText.value
        val resultArea = viewModel.areaText.value
        val resultBirthday = viewModel.birthdayText.value
        val resultFavorite = viewModel.favoriteText.value

        assertEquals(user.email, resultEmail)
        assertEquals(user.name, resultName)
        assertEquals(userInfoChangeListUtil.changeGender(user.gender), resultGender)
        assertEquals(userInfoChangeListUtil.changeArea(user.area), resultArea)
        assertEquals(user.birthday, resultBirthday)
        assertEquals(user.artist_count.toString(), resultFavorite)
    }

    // endregion
}