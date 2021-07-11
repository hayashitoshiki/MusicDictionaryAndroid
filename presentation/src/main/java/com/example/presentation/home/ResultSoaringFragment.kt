package com.example.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.domain.model.value.ArtistSearchContents
import com.example.presentation.R
import com.example.presentation.databinding.FragmentResultSoaringBinding
import com.example.presentation.util.Status
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 急上昇アーティスト一覧画面
 */
class ResultSoaringFragment : Fragment() {

    private val viewModel: ResultSoaringViewModel by viewModel()
    private val resultViewModel: ResultAdapterViewModel by viewModel()
    private lateinit var binding: FragmentResultSoaringBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_soaring, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, { onStateChanged(it) })
        viewModel.getSoaring()
    }

    // ステータス監視
    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun onStateChanged(state: Status<List<ArtistSearchContents<*>>>) = when (state) {
        is Status.Loading -> {
        }
        is Status.Success -> {
            viewUpDate(state.data)
        }
        is Status.Failure -> {
        }
        is Status.Non -> {
        }
    }

    // データ反映
    private fun viewUpDate(data: List<ArtistSearchContents<*>>) {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        val groupAdapter = GroupAdapter<ViewHolder>()
        binding.recyclerView.adapter = groupAdapter
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.layoutAnimation = controller

        val clickListener: (View) -> Unit = {}
        val items = data.map { artistSearchContents ->
            when (artistSearchContents) {
                is ArtistSearchContents.Conditions -> {
                    ResultArtistHeaderItem(artistSearchContents.value, requireContext(), clickListener)
                }
                is ArtistSearchContents.Item -> {
                    ResultArtistBodyItem(artistSearchContents.value, requireContext(), viewLifecycleOwner, resultViewModel)
                }
            }
        }
        groupAdapter.update(items)
    }
}
