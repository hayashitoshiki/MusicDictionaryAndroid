package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentCategorySearchBinding
import kotlinx.android.synthetic.main.fragment_category_search.*


/**
 * カテゴリ検索画面
 *
 */
class CategorySearchFragment : Fragment() {

    private val viewModel: CategorySearchViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CategorySearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCategorySearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_search, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
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
            resources.getStringArray(R.array.genre62_spinner_list)
        )
        viewModel.genre1ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre1(it) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre2(it) })

        // 検索ボタン
        submit.setOnClickListener {
            val action = CategorySearchFragmentDirections.actionCategorySearchToNavigationResult(viewModel.artistForm)
            findNavController().navigate(action)
        }
    }
}
