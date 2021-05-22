package com.example.presentation.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.entity.User
import com.example.presentation.BaseTestUnit
import com.example.presentation.util.MessageUtil
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
    private lateinit var viewModel: com.example.presentation.mypage.MyPageUserViewModel
    private lateinit var messageUtil: MessageUtil
    private lateinit var userUseCase: com.example.domain.usecase.UserUseCase

    // data
    private val user = User("test@com.jp", "testSuccess", 1, 1, "2000/2/2", 1)

    @Before
    fun setUp() {
        userUseCase = mockk<com.example.domain.usecase.UserUseCase>().also {
            every { it.getUserByCache() } returns user
        }
        messageUtil = mockk<MessageUtil>().also {
            every { it.getGender(user.gender) } returns "男"
            every { it.getArea(user.area) } returns "東京"
        }
        viewModel = com.example.presentation.mypage.MyPageUserViewModel(userUseCase, messageUtil)
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
        assertEquals(messageUtil.getGender(user.gender), resultGender)
        assertEquals(messageUtil.getArea(user.area), resultArea)
        assertEquals(user.birthday, resultBirthday)
        assertEquals(user.artistCount.toString(), resultFavorite)
    }

    // endregion
}
