package com.example.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.databinding.FragmentDetailsSearchBinding
import com.example.presentation.util.transition.FabTransform
import com.example.presentation.util.transition.HOME_DETAILS_BUTTON
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.*

/**
 * 詳細検索画面
 */
class DetailsSearchFragment : Fragment(), CoroutineScope {

    companion object {
        private var state = 0
    }

    private lateinit var binding: FragmentDetailsSearchBinding
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    private val viewModel: DetailsSearchViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(DetailsSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val trans = FabTransform(
            resources.getColor(R.color.bg_color_primary, null),
            R.drawable.round_primary_dark_button,
            HOME_DETAILS_BUTTON
        )
        sharedElementEnterTransition = trans
        sharedElementReturnTransition = trans
        if (state == 1) {
            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_bottom)
            binding.detailSearchView.startAnimation(anim)
            state = 0
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(
            resources.getStringArray(R.array.category_spinner_list),
            resources.getStringArray(R.array.gender_spinner_list),
            resources.getStringArray(R.array.registration_spinner_list),
            resources.getStringArray(R.array.area_spinner_list),
            resources.getStringArray(R.array.birthday_spinner_list)
        )

        viewModel.genre1KeyInt.observe(viewLifecycleOwner, { viewModel.changeGenreKey(1, it) })
        viewModel.genre2KeyInt.observe(viewLifecycleOwner, { viewModel.changeGenreKey(2, it) })
        viewModel.genre3KeyInt.observe(viewLifecycleOwner, { viewModel.changeGenreKey(3, it) })
        viewModel.genre4KeyInt.observe(viewLifecycleOwner, { viewModel.changeGenreKey(4, it) })
        viewModel.genre1ValueInt.observe(viewLifecycleOwner, { viewModel.changeGenreValue(1, it) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, { viewModel.changeGenreValue(2, it) })
        viewModel.genre3ValueInt.observe(viewLifecycleOwner, { viewModel.changeGenreValue(3, it) })

        // 検索結果画面へ遷移
        binding.submit.setOnClickListener {
            val anim1 = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_bottom)
            binding.detailSearchView.startAnimation(anim1)
            binding.detailSearchView.visibility = View.GONE
            launch {
                delay(resources.getInteger(R.integer.fade_out_time).toLong())
                val artist = viewModel.getArtist()
                val action =
                    DetailsSearchFragmentDirections.actionNavigationDetailsSearchToNavigationResult(artist)
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
