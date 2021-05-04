package com.example.musicdictionaryandroid.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentBookmarkArtistListBinding
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.ui.home.ResultAdapter
import com.example.musicdictionaryandroid.ui.home.ResultAdapterViewModel
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
        val adapter = ResultAdapter(resultViewModel, requireContext(), data)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        binding.recyclerView.layoutAnimation = controller
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }
}
