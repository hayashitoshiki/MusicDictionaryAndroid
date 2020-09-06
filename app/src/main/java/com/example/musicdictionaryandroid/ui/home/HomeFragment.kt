package com.example.musicdictionaryandroid.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentHomeBinding
import com.example.musicdictionaryandroid.model.repository.PreferenceRepositoryImp
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment {
            val f = HomeFragment()
            return f
        }
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        root.homeViewModel = viewModel

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
        viewModel.searchText.observe(viewLifecycleOwner, Observer { changeSubmitButton(it.length) })
        // カテゴリボタン
        category_button.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToCategorySearch()
            findNavController().navigate(action)
        }
        // 検索ボタン
        submit.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationResult(viewModel.artistsFrom)
            findNavController().navigate(action)
        }

        // 検索ボタンのバリデート
        val count = PreferenceRepositoryImp.getFavorite()
        if (count == 0) {
            submit.isClickable = false
            search_bar.isEnabled = false
            submit_button_enable.visibility = View.VISIBLE
        }
        if (count < 3) {
            category_button.isClickable = false
            category_button_enable.visibility = View.VISIBLE
        }
        if (count < 5) {
            detail_button.isClickable = false
            detail_button_enable.visibility = View.VISIBLE
        }
        if (count < 8) {
            soaring_button.isClickable = false
            soaring_button_enable.visibility = View.VISIBLE
        }
        if (count < 10) {
            recommend_button.isClickable = false
            recommend_button_enable.visibility = View.VISIBLE
        }
    }

    // 検索ボタン活性・非活性
    private fun changeSubmitButton(count: Int) = when (count) {
        0 -> {
            submit.isClickable = false
        } else -> {
            viewModel.artistsFrom.name = viewModel.searchText.value.toString()
            submit.isClickable = true
        }
    }
}
