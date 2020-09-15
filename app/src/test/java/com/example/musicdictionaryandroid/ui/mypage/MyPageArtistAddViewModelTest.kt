package com.example.musicdictionaryandroid.ui.mypage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.repository.ApiServerRepository
import com.example.musicdictionaryandroid.model.repository.FireBaseRepository
import com.example.musicdictionaryandroid.model.util.Status
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.rules.TestRule
import retrofit2.Response

/**
 * アーティスト登録・更新画面
 */

class MyPageArtistAddViewModelTest {

    companion object{
        const val NAME = "test1"
        const val GENDER = 1
        const val VOICE = 2
        const val LENGTH = 3
        const val LYRIC = 4
    }

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val response: Response<CallBackData> = Response.success<CallBackData>(CallBackData("success"))

    // LiveData用
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // coroutines用
    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }


    /**
     * アーティスト登録（正常系）
     * 条件：全ての値を入力している
     * 期待結果：usecase.cancel()が呼ばれる
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitAdd() = runBlocking {
        // テストクラス作成
        val firebaseRepository = mockk<FireBaseRepository>().also {
            every { it.getEmail() } returns "aaa"
        }
        val apiServerRepository = mockk<ApiServerRepository> ().also {
            coEvery { it.addArtist(any(), "aaa") } returns response
        }
        val viewModel = MyPageArtistAddViewModel(firebaseRepository, apiServerRepository)

        // 実行
        viewModel.changeArtistName("test")
        viewModel.checkedChangeGender(1)
        viewModel.checkedChangeLength(1)
        viewModel.checkedChangeVoice(1)
        viewModel.checkedChangeLyric(1)
        viewModel.submit()
        coVerify{apiServerRepository.addArtist(any(), "aaa")}
    }

    /**
     * 検索バリデート
     * 条件：１つずつ入力していき全てのバリデートが通ること
     * 期待結果：５種類全部のバリデートが通ること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitError() = runBlocking {
        // テストクラス作成
        val firebaseRepository = mockk<FireBaseRepository>().also {
            every { it.getEmail() } returns "aaa"
        }
        val apiServerRepository = mockk<ApiServerRepository> ().also {
            coEvery { it.addArtist(any(), "aaa") } returns response
        }
        val viewModel = MyPageArtistAddViewModel(firebaseRepository, apiServerRepository)

        // 実行
        viewModel.submit()
        Assert.assertEquals((viewModel.status.value as Status.Success).data!!.status, "001")
        viewModel.changeArtistName("test")
        viewModel.submit()
        Assert.assertEquals((viewModel.status.value as Status.Success).data!!.status, "002")
        viewModel.checkedChangeGender(1)
        viewModel.submit()
        Assert.assertEquals((viewModel.status.value as Status.Success).data!!.status, "003")
        viewModel.checkedChangeLength(1)
        viewModel.submit()
        Assert.assertEquals((viewModel.status.value as Status.Success).data!!.status, "004")
        viewModel.checkedChangeVoice(1)
        viewModel.submit()
        Assert.assertEquals((viewModel.status.value as Status.Success).data!!.status, "005")
        viewModel.checkedChangeLyric(1)
        viewModel.submit()
        coVerify{apiServerRepository.addArtist(any(), "aaa")}
    }

    /**
     * アーティスト更新(正常系）
     * 条件：全ての値を変更して変更されていることが確認できること
     * 期待結果：５種類全部の値が変更されていること・アーティスト更新メソッドが呼ばれていること
     */
    @ExperimentalCoroutinesApi
    @Test
    fun onSubmitUpdate() = runBlocking {
        // テストクラス作成
        val firebaseRepository = mockk<FireBaseRepository>().also {
            every { it.getEmail() } returns "aaa"
        }
        val apiServerRepository = mockk<ApiServerRepository>().also{
            coEvery { it.updateArtist(any(),"test", "aaa") } returns response
        }
        val viewModel = MyPageArtistAddViewModel(firebaseRepository, apiServerRepository)
        val artist = ArtistsForm("test",1,1,1,1)

        // 実行
        viewModel.init(artist)
        viewModel.changeArtistName(NAME)
        viewModel.checkedChangeGender(GENDER)
        viewModel.checkedChangeLength(LENGTH)
        viewModel.checkedChangeVoice(VOICE)
        viewModel.checkedChangeLyric(LYRIC)
        viewModel.submit()
        Assert.assertEquals(viewModel.artistForm.value!!.name, NAME)
        Assert.assertEquals(viewModel.artistForm.value!!.voice, VOICE)
        Assert.assertEquals(viewModel.artistForm.value!!.lyrics, LYRIC)
        Assert.assertEquals(viewModel.artistForm.value!!.length, LENGTH)
        Assert.assertEquals(viewModel.artistForm.value!!.gender, GENDER)
        coVerify{apiServerRepository.updateArtist(any(),"test", "aaa")}
    }


}