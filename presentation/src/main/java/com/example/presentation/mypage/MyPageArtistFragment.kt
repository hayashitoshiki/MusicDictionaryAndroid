package com.example.presentation.mypage

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.domain.model.entity.Artist
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMypageArtistListBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 登録済みアーティスト一覧画面
 */
class MyPageArtistFragment : Fragment() {

    private val viewModel: MyPageArtistViewModel by viewModel()
    private lateinit var binding: FragmentMypageArtistListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
        viewModel.artistList.observe(viewLifecycleOwner, { viewUpDate(it) })

        // アーティスト追加ボタン
        binding.artistAddButton.setOnClickListener {
            val action = MyPageArtistFragmentDirections.actionNavigationMypageArtistToMyPageArtistAddFragment(null)
            val extras = FragmentNavigatorExtras(it to "end_artist_add_view_transition")
            findNavController().navigate(action, extras)
        }
        // リストビューの各項目タップ
        binding.artistList.adapter = SettingBaseAdapter(context, listOf())
        binding.artistList.onItemClickListener = AdapterView.OnItemClickListener { _, v, position, _ ->
            val artist = viewModel.artistList.value!![position]
            when (v.id) {
                R.id.update_button -> {
                    val action =
                        MyPageArtistFragmentDirections.actionNavigationMypageArtistToMyPageArtistAddFragment(
                            artist
                        )
                    findNavController().navigate(action)
                }
                R.id.delete_button -> {
                    viewModel.deleteArtist(artist)
                }
            }
        }
    }

    // データ反映
    private fun viewUpDate(data: List<Artist>) {
        val adapter = binding.artistList.adapter as SettingBaseAdapter
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }
}
