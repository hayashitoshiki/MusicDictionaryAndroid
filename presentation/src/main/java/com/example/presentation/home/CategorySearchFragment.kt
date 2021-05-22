package com.example.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.ChangeBounds
import androidx.transition.ChangeClipBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCategorySearchBinding
import com.example.presentation.util.transition.FabTransform
import com.example.presentation.util.transition.HOME_CATEGORY_BUTTON
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * カテゴリ検索画面
 */
class CategorySearchFragment : Fragment(), CoroutineScope {

    companion object {
        private var state = 0
    }

    private lateinit var binding: FragmentCategorySearchBinding
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    private val viewModel: CategorySearchViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(CategorySearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
        }
        val trans = FabTransform(
            resources.getColor(R.color.bg_color_primary, null),
            R.drawable.round_primary_dark_button,
            HOME_CATEGORY_BUTTON
        )
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = transition

        if (com.example.presentation.home.CategorySearchFragment.Companion.state == 1) {
            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_bottom)
            binding.categoryCard.startAnimation(anim)
            com.example.presentation.home.CategorySearchFragment.Companion.state = 0
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        // 検索ボタン
        binding.submit.setOnClickListener {
            val anim1 = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_bottom)
            binding.categoryCard.startAnimation(anim1)
            binding.categoryCard.visibility = View.GONE
            launch {
                delay(resources.getInteger(R.integer.fade_out_time).toLong())
                val artist = viewModel.getArtist()
                val action = CategorySearchFragmentDirections.actionCategorySearchToNavigationResult(artist)
                findNavController().navigate(action)
                com.example.presentation.home.CategorySearchFragment.Companion.state = 1
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
