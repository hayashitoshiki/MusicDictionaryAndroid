package com.example.musicdictionaryandroid.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageTopBinding
import com.example.musicdictionaryandroid.ui.login.StartActivity
import com.example.musicdictionaryandroid.ui.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 設定画面
 */
class MyPageTopFragment : Fragment() {

    companion object {
        const val TAG = "MyPageTopFragment"
    }

    private val viewModel: MyPageTopViewModel by viewModel()
    private lateinit var binding: FragmentMypageTopBinding
    private var isFirst = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_top, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        if (isFirst) {
            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.mypage_start_anim)
            binding.mypageTopCard.startAnimation(anim)
            isFirst = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.authStatus.observe(viewLifecycleOwner, Observer { onStateChanged(it) })

        // アーティスト一覧ボタン
        binding.artistListButton.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "end_artist_list_view_transition")
            findNavController().navigate(R.id.action_navigation_mypage_to_my_page_artist, null, null, extras)
        }
        // ユーザー情報ボタン
        binding.userinfoButton.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "end_user_info_view_transition")
            findNavController().navigate(R.id.action_navigation_mypage_to_navigation_mypage_userinfo, null, null, extras)
        }
        // ログアウトボタン
        binding.logout.setOnClickListener { viewModel.signOut() }
    }

    // ステータス監視
    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun onStateChanged(state: Status<*>) = when (state) {
        is Status.Loading -> {
        }
        is Status.Success -> {
            activityFinish()
        }
        is Status.Failure -> {
            Log.i(TAG, "Failure:${state.throwable}")
        }
        is Status.Non -> {
        }
    }

    // 終了
    private fun activityFinish() {
        val intent = Intent(activity, StartActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
