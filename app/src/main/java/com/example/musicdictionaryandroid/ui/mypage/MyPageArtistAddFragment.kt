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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageArtistAddBinding
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.entity.CallBackData
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.*
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * アーティスト情報登録・追加画面
 */
class MyPageArtistAddFragment : Fragment() {

    private val args: MyPageArtistAddFragmentArgs by navArgs()
    private val viewModel: MyPageArtistAddViewModel by viewModel()

    // 初期画面にHome画面をセット
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: FragmentMypageArtistAddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_artist_add, container, false)
        root.lifecycleOwner = viewLifecycleOwner
        root.viewModel = viewModel

        val anim1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_offset_300_anim)
        val anim2 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_offset_400_anim)
        val anim3 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_offset_500_anim)
        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
        }
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
        args.data?.let {
            root.root.artist_name_title.startAnimation(anim1)
        } ?: run {
            root.root.artist_name_edit.startAnimation(anim1)
        }
        root.root.category_title1.startAnimation(anim1)
        root.root.category_value1.startAnimation(anim1)
        root.root.category_title2.startAnimation(anim1)
        root.root.category_value2.startAnimation(anim2)
        root.root.category_title3.startAnimation(anim2)
        root.root.category_value3.startAnimation(anim2)
        root.root.category_title4.startAnimation(anim3)
        root.root.category_value4.startAnimation(anim3)
        root.root.genre1_title.startAnimation(anim3)
        root.root.genre1_value.startAnimation(anim3)
        root.root.genre2_title.startAnimation(anim3)
        root.root.genre2_value.startAnimation(anim3)
        root.root.submit.startAnimation(anim3)

        // editTextフォーカス制御
        root.root.artist_name_edit.setOnFocusChangeListener { v, hasFocus ->
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

        viewModel.init(
            resources.getStringArray(R.array.genre1_spinner_list),
            resources.getStringArray(R.array.genre12_spinner_list),
            resources.getStringArray(R.array.genre22_spinner_list),
            resources.getStringArray(R.array.genre32_spinner_list),
            resources.getStringArray(R.array.genre42_spinner_list),
            resources.getStringArray(R.array.genre52_spinner_list),
            resources.getStringArray(R.array.genre62_spinner_list)
        )
        args.data?.let {
            fragment_title.text = getString(R.string.artist_change_title)
            submit.text = getString(R.string.mypage_artist_change_button)
            artist_name_edit.visibility = View.GONE
            artist_name_title.visibility = View.VISIBLE
            viewModel.setArtist(it)

        } ?: run {
            fragment_title.text = getString(R.string.artist_add_title)
            submit.text = getString(R.string.mypage_artist_add_button)
            artist_name_edit.visibility = View.VISIBLE
            artist_name_title.visibility = View.GONE
        }
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
        viewModel.genre1ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre1(it) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre2(it) })
    }

    // ステータス監視
    private fun onStateChanged(state: Status<ArtistsForm?>) = when (state) {
        is Status.Loading -> { showProgressbar() }
        is Status.Success -> {
            Log.d("TAG", "Success:${state.data}")
            hideProgressbar()
            state.data?.let {
                 back()
            } ?: run {
                showServerError()
            }
        }
        is Status.Failure -> {
            Log.d("TAG", "Failure:${state.throwable}")
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
    private fun showErrorToastValidate() {
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
