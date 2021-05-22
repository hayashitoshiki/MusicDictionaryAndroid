package com.example.presentation.home

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
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.util.setSafeClickListener
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

/**
 * HOME画面
 */
class HomeFragment : Fragment(), CoroutineScope {

    companion object {
        private var state = 0
    }

    private lateinit var binding: FragmentHomeBinding
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        if (state == 0) {
            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.home_start_anim)
            binding.homeView.startAnimation(anim)
        }
        state = 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchText.observe(viewLifecycleOwner, Observer { viewModel.changeSubmitButton(it.length) })
        val anim1 = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out)

        // カテゴリボタン
        binding.categoryButton.setOnClickListener {
            state = 1
            val extras = FragmentNavigatorExtras(it to "end_category_view_transition")
            findNavController().navigate(R.id.action_navigation_home_to_category_search, null, null, extras)
        }
        // 詳細検索ボタン
        binding.detailButton.setOnClickListener {
            state = 1
            val extras = FragmentNavigatorExtras(it to "end_detail_view_transition")
            findNavController().navigate(R.id.action_navigation_home_to_navigation_details_search, null, null, extras)
        }
        // 急上昇ボタン
        binding.soaringButton.setSafeClickListener {
            binding.homeView.startAnimation(anim1)
            binding.homeView.visibility = View.GONE
            launch {
                delay(resources.getInteger(R.integer.fade_out_time_home).toLong())
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationResultSoaring()
                findNavController().navigate(action)
            }
        }
        // おすすめボタン
        binding.recommendButton.setSafeClickListener {
            binding.homeView.startAnimation(anim1)
            binding.homeView.visibility = View.GONE
            launch {
                delay(resources.getInteger(R.integer.fade_out_time_home).toLong())
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationResultRecommend()
                findNavController().navigate(action)
            }
        }
        // 検索ボタン
        binding.submit.setSafeClickListener {
            binding.homeView.startAnimation(anim1)
            binding.homeView.visibility = View.GONE
            launch {
                delay(resources.getInteger(R.integer.fade_out_time_home).toLong())
                val artist = viewModel.getArtist()
                val action = HomeFragmentDirections.actionNavigationHomeToNavigationResult(artist)
                findNavController().navigate(action)
            }
        }
        // editTextフォーカス制御
        binding.searchBar.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.setOnTouchListener { v, event ->
            binding.root.requestFocus()
            v?.onTouchEvent(event) ?: true
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
