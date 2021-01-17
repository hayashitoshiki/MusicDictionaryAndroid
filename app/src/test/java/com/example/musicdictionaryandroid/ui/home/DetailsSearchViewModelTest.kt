package com.example.musicdictionaryandroid.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * 詳細検索画面
 */

class DetailsSearchViewModelTest {

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
        val observer = mock<Observer<Boolean>>()
        viewModel.isEnableSubmitButton.observeForever(observer)
    }

    /**
     * バリデーションロジック
     *
     * 条件：各カテゴリのジャンルのどれか１つを選択していれば活性化する、１つも選択していな場合、非活性となる
     * 期待結果：バリデーション条件を満たさない場合false、満たす場合trueが帰る
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onButtonValidate() {
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

    /**
     * 属性を設定状態によって＋ボタンの表示が切り替わるか
     * 条件　　：各ジャンルで属性設定→設定なしの切り替えを行う
     * 期待結果：それぞれのジャンルで表示・非表示の切り替えが行われていること
     */
    @Test
    fun changeGenreValue() {
        // モック作成
        val viewModel = DetailsSearchViewModel()
        viewModel.init(genreKeyList, genre1List, genre2List, genre3List, genre4List)
        viewModel.genre2KeyList.value = genreKeyList
        viewModel.genre3KeyList.value = genreKeyList
        viewModel.genre4KeyList.value = genreKeyList

        // テスト実施
        viewModel.changeGenreValue(1, 1)
        assert(viewModel.isGenreAddButton1.value!!)
        viewModel.changeGenreValue(1, 0)
        assert(!viewModel.isGenreAddButton1.value!!)
        viewModel.changeGenreValue(2, 1)
        assert(viewModel.isGenreAddButton2.value!!)
        viewModel.changeGenreValue(2, 0)
        assert(!viewModel.isGenreAddButton2.value!!)
        viewModel.changeGenreValue(3, 1)
        assert(viewModel.isGenreAddButton3.value!!)
        viewModel.changeGenreValue(3, 0)
        assert(!viewModel.isGenreAddButton3.value!!)
    }

    /**
     * カテゴリ追加時、次のカテゴリが前のカテゴリよりサイズが１小さいか
     * 条件　　：全てのカテゴリ追加ボタンタップ
     * 期待結果：前のカテゴリよりサイズが１小さい
     */
    @Test
    fun addSortList() {
        // モック作成
        val viewModel = DetailsSearchViewModel()
        viewModel.init(genreKeyList, genre1List, genre2List, genre3List, genre4List)
        viewModel.genre2KeyList.value = genreKeyList

        // テスト実施
        viewModel.changeGenreKey(1, 4)
        viewModel.changeGenreValue(1, 1)
        viewModel.addSortList(1)
        assertEquals(viewModel.genre2KeyList.value!!.size, viewModel.genre1KeyList.value!!.size - 1)

        viewModel.changeGenreKey(2, 3)
        viewModel.changeGenreValue(2, 1)
        viewModel.addSortList(2)
        assertEquals(viewModel.genre3KeyList.value!!.size, viewModel.genre2KeyList.value!!.size - 1)

        viewModel.changeGenreKey(3, 2)
        viewModel.changeGenreValue(3, 1)
        viewModel.addSortList(3)
        assertEquals(viewModel.genre3KeyList.value!!.size, viewModel.genre2KeyList.value!!.size - 1)
    }
}
