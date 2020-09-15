package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet
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
import com.example.musicdictionaryandroid.ui.transition.FabTransform
import com.example.musicdictionaryandroid.ui.transition.HOME_DETAILS_BUTTON
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
        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
        }
        val  trans = FabTransform(resources.getColor(R.color.colorPrimary), R.drawable.round_primary_dark_button, HOME_DETAILS_BUTTON)
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = transition

        val binding: FragmentDetailsSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.init(
            resources.getStringArray(R.array.category_spinner_list),
            resources.getStringArray(R.array.gender_spinner_list),
            resources.getStringArray(R.array.registration_spinner_list),
            resources.getStringArray(R.array.area_spinner_list),
            resources.getStringArray(R.array.birthday_spinner_list)
        )

        viewModel.genre1KeyInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreKey(1, it) })
        viewModel.genre2KeyInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreKey(2, it) })
        viewModel.genre3KeyInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreKey(3, it) })
        viewModel.genre4KeyInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreKey(4, it) })
        viewModel.genre1ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreValue(1,it) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreValue(2, it) })
        viewModel.genre3ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenreValue(3, it) })

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