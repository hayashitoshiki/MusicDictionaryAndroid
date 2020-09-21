package com.example.musicdictionaryandroid.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.submit
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment {
            val f = HomeFragment()
            return f
        }
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        root.viewModel = viewModel
        root.lifecycleOwner = viewLifecycleOwner

        val anim = AnimationUtils.loadAnimation(requireContext(),R.anim.home_start_anim)
        root.root.home_view.startAnimation(anim)

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

        viewModel.searchText.observe(viewLifecycleOwner, Observer { viewModel.changeSubmitButton(it.length) })

        // カテゴリボタン
        category_button.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "end_category_view_transition")
            findNavController().navigate(R.id.action_navigation_home_to_category_search, null, null, extras)
        }
        // 詳細検索ボタン
        detail_button.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "end_detail_view_transition")
            findNavController().navigate(R.id.action_navigation_home_to_navigation_details_search, null, null, extras)
        }
        // 急上昇ボタン
        soaring_button.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationResultSoaring()
            findNavController().navigate(action)
        }
        // おすすめボタン
        recommend_button.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationResultRecommend()
            findNavController().navigate(action)
        }
        // 検索ボタン
        submit.setOnClickListener {
            val action = HomeFragmentDirections.actionNavigationHomeToNavigationResult(viewModel.artistsFrom)
            findNavController().navigate(action)
        }
    }
}
