package com.example.musicdictionaryandroid.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.login.StartActivity
import kotlinx.android.synthetic.main.fragment_mypage_top.*
import kotlinx.android.synthetic.main.fragment_mypage_top.view.*


class MyPageTopFragment : Fragment() {

    private lateinit var viewModel: MyPageTopViewModel

    private var firstCreateFrg = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MyPageTopViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mypage_top, container, false)

        if (firstCreateFrg) {
            val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.mypage_start_anim)
            root.mypage_top_card.startAnimation(anim)
            firstCreateFrg = false
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.authStatus.observe(viewLifecycleOwner, Observer { onStateChanged(it) })

        // アーティスト一覧ボタン
        artist_list_button.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "end_artist_list_view_transition")
            findNavController().navigate(R.id.action_navigation_mypage_to_my_page_artist, null, null, extras)
        }
        // ユーザー情報ボタン
        userinfo_button.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "end_user_info_view_transition")
            findNavController().navigate(R.id.action_navigation_mypage_to_navigation_mypage_userinfo, null, null, extras)
        }
        // ログアウトボタン
        logout.setOnClickListener {
            viewModel.signOut()
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<*>) = when (state) {
        is Status.Loading -> {  }
        is Status.Success -> {
            if (state.data != "error" ) {
                activityFinish()
            } else {
                showErrorNetwork()
            }
        }
        is Status.Failure -> { showErrorNetwork() }
    }

    //終了
    private fun activityFinish() {
        val intent = Intent(activity, StartActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    //通信エラーダイアログ
    private fun showErrorNetwork() {
        Toast.makeText(requireContext(), "通信環境の良いところでお試しください", Toast.LENGTH_LONG).show()
    }
}
