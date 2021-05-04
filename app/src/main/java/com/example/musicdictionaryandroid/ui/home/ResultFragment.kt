package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentResultBinding
import com.example.musicdictionaryandroid.domain.model.value.ArtistConditions
import com.example.musicdictionaryandroid.domain.model.value.ArtistSearchContents
import com.example.musicdictionaryandroid.ui.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 検索結果画面
 */
class ResultFragment : Fragment(), DialogFragmentCallbackInterface {

    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: ResultViewModel by viewModel()
    private val resultViewModel: ResultAdapterViewModel by viewModel()
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, { onStateChanged(it) })

        if (savedInstanceState != null) {
            val artist = savedInstanceState.getSerializable("artistSave") as ArtistConditions
            viewModel.getArtists(artist)
        } else {
            viewModel.getArtists(args.data)
        }
    }

    // ステータス監視
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
        val adapter = ResultAdapter(resultViewModel, requireContext(), data)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        binding.recyclerView.layoutAnimation = controller
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        adapter.setOnItemClickListener {
            val dialogFragment = SearchDialogFragment()
            val bundle = Bundle()
            bundle.putSerializable("artist", args.data)
            dialogFragment.arguments = bundle
            dialogFragment.setCallbackListener(this)
            dialogFragment.show(requireActivity().supportFragmentManager, null)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("artistSave", args.data)
    }

    // ダイアログコールバック
    override fun callBackMethod(data: ArtistConditions) {
        viewModel.getArtists(data)
    }
}
