package com.example.musicdictionaryandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.domain.model.entity.User
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.UserUseCase
import com.example.musicdictionaryandroid.ui.util.Status
import com.example.musicdictionaryandroid.ui.util.UserInfoChangeListUtil
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * 新規登録画面
 */
class SignUpViewModelTest : BaseTestUnit() {

    // mock
    private lateinit var viewModel: SignUpViewModel
    private lateinit var userInfoChangeListUtil: UserInfoChangeListUtil

    // data
    private val user = User("test@com.jp", "testSuccess", 1, 1, "2000/2/2", 1)
    private val successEmail = "successEmail"
    private val successResult = Result.Success("Success")
    private val successStatus = Status.Success("Success")
    private val failureEmail = "failureEmail"
    private val failure = IllegalArgumentException("")
    private val failureResult = Result.Error(failure)
    private val failureStatus = Status.Failure(failure)

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        val userUseCase = mockk<UserUseCase>().also() {
            coEvery { it.createUser(successEmail, any(), any()) } returns flow { emit(successResult) }
            coEvery { it.createUser(failureEmail, any(), any()) } returns flow { emit(failureResult) }
        }
        userInfoChangeListUtil = mockk<UserInfoChangeListUtil>().also {
            every { it.getBirthday(any()) } returns "2000/02/02"
            every { it.changeGender(user.gender) } returns "男"
            every { it.changeArea(user.area) } returns "東京"
        }
        viewModel = SignUpViewModel(userInfoChangeListUtil, userUseCase, testScope)
        viewModel.isEnableSubmitButton.observeForever(observerBoolean)
        viewModel.passwordError1Text.observeForever(observerStringNullable)
        viewModel.passwordError2Text.observeForever(observerStringNullable)
        viewModel.emailErrorText.observeForever(observerStringNullable)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    // region メールアドレス入力欄のエラー文言制御

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件；初期表示
     * 結果： エラー文言なし
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailByInit() {
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertEquals(null, emailError)
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件；５文字
     * 結果： エラー文言あり
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailBy5() {
        viewModel.emailText.value = "12345"
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertNotEquals(null, emailError)
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件；６文字 && メールアドレス形式ではない
     * 結果： エラー文言あり
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailBy6AndNotEmail() {
        viewModel.emailText.value = "123456"
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertNotEquals(null, emailError)
    }

    /**
     * メールアドレス入力欄のエラー文言制御
     *
     * 条件；６文字以上 && メールアドレス形式
     * 結果： エラー文言なし
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeEmailBy6AndEmail() {
        viewModel.emailText.value = "abc@aaa.ne.jp"
        viewModel.focusChangeEmail()
        val emailError = viewModel.emailErrorText.value
        assertEquals(null, emailError)
    }

    // endregion

    // region パスワード入力欄のエラー文言制御

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：初期表示
     * 結果：エラー文言なし
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByInit() {
        viewModel.focusChangePassword1()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError1Text.value
        assertEquals(null, passwordError1)
        assertEquals(null, passwordError2)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：１つ目のみ入力 && ５文字
     * 結果：１つ目のパスワード入力欄のみエラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByFirstAnd5() {
        viewModel.password1Text.value = "12345"
        viewModel.focusChangePassword1()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError2Text.value
        assertNotEquals(null, passwordError1)
        assertEquals(null, passwordError2)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：１つ目のみ入力 && ６文字
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByFirstAnd6() {
        viewModel.password1Text.value = "123456"
        viewModel.focusChangePassword1()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError2Text.value
        assertEquals(null, passwordError1)
        assertEquals(null, passwordError2)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：２つ目のみ入力 && ５文字
     * 結果：２つ目のパスワード入力欄のみエラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordBySecondAnd5() {
        viewModel.password2Text.value = "12345"
        viewModel.focusChangePassword2()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError2Text.value
        assertEquals(null, passwordError1)
        assertNotEquals(null, passwordError2)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：２つ目のみ入力 && ６文字
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordBySecondAnd6() {
        viewModel.password2Text.value = "123456"
        viewModel.focusChangePassword2()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError2Text.value
        assertEquals(null, passwordError1)
        assertEquals(null, passwordError2)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：両方入力済み && 不一致
     * 結果：２つ目のパスワード入力欄のみエラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByNotEqual() {
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "654321"
        viewModel.focusChangePassword1()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError2Text.value
        assertEquals(null, passwordError1)
        assertNotEquals(null, passwordError2)
    }

    /**
     * パスワード入力欄のエラー文言制御
     *
     * 条件：両方入力済み && 一致
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangePasswordByEqual() {
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.focusChangePassword1()
        val passwordError1 = viewModel.passwordError1Text.value
        val passwordError2 = viewModel.passwordError2Text.value
        assertEquals(null, passwordError1)
        assertEquals(null, passwordError2)
    }

    // endregion

    // region 名前入力欄のエラー文言制御

    /**
     * 名前入力欄のエラー文言制御
     *
     * 条件：初期状態
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeNameByInit() {
        viewModel.focusChangeName()
        val nameError = viewModel.nameErrorText.value
        assertEquals(null, nameError)
    }

    /**
     * 名前入力欄のエラー文言制御
     *
     * 条件：入力済み && ５文字
     * 結果：エラー文言が表示されること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeNameBy5() {
        viewModel.nameText.value = "12345"
        viewModel.focusChangeName()
        val nameError = viewModel.nameErrorText.value
        assertNotEquals(null, nameError)
    }

    /**
     * 名前入力欄のエラー文言制御
     *
     * 条件：入力済み && ６文字
     * 結果：エラー文言が表示されないこと
     */
    @ExperimentalCoroutinesApi
    @Test
    fun focusChangeNameBy6() {
        viewModel.nameText.value = "123456"
        viewModel.focusChangeName()
        val nameError = viewModel.nameErrorText.value
        assertEquals(null, nameError)
    }

    // endregion

    // region 送信ボタンバリデーション制御

    /**
     * バリデーションロジック
     *
     * 条件：初期表示
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByInit() {
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：メールアドレスが５文字
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByEmail5() {
        viewModel.emailText.value = "12345"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：パスワードが５文字
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByPassword5() {
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "12345"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：名前が５文字
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByName5() {
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "12345"
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：性別が未入力
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByNotGender() {
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = 0
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：地域が未入力
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByNotArea() {
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = 0
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：生年月日が未入力
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByNotBirthday() {
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 0
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：全て入力済み && メールアドレスが不正
     * 期待結果：非活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationByEmailFailure() {
        viewModel.emailText.value = "123456"
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.nameText.value = "123456"
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, buttonEnable)
    }

    /**
     * バリデーションロジック
     *
     * 条件：全て入力済み
     * 期待結果：活性状態となること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun signUpButtonValidationBySuccess() {
        viewModel.emailText.value = user.email
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.nameText.value = user.name
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        val buttonEnable = viewModel.isEnableSubmitButton.value!!
        assertEquals(true, buttonEnable)
    }

    // endregion


    // region 新規作成処理

    /**
     * 新規登録処理
     *
     * 条件：新規登録成功
     * 結果：新規登録のステータスが成功になること
     */
    @Test
    fun signUpBySuccess() {
        viewModel.emailText.value = successEmail
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.nameText.value = user.name
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.signUp()
        val result = viewModel.status.value
        assertEquals(successStatus, result)
    }

    /**
     * 新規登録処理
     *
     * 条件：新規登録失敗
     * 結果：新規登録のステータスが失敗になること
     */
    @Test
    fun signUpByError() {
        viewModel.emailText.value = failureEmail
        viewModel.password1Text.value = "123456"
        viewModel.password2Text.value = "123456"
        viewModel.nameText.value = user.name
        viewModel.genderInt.value = user.gender
        viewModel.areaSelectedPosition.value = user.area
        viewModel.birthdaySelectedPosition.value = 1
        viewModel.signUp()
        val result = viewModel.status.value
        assertEquals(failureStatus, result)
    }

    // endregion
}
