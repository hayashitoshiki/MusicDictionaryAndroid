package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.musicdictionaryandroid.BaseTestUnit
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.domain.model.value.*
import com.example.musicdictionaryandroid.domain.model.value.Result
import com.example.musicdictionaryandroid.domain.usecase.ArtistUseCase
import com.example.musicdictionaryandroid.ui.util.MessageUtil
import com.example.musicdictionaryandroid.ui.util.Status
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.rules.TestRule

/**
 * アーティスト登録・更新画面
 */
class MyPageArtistAddViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // mock
    private lateinit var viewModel: MyPageArtistAddViewModel
    private lateinit var artistUseCase: ArtistUseCase
    private lateinit var messageUtil: MessageUtil

    // data
    private val addTitle = "アーティスト登録"
    private val changeTitle = "アーティスト編集"
    private val addButton = "追加"
    private val changeButton = "変更"
    private val mainList = listOf("0")
    private val list0 = listOf("未選択")
    private val list1 = listOf("1")
    private val list2 = listOf("2")
    private val list3 = listOf("3")
    private val list4 = listOf("4")
    private val list5 = listOf("5")
    private val list6 = listOf("6")
    private val artist = Artist("test", Gender.MAN, Voice(1), Length(2), Lyrics(3), Genre1(4), Genre2(5))

    // coroutines用
    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)

        // テストクラス作成
        artistUseCase = mockk<ArtistUseCase>().also {
            coEvery { it.addArtist(any()) } returns Result.Success(artist)
            coEvery { it.updateArtist(any()) } returns Result.Success(artist)
        }
        messageUtil = mockk<MessageUtil>().also {
            coEvery { it.getMainCategory() } returns mainList
            coEvery { it.getSubCategory(0) } returns list0
            coEvery { it.getSubCategory(1) } returns list1
            coEvery { it.getSubCategory(2) } returns list2
            coEvery { it.getSubCategory(3) } returns list3
            coEvery { it.getSubCategory(4) } returns list4
            coEvery { it.getSubCategory(5) } returns list5
            coEvery { it.getSubCategory(6) } returns list6
            coEvery { it.getArtistAddTitle() } returns addTitle
            coEvery { it.getArtistChangeTitle() } returns changeTitle
            coEvery { it.getAddButton() } returns addButton
            coEvery { it.getChangeButton() } returns changeButton
        }
        initNew()
        initObserver()
    }

    private fun initNew() {
        viewModel = MyPageArtistAddViewModel(null, artistUseCase, messageUtil)
    }

    private fun initUpdate() {
        viewModel = MyPageArtistAddViewModel(artist, artistUseCase, messageUtil)
    }

    private fun initObserver() {
        viewModel.isEnableSubmitButton.observeForever(observerBoolean)
        viewModel.genre2List.observeForever(observerStringList)
        viewModel.isProgressBar.observeForever(observerBoolean)
        viewModel.nameText.observeForever(observerString)
        viewModel.gender.observeForever(observerInt)
        viewModel.voice.observeForever(observerInt)
        viewModel.length.observeForever(observerInt)
        viewModel.lyrics.observeForever(observerInt)
        viewModel.genre1.observeForever(observerInt)
        viewModel.genre2.observeForever(observerInt)
        viewModel.titleText.observeForever(observerString)
        viewModel.submitText.observeForever(observerString)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    // region 初期表示

    /**
     * 初期表示
     *
     * 条件：新規登録
     * 結果：アーティスト情報が表示状態でかつ新規登録の文言が設定されること
     */
    @Test
    fun initByNew() {
        initNew()
        val name = viewModel.nameText.value
        val gender = viewModel.gender.value
        val voice = viewModel.voice.value
        val length = viewModel.length.value
        val lyrics = viewModel.lyrics.value
        val genre1 = viewModel.genre1.value
        val genre2 = viewModel.genre2.value
        val title = viewModel.titleText.value
        val buttonName = viewModel.submitText.value
        assertEquals("", name)
        assertEquals(0, gender)
        assertEquals(0, voice)
        assertEquals(0, length)
        assertEquals(0, lyrics)
        assertEquals(0, genre1)
        assertEquals(0, genre2)
        assertEquals(addTitle, title)
        assertEquals(addButton, buttonName)
    }

    /**
     * 初期表示
     *
     * 条件：更新
     * 結果：アーティスト情報が渡されたでかつ更新の文言が設定されること
     */
    @Test
    fun initByUpdate() {
        initUpdate()
        val name = viewModel.nameText.value
        val gender = viewModel.gender.value
        val voice = viewModel.voice.value
        val length = viewModel.length.value
        val lyrics = viewModel.lyrics.value
        val genre1 = viewModel.genre1.value
        val genre2 = viewModel.genre2.value
        val title = viewModel.titleText.value
        val buttonName = viewModel.submitText.value
        assertEquals(artist.name, name)
        assertEquals(artist.gender.value, gender)
        assertEquals(artist.voice.value, voice)
        assertEquals(artist.length.value, length)
        assertEquals(artist.lyrics.value, lyrics)
        assertEquals(artist.genre1.value, genre1)
        assertEquals(artist.genre2.value, genre2)
        assertEquals(changeTitle, title)
        assertEquals(changeButton, buttonName)
    }

    // endregion

    // region プログレスバーの表示制御

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除未実施
     * 結果：非表示
     */
    @Test
    fun isProgressBarByStatusNon() {
        val status = Status.Non
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        Assert.assertEquals(false, result)
    }

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除実施中
     * 結果：表示
     */
    @Test
    fun isProgressBarByStatusLoading() {
        val status = Status.Loading
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        Assert.assertEquals(true, result)
    }

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除成功
     * 結果：非表示
     */
    @Test
    fun isProgressBarByStatusSuccess() {
        val status = Status.Success(artist)
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        Assert.assertEquals(false, result)
    }

    /**
     * プログレスバーの表示制御
     *
     * 条件：アーティスト削除失敗
     * 結果：非表示
     */
    @Test
    fun isProgressBarByStatusError() {
        val status = Status.Failure(IllegalArgumentException(""))
        (viewModel.status as MutableLiveData).value = status
        val result = viewModel.isProgressBar.value
        Assert.assertEquals(false, result)
    }
    // endregion


    // region 送信ボタンバリデート

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：アーティスト新規登録時の初期状態
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByInitNew() {
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：アーティスト更新時の初期状態
     * 期待結果：送信ボタンが活性状態であること
     */
    @Test
    fun onSubmitValidateByInitUpdate() {
        initUpdate()
        initObserver()
        val result = viewModel.isEnableSubmitButton.value
        assertEquals(true, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：アーティスト名のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @Test
    fun onSubmitValidateByNameNull() {
        viewModel.nameText.value = ""
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：性別のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByGenderNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 0
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：長さのみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByLengthNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 0
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：声の高さのみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByVoiceNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 0
        viewModel.lyrics.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：歌詞情報のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @Test
    fun onSubmitValidateByLyricsNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 0
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：ジャンル１のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByGenre1Null() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 0
        viewModel.genre2.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：ジャンル２のみ未入力
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateByGenre2rNull() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 0
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(false, result)
    }

    /**
     * 送信ボタンバリデートロジック
     *
     * 条件：全て入力済みであること
     * 期待結果：送信ボタンが非活性状態であること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitValidateBySuccess() {
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 1
        val result = viewModel.isEnableSubmitButton.value!!
        assertEquals(true, result)
    }

    // endregion

    // region 送信ボタン

    /**
     *　送信ロジック
     *
     * 条件：初期表示時にアーティスト情報なし
     * 結果：アーティスト業務ロジッククラスの新規登録メソッドが呼ばれること
     */
    @Test
    fun submitByNew() {
        initNew()
        viewModel.nameText.value = "test"
        viewModel.gender.value = 1
        viewModel.length.value = 1
        viewModel.voice.value = 1
        viewModel.lyrics.value = 1
        viewModel.genre1.value = 1
        viewModel.genre2.value = 1
        viewModel.submit()
        coVerify(exactly = 1) { (artistUseCase).addArtist(any()) }
        coVerify(exactly = 0) { (artistUseCase).updateArtist(any()) }
    }

    /**
     *　送信ロジック
     *
     * 条件：初期表示時にアーティスト情報あり
     * 結果：アーティスト業務ロジッククラスの更新メソッドが呼ばれること
     */
    @Test
    fun submitByUpdate() {
        initUpdate()
        viewModel.submit()
        coVerify(exactly = 0) { (artistUseCase).addArtist(any()) }
        coVerify(exactly = 1) { (artistUseCase).updateArtist(any()) }
    }

    // endregion

    // region 性別変更

    /**
     * 性別変更
     *
     * 条件：なし
     * 結果：性別のパラメータが変更されるこt
     */
    @Test
    fun checkedChangeGender() {
        val value = 1
        viewModel.checkedChangeGender(value)
        val result = viewModel.gender.value
        assertEquals(value, result)
    }

    // endregion

    // region 長さ変更

    /**
     * 長さ変更
     *
     * 条件：なし
     * 結果：長さのパラメータが変更されるこt
     */
    @Test
    fun checkedChangeLength() {
        val value = 1
        viewModel.checkedChangeLength(value)
        val result = viewModel.length.value
        assertEquals(value, result)
    }

    // endregion

    // region 高さ変更

    /**
     * 高さ変更
     *
     * 条件：なし
     * 結果：長さのパラメータが変更されるこt
     */
    @Test
    fun checkedChangeVoice() {
        val value = 1
        viewModel.checkedChangeVoice(value)
        val result = viewModel.voice.value
        assertEquals(value, result)
    }

    // endregion

    // region 歌詞情報変更

    /**
     * 歌詞の言語変更
     *
     * 条件：なし
     * 結果：歌詞の言語のパラメータが変更されるこt
     */
    @Test
    fun checkedChangeLyric() {
        val value = 1
        viewModel.checkedChangeLyric(value)
        val result = viewModel.lyrics.value
        assertEquals(value, result)
    }

    // endregion

    // region ジャンル１変更

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を空のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By0() {
        initNew()
        val value = 0
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(listOf("未選択"), result)
    }

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を１つ目のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By1() {
        initNew()
        val value = 1
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(list1, result)
    }

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を２つ目のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By2() {
        initNew()
        val value = 2
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(list2, result)
    }

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を３つ目のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By3() {
        initNew()
        val value = 3
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(list3, result)
    }

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を４つ目のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By4() {
        initNew()
        val value = 4
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(list4, result)
    }

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を５つ目のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By5() {
        initNew()
        val value = 5
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(list5, result)
    }

    /**
     * ジャンル１の変更
     *
     * 条件：ジャンル１を６つ目のジャンルへ変更
     * 結果：１つ目のジャンルに紐づくジャンル２が設定されること
     */
    @Test
    fun changeGenre1By6() {
        initNew()
        val value = 6
        viewModel.changeGenre1(value)
        val result = viewModel.genre2List.value
        assertEquals(list6, result)
    }

    // endregion
}
