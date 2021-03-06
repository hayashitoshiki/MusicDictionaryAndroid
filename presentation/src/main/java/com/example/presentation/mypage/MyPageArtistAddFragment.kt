package com.example.presentation.mypage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.entity.Artist
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMypageArtistAddBinding
import com.example.presentation.util.MessageUtilImp
import com.example.presentation.util.Status
import com.example.presentation.util.setSafeClickListener
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * アーティスト情報登録・追加画面
 */
class MyPageArtistAddFragment : Fragment() {

    private lateinit var binding: FragmentMypageArtistAddBinding
    private val args: MyPageArtistAddFragmentArgs by navArgs()
    private val viewModel: MyPageArtistAddViewModel by inject { parametersOf(args.data, MessageUtilImp(requireContext())) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_artist_add, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

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
            binding.artistNameTitle.startAnimation(anim1)
        } ?: run {
            binding.artistNameEdit.startAnimation(anim1)
        }
        binding.categoryTitle1.startAnimation(anim1)
        binding.categoryValue1.startAnimation(anim1)
        binding.categoryTitle2.startAnimation(anim1)
        binding.categoryValue2.startAnimation(anim2)
        binding.categoryTitle3.startAnimation(anim2)
        binding.categoryValue3.startAnimation(anim2)
        binding.categoryTitle4.startAnimation(anim3)
        binding.categoryValue4.startAnimation(anim3)
        binding.genre1Title.startAnimation(anim3)
        binding.genre1Value.startAnimation(anim3)
        binding.genre2Title.startAnimation(anim3)
        binding.genre2Value.startAnimation(anim3)
        binding.submit.startAnimation(anim3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner, { onStateChanged(it) })
        viewModel.genre1.observe(viewLifecycleOwner, { viewModel.changeGenre1(it) })

        // 送信ボタン
        binding.submit.setSafeClickListener {
            viewModel.submit()
        }

        // editTextフォーカス制御
        binding.artistNameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.setOnTouchListener { v, event ->
            binding.root.requestFocus()
            v?.onTouchEvent(event) ?: true
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<Artist>) = when (state) {
        is Status.Loading -> {
        }
        is Status.Success -> {
            back()
        }
        is Status.Failure -> {
            showServerError()
        }
        is Status.Non -> {
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
}
