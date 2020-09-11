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
import com.example.musicdictionaryandroid.databinding.FragmentDetailsSearchBinding
import kotlinx.android.synthetic.main.fragment_details_search.*

class DetailsSearchFragment : Fragment() {

    private val viewModel: DetailsSearchViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(DetailsSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.init(resources.getStringArray(R.array.category_spinner_list),
            resources.getStringArray(R.array.gender_spinner_list),
            resources.getStringArray(R.array.registration_spinner_list) ,
            resources.getStringArray(R.array.area_spinner_list),
            resources.getStringArray(R.array.birthday_spinner_list))

        viewModel.genre1KeyInt.observe(viewLifecycleOwner, Observer {
            viewModel.changeGenreKey(viewModel.genre1KeyList.value?.get(it), viewModel.genre1ValueList, viewModel.isSortGenre1ValueList)
        })
        viewModel.genre2KeyInt.observe(viewLifecycleOwner, Observer {
            viewModel.changeGenreKey(viewModel.genre2KeyList.value?.get(it), viewModel.genre2ValueList, viewModel.isSortGenre2ValueList)
        })
        viewModel.genre3KeyInt.observe(viewLifecycleOwner, Observer {
            viewModel.changeGenreKey(viewModel.genre3KeyList.value?.get(it), viewModel.genre3ValueList, viewModel.isSortGenre3ValueList)
        })
        viewModel.genre4KeyInt.observe(viewLifecycleOwner, Observer {
            viewModel.changeGenreKey(viewModel.genre4KeyList.value?.get(it), viewModel.genre4ValueList, viewModel.isSortGenre4ValueList)
        })
        viewModel.genre1ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreValue(it, viewModel.isGenreAddButton1) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreValue(it, viewModel.isGenreAddButton2) })
        viewModel.genre3ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreValue(it, viewModel.isGenreAddButton3) })

        // 検索結果画面へ遷移
        submit.setOnClickListener {
            if (viewModel.artistForm.gender != 0 || viewModel.artistForm.length != 0 || viewModel.artistForm.voice != 0 || viewModel.artistForm.lyrics != 0) {
                val action = DetailsSearchFragmentDirections.actionNavigationDetailsSearchToNavigationResult(viewModel.artistForm)
                findNavController().navigate(action)
            } else {
                Log.d("TAG", "どれか一つ以上入力してください")
            }
        }
    }

}