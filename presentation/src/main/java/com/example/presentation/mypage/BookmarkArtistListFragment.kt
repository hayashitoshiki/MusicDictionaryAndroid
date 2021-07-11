package com.example.presentation.mypage

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
import com.example.presentation.databinding.FragmentBookmarkArtistListBinding
import com.example.presentation.home.ResultAdapterViewModel
import com.example.presentation.home.ResultArtistBodyItem
import com.example.presentation.home.ResultArtistHeaderItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.koin.android.viewmodel.ext.android.viewModel

class BookmarkArtistListFragment : Fragment() {

    private val viewModel: BookmarkArtistListViewModel by viewModel()
    private val resultViewModel: ResultAdapterViewModel by viewModel()
    private lateinit var binding: FragmentBookmarkArtistListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark_artist_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.bookmarkArtistList.observe(viewLifecycleOwner, { viewUpDate(it) })
    }

    // データ反映
    private fun viewUpDate(data: List<ArtistSearchContents<*>>) {
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
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
