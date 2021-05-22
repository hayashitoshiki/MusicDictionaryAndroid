package com.example.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.BaseTestUnit
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * 詳細検索画面
 */
class DetailsSearchViewModelTest : BaseTestUnit() {

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // テストデータ
    companion object {
        const val N0CATEGORY = "null"
        const val CATEGORY1 = "category1"
        const val CATEGORY2 = "category2"
        const val CATEGORY3 = "category3"
        const val CATEGORY4 = "category4"
    }

    private val genreKeyList: Array<String> = arrayOf(N0CATEGORY, CATEGORY1, CATEGORY2, CATEGORY3, CATEGORY4)
    private val genre1List: Array<String> = arrayOf("value1", "value2", "value3", "value4")
    private val genre2List: Array<String> = arrayOf("value1", "value2", "value3", "value4")
    private val genre3List: Array<String> = arrayOf("value1", "value2", "value3", "value4")
    private val genre4List: Array<String> = arrayOf("value1", "value2", "value3", "value4")

    // mock
    private lateinit var viewModel: DetailsSearchViewModel

    @Before
    fun before() {
        viewModel = DetailsSearchViewModel()
        viewModel.init(genreKeyList, genre1List, genre2List, genre3List, genre4List)
        viewModel.isEnableSubmitButton.observeForever(observerBoolean)
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

    // region カテゴリ追加ボタン表示制御

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     *
     * 条件：１つ目の検索カテゴリの属性を設定
     * 結果：１つ目のカテゴリ追加ボタンが表示されること
     */
    @Test
    fun changeGenreValueByFirstOn() {
        viewModel.genre2KeyList.value = genreKeyList
        viewModel.changeGenreValue(1, 1)
        assert(viewModel.isGenreAddButton1.value!!)
    }

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     *
     * 条件：１つ目の検索カテゴリの属性を選択解除
     * 結果：１つ目のカテゴリ追加ボタンが表示されないこと
     */
    @Test
    fun changeGenreValueByFirstOff() {
        viewModel.genre2KeyList.value = genreKeyList
        viewModel.changeGenreValue(1, 0)
        assert(!viewModel.isGenreAddButton1.value!!)
    }

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     *
     * 条件：２つ目の検索カテゴリの属性を選択解除
     * 結果：２つ目のカテゴリ追加ボタンが表示されること
     */
    @Test
    fun changeGenreValueBySecondOn() {
        viewModel.genre3KeyList.value = genreKeyList
        viewModel.changeGenreValue(2, 1)
        assert(viewModel.isGenreAddButton2.value!!)
    }

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     *
     * 条件：２つ目の検索カテゴリの属性を選択解除
     * 結果：２つ目のカテゴリ追加ボタンが表示されないこと
     */
    @Test
    fun changeGenreValueBySecondOff() {
        viewModel.genre3KeyList.value = genreKeyList
        viewModel.changeGenreValue(2, 0)
        assert(!viewModel.isGenreAddButton2.value!!)
    }

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     *
     * 条件：３つ目の検索カテゴリの属性を選択解除
     * 結果：３つ目のカテゴリ追加ボタンが表示されること
     */
    @Test
    fun changeGenreValueByThirdOn() {
        viewModel.genre4KeyList.value = genreKeyList
        viewModel.changeGenreValue(3, 1)
        assert(viewModel.isGenreAddButton3.value!!)
    }

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     *
     * 条件：３つ目の検索カテゴリの属性を選択解除
     * 結果：３つ目のカテゴリ追加ボタンが表示されないこと
     */
    @Test
    fun changeGenreValueByThirdOff() {
        viewModel.genre4KeyList.value = genreKeyList
        viewModel.changeGenreValue(3, 0)
        assert(!viewModel.isGenreAddButton3.value!!)
    }

    // endregion

    // region 検索カテゴリ追加

    /**
     * 検索カテゴリ追加
     *
     * 条件：１つ目のカテゴリ追加ボタンタップ
     * 期待：２つ目のカテゴリリストのサイスが１つ目のカテゴリサイズより１つ小さく設定されていること
     */
    @Test
    fun addSortListByFirst() {
        viewModel.changeGenreKey(1, 4)
        viewModel.changeGenreValue(1, 1)
        viewModel.addSortList(1)
        val expected = viewModel.genre1KeyList.value!!.size - 1
        val genre2ListSize = viewModel.genre2KeyList.value!!.size
        assertEquals(expected, genre2ListSize)
    }

    /**
     * 検索カテゴリ追加
     *
     * 条件：２つ目のカテゴリ追加ボタンタップ
     * 期待：３つ目のカテゴリリストのサイスが２つ目のカテゴリサイズより１つ小さく設定されていること
     */
    @Test
    fun addSortListBySecond() {
        viewModel.genre2KeyList.value = genreKeyList
        viewModel.changeGenreKey(1, 4)
        viewModel.changeGenreValue(1, 1)
        viewModel.addSortList(1)
        viewModel.changeGenreKey(2, 3)
        viewModel.changeGenreValue(2, 1)
        viewModel.addSortList(2)
        val expected = viewModel.genre2KeyList.value!!.size - 1
        val genre3ListSize = viewModel.genre3KeyList.value!!.size
        assertEquals(expected, genre3ListSize)
    }

    /**
     * 検索カテゴリ追加
     *
     * 条件：３つ目のカテゴリ追加ボタンタップ
     * 期待：４つ目のカテゴリリストのサイスが３つ目のカテゴリサイズより１つ小さく設定されていること
     */
    @Test
    fun addSortListByThird() {
        viewModel.genre2KeyList.value = genreKeyList
        viewModel.changeGenreKey(1, 4)
        viewModel.changeGenreValue(1, 1)
        viewModel.addSortList(1)
        viewModel.changeGenreKey(2, 3)
        viewModel.changeGenreValue(2, 1)
        viewModel.addSortList(2)
        viewModel.changeGenreKey(3, 2)
        viewModel.changeGenreValue(3, 1)
        viewModel.addSortList(3)
        val expected = viewModel.genre3KeyList.value!!.size - 1
        val genre4ListSize = viewModel.genre4KeyList.value!!.size
        assertEquals(expected, genre4ListSize)
    }

    // endregion

    // region 検索カテゴリ設定
    /**
     * カテゴリにあった属性が設定されているか
     * 条件　　：全パターンでカテゴリ設定を実施
     * 期待結果：全てのパターンで設定されていること
     */
    @Test
    fun changeGenreKey() {
        // モック作成
        val viewModel = DetailsSearchViewModel()
        viewModel.init(genreKeyList, genre1List, genre2List, genre3List, genre4List)
        viewModel.genre2KeyList.value = genreKeyList
        viewModel.genre3KeyList.value = genreKeyList
        viewModel.genre4KeyList.value = genreKeyList

        // テスト実施
        viewModel.changeGenreKey(1, 0)
        assert(!viewModel.isSortGenre1ValueList.value!!)
        viewModel.changeGenreKey(1, 3)
        assertArrayEquals(viewModel.genre1ValueList.value, genre3List)
        assert(viewModel.isSortGenre1ValueList.value!!)
        viewModel.changeGenreKey(2, 4)
        assertArrayEquals(viewModel.genre2ValueList.value, genre4List)
        assert(viewModel.isSortGenre2ValueList.value!!)
        viewModel.changeGenreKey(3, 1)
        assertArrayEquals(viewModel.genre2ValueList.value, genre4List)
        assert(viewModel.isSortGenre3ValueList.value!!)
        viewModel.changeGenreKey(4, 2)
        assertArrayEquals(viewModel.genre4ValueList.value, genre2List)
        assert(viewModel.isSortGenre4ValueList.value!!)
    }

    // endregion
}
