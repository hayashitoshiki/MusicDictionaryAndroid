package com.example.musicdictionaryandroid.ui.mypage

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentMypageArtistListBinding
import com.example.musicdictionaryandroid.data.database.entity.ArtistEntity
import com.example.musicdictionaryandroid.data.net.dto.ArtistsDto
import com.example.musicdictionaryandroid.data.util.Status
import com.example.musicdictionaryandroid.domain.model.entity.Artist
import com.example.musicdictionaryandroid.ui.adapter.SettingBaseAdapter
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 登録済みアーティスト一覧画面
 */
class MyPageArtistFragment : Fragment(), CoroutineScope {

    companion object {
        const val TAG = "MyPageArtistFragment"
    }

    private val viewModel: MyPageArtistViewModel by viewModel()
    private lateinit var binding: FragmentMypageArtistListBinding
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage_artist_list, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_offset_300_anim)
        val transition = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
        }
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
        binding.artistAddButton.startAnimation(anim)
        binding.artistList.startAnimation(anim)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
        viewModel.artistEntityList.observe(viewLifecycleOwner, Observer { viewUpDate(it) })

        // アーティスト追加ボタン
        binding.artistAddButton.setOnClickListener {
            val action = MyPageArtistFragmentDirections.actionNavigationMypageArtistToMyPageArtistAddFragment(null)
            val extras = FragmentNavigatorExtras(it to "end_artist_add_view_transition")
            findNavController().navigate(action, extras)
        }
        // リストビューの各項目タップ
        binding.artistList.adapter = SettingBaseAdapter(context, listOf())
        binding.artistList.onItemClickListener = AdapterView.OnItemClickListener { _, v, position, _ ->
            val artist = viewModel.artistEntityList.value!![position]
            when (v.id) {
                R.id.update_button -> {
                    val action =
                        MyPageArtistFragmentDirections.actionNavigationMypageArtistToMyPageArtistAddFragment(
                            artist
                        )
                    findNavController().navigate(action)
                }
                R.id.delete_button -> { viewModel.deleteArtist(artist) }
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    // ステータス監視
    private fun onStateChanged(state: Status<List<Artist>?>) = when (state) {
        is Status.Loading -> {
            hideNoDataView()
            showProgressbar()
        }
        is Status.Success -> { hideProgressbar() }
        is Status.Failure -> {
            Log.e(TAG, "Failure:${state.throwable}")
            hideProgressbar()
        }
        is Status.Non -> { hideNoDataView() }
    }

    // データ反映
    private fun viewUpDate(data: List<Artist>) {
        launch {
            val adapter = binding.artistList.adapter as SettingBaseAdapter
            adapter.setData(data)
            adapter.notifyDataSetChanged()
            if (data.isNullOrEmpty()) {
                showNoDataView()
            }
        }
    }

    // 一致データなし表示
    private fun showNoDataView() {
        binding.noDataText.visibility = View.VISIBLE
    }

    // 一致データなし非表示
    private fun hideNoDataView() {
        binding.noDataText.visibility = View.INVISIBLE
    }

    // プログレスバー表示
    private fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    // プログレスバー非表示
    private fun hideProgressbar() {
        binding.progressBar.visibility = View.GONE
    }
}
