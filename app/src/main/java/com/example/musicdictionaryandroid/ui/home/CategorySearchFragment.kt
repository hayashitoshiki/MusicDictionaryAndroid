package com.example.musicdictionaryandroid.ui.home

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentCategorySearchBinding
import com.example.musicdictionaryandroid.ui.transition.FabTransform
import com.example.musicdictionaryandroid.ui.transition.HOME_CATEGORY_BUTTON
import kotlin.coroutines.CoroutineContext
import kotlinx.android.synthetic.main.fragment_category_search.*
import kotlinx.android.synthetic.main.fragment_category_search.view.*
import kotlinx.coroutines.*

/**
 * カテゴリ検索画面
 *
 */
class CategorySearchFragment : Fragment(), CoroutineScope {

    companion object {
        private var state = 0
    }

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val viewModel: CategorySearchViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CategorySearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
        }
        val trans = FabTransform(resources.getColor(R.color.bg_color_primary, null), R.drawable.round_primary_dark_button, HOME_CATEGORY_BUTTON)
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = transition

        val binding: FragmentCategorySearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_search, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (state == 1) {
            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_bottom)
            binding.root.category_card.startAnimation(anim)
            state = 0
        }
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
            val anim1 = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_bottom)
            category_card.startAnimation(anim1)
            category_card.visibility = View.GONE
            launch {
                delay(resources.getInteger(R.integer.fade_out_time).toLong())
                val action = CategorySearchFragmentDirections.actionCategorySearchToNavigationResult(viewModel.artistForm)
                findNavController().navigate(action)
                state = 1
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
