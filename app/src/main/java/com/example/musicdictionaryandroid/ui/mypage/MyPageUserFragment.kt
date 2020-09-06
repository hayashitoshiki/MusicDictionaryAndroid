package com.example.musicdictionaryandroid.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageUserBinding

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
        return root.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()
    }

}