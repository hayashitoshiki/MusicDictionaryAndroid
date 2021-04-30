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
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageUserBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * ユーザー情報画面
 */
class MyPageUserFragment : Fragment() {

    private val viewModel: MyPageUserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root: FragmentMypageUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_user, container, false)
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
        root.emailLabel.startAnimation(anim1)
        root.nameLabel.startAnimation(anim1)
        root.nameText.startAnimation(anim1)
        root.emailText.startAnimation(anim1)
        root.genderLabel.startAnimation(anim2)
        root.genderText.startAnimation(anim2)
        root.areaLabel.startAnimation(anim2)
        root.areaText.startAnimation(anim2)
        root.birthdayLabel.startAnimation(anim3)
        root.birthdayText.startAnimation(anim3)
        root.favoriteLabel.startAnimation(anim3)
        root.favoriteText.startAnimation(anim3)

        return root.root
    }
}
