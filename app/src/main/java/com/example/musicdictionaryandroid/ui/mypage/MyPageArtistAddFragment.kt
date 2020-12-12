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
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.adapter.setSafeClickListener
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.*
import kotlinx.android.synthetic.main.fragment_mypage_artist_add.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * アーティスト情報登録・追加画面
 */
class MyPageArtistAddFragment : Fragment() {

    private val args: MyPageArtistAddFragmentArgs by navArgs()
    private val viewModel: MyPageArtistAddViewModel by viewModel()

    @Suppress("JAVA_CLASS_ON_COMPANION")
    companion object {
        val TAG = javaClass.name
        @JvmStatic
        fun newInstance(): MyPageArtistAddFragment {
            return MyPageArtistAddFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: FragmentMypageArtistAddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_artist_add, container, false)
        root.lifecycleOwner = viewLifecycleOwner
        root.viewModel = viewModel

        // アニメーション設定
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
            resources.getStringArray(R.array.genre62_spinner_list),
            args.data
        )

        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
        viewModel.genre1ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre1(it) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre2(it) })

        // 送信ボタン
        submit.setSafeClickListener {
            viewModel.submit()
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<ArtistsForm?>) = when (state) {
        is Status.Loading -> { showProgressbar() }
        is Status.Success -> {
            hideProgressbar()
            state.data?.let {
                 back()
            } ?: run {
                showServerError()
            }
        }
        is Status.Failure -> {
            Log.i(TAG, "Failure:${state.throwable}")
            hideProgressbar()
            showServerError()
        }
    }

    // 設定画面へ画面戻る
    private fun back() {
        findNavController().popBackStack()
    }

    // エラートースト表示
    private fun showServerError() {
        Toast.makeText(requireContext(), "エラーが発生しました", Toast.LENGTH_SHORT).show()
    }

    // プログレスバー表示
    private fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    // プログレスバー非表示
    private fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }
}
