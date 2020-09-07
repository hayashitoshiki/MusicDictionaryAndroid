package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.util.Log
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
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import kotlinx.android.synthetic.main.fragment_category_search.*

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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 検索結果画面へ遷移
        submit.setOnClickListener{
            if (viewModel.artistForm.gender != 0 || viewModel.artistForm.length != 0 || viewModel.artistForm.voice != 0 || viewModel.artistForm.lyrics != 0) {
                val action = CategorySearchFragmentDirections.actionCategorySearchToNavigationResult(viewModel.artistForm)
                findNavController().navigate(action)
            } else {
                Log.d("TAG", "どれか一つ以上入力してください")
            }
        }
    }
}
