package com.example.musicdictionaryandroid.ui.mypage

import android.content.Context
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
import com.example.musicdictionaryandroid.model.util.Status
import com.example.tosik.musicdictionary_androlid.model.net.CallBackData
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.*
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.view.*

class MyPageArtistAddFragment : Fragment() {

    private val args: MyPageArtistAddFragmentArgs by navArgs()
    private val viewModel: MyPageArtistAddViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MyPageArtistAddViewModel::class.java)
    }

    // 初期画面にHome画面をセット
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: FragmentMypageArtistAddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_artist_add, container, false)
        root.lifecycleOwner = viewLifecycleOwner
        root.viewModel = viewModel

        val anim1 = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_offset_300_anim)
        val anim2 = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_offset_400_anim)
        val anim3 = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_offset_500_anim)
        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
        }
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
        root.root.search_bar.startAnimation(anim1)
        root.root.category_title1.startAnimation(anim1)
        root.root.category_value1.startAnimation(anim1)
        root.root.category_title2.startAnimation(anim2)
        root.root.category_value2.startAnimation(anim2)
        root.root.category_title3.startAnimation(anim3)
        root.root.category_value3.startAnimation(anim3)
        root.root.category_title4.startAnimation(anim3)
        root.root.category_value4.startAnimation(anim3)
        root.root.submit.startAnimation(anim3)


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

        args.data?.let {
            fragment_title.text = getString(R.string.artist_change_title)
            submit.text = getString(R.string.mypage_artist_change_button)
            viewModel.init(it)
        } ?: run {
            fragment_title.text = getString(R.string.artist_add_title)
            submit.text = getString(R.string.mypage_artist_add_button)
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<CallBackData?>) = when (state) {
        is Status.Loading -> { showProgressbar() }
        is Status.Success -> {
            Log.d("TAG","Success:${state.data}")
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
            Log.d("TAG","Failure:${state.throwable}")
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
