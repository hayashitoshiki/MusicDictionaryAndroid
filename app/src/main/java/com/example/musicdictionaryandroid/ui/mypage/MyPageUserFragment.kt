package com.example.musicdictionaryandroid.ui.mypage

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageUserBinding
import kotlinx.android.synthetic.main.fragment_mypage_user.view.*

/**
 * ユーザー情報画面
 */
class MyPageUserFragment : Fragment() {

    private val viewModel: MyPageUserViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MyPageUserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: FragmentMypageUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_user, container, false)
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
        root.root.email_label.startAnimation(anim1)
        root.root.name_label.startAnimation(anim1)
        root.root.name_text.startAnimation(anim1)
        root.root.email_text.startAnimation(anim1)
        root.root.gender_label.startAnimation(anim2)
        root.root.gender_text.startAnimation(anim2)
        root.root.area_label.startAnimation(anim2)
        root.root.area_text.startAnimation(anim2)
        root.root.birthday_label.startAnimation(anim3)
        root.root.birthday_text.startAnimation(anim3)
        root.root.favorite_label.startAnimation(anim3)
        root.root.favorite_text.startAnimation(anim3)

        return root.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()
    }
}
