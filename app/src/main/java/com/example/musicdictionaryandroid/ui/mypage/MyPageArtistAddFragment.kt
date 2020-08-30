package com.example.musicdictionaryandroid.ui.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageArtistAddBinding
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.Status
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import kotlinx.android.synthetic.main.fragment_category_search.lyric1
import kotlinx.android.synthetic.main.fragment_category_search.lyric2
import kotlinx.android.synthetic.main.fragment_category_search.lyric3
import kotlinx.android.synthetic.main.fragment_category_search.lyric4
import kotlinx.android.synthetic.main.fragment_category_search.lyric5
import kotlinx.android.synthetic.main.fragment_category_search.voice1
import kotlinx.android.synthetic.main.fragment_category_search.voice2
import kotlinx.android.synthetic.main.fragment_category_search.voice3
import kotlinx.android.synthetic.main.fragment_category_search.voice4
import kotlinx.android.synthetic.main.fragment_category_search.voice5
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.*
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.gender1
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.gender2
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.lenght1
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.lenght2
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.lenght3
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.lenght4
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.lenght5

class MyPageArtistAddFragment : Fragment() {

    private val args: MyPageArtistAddFragmentArgs by navArgs()
    private val viewModel: MyPageArtistAddViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MyPageArtistAddViewModel::class.java)
    }

    // 初期画面にHome画面をセット
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: FragmentMypageArtistAddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_artist_add, container, false)
        root.viewModel = viewModel

        // editTextフォーカス制御
        root.root.search_bar.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        root.root.setOnTouchListener { v, event ->
            root.root.requestFocus()
            v?.onTouchEvent(event) ?: true
        }
        return root.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
        viewModel.searchText.observe(viewLifecycleOwner, Observer { viewModel.changeArtistName(it) })
        args.data?.let { init(it) }
    }

    private fun init(artist: ArtistsForm) {

        viewModel.searchText.value = artist.name
        viewModel.oldArtistName = artist.name
        viewModel.artistForm = artist

        when (artist.gender) {
            1 -> gender1.isChecked = true
            2 -> gender2.isChecked = true
        }
        when (artist.length) {
            1 -> lenght1.isChecked = true
            2 -> lenght2.isChecked = true
            3 -> lenght3.isChecked = true
            4 -> lenght4.isChecked = true
            5 -> lenght5.isChecked = true
        }
        when (artist.voice) {
            1 -> voice1.isChecked = true
            2 -> voice2.isChecked = true
            3 -> voice3.isChecked = true
            4 -> voice4.isChecked = true
            5 -> voice5.isChecked = true
        }
        when (artist.lyrics) {
            1 -> lyric1.isChecked = true
            2 -> lyric2.isChecked = true
            3 -> lyric3.isChecked = true
            4 -> lyric4.isChecked = true
            5 -> lyric5.isChecked = true
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<CallBackData?>) = when (state) {
        is Status.Loading -> { showProgressbar() }
        is Status.Success -> {
            hideProgressbar()
            state.data?.let {
                if (it.status == "200") back()
                else if (it.status == "000") showErrorToastValidate()
                else showServerError()
            } ?: run {
                showServerError()
            }
        }
        is Status.Failure -> {
            hideProgressbar()
            showServerError()
        }
    }

    // 登録したら登録画面から設定画面へ画面戻る(更新)
    private fun back() {
        findNavController().popBackStack()
    }

    private fun showServerError() {
        Toast.makeText(requireContext(), "サーバーエラーが発生しました", Toast.LENGTH_SHORT).show()
    }

    // 入力不足エラー
    fun showErrorToastValidate() {
        Toast.makeText(requireContext(), "全て入力してください", Toast.LENGTH_SHORT).show()
    }

    // プログレスバー表示
    private fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    // プログレスバー非表示
    private fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    companion object {

        @JvmStatic
        fun newInstance(): MyPageArtistAddFragment {
            val fragment = MyPageArtistAddFragment()
            val args = Bundle()
            return fragment
        }
    }
}
