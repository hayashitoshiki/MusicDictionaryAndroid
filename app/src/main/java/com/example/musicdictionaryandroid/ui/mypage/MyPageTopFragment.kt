package com.example.musicdictionaryandroid.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import kotlinx.android.synthetic.main.fragment_mypage_top.*

class MyPageTopFragment : Fragment() {

    private lateinit var myPageTopViewModel: MyPageTopViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myPageTopViewModel = ViewModelProviders.of(this).get(MyPageTopViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_mypage_top, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        artist_list_button.setOnClickListener {
            val action = MyPageTopFragmentDirections.actionNavigationMypageToMyPageArtist()
            findNavController().navigate(action)
        }
    }
}
