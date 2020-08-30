package com.example.musicdictionaryandroid.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.adapter.SettingBaseAdapter
import kotlinx.android.synthetic.main.fragment_mypage_artist_list.*
import kotlinx.android.synthetic.main.fragment_result.no_data_text
import kotlinx.android.synthetic.main.fragment_result.progressBar

class MyPageArtistFragment : Fragment() {

    private val viewModel: MyPageArtistViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MyPageArtistViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_mypage_artist_list, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
        viewModel.getArtistsByEmail()

        artist_add_button.setOnClickListener {
            val action = MyPageArtistFragmentDirections.actionNavigationMypageArtistToMyPageArtistAddFragment(null)
            findNavController().navigate(action)
        }

        // リストビューの各項目タップ
        artist_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Log.d("Setting", "押されたボタン：" + view.id)
            val artist = (viewModel.status.value as Status.Success).data?.get(position)!!
            when (view.id) {
                R.id.update_button -> {
                    val action = MyPageArtistFragmentDirections.actionNavigationMypageArtistToMyPageArtistAddFragment(artist)
                    findNavController().navigate(action)
                }
                R.id.delete_button -> {
                    viewModel.deleteArtist(artist)
                }
            }
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<ArrayList<ArtistsForm>?>) = when (state) {
        is Status.Loading -> { showProgressbar() }
        is Status.Success -> {
            hideProgressbar()
            hideNoDataView()
            state.data?.let {
                if (it.size == 0) showNoDataView()
                else viewUpDate(state.data)
            } ?: run {
                showNoDataView()
            }
        }
        is Status.Failure -> { hideProgressbar() }
    }

    // データ反映
    private fun viewUpDate(data: ArrayList<ArtistsForm>) {
        artist_list.adapter = SettingBaseAdapter(context, data)
    }

    // 一致データなし表示
    private fun showNoDataView() {
        no_data_text.visibility = View.VISIBLE
    }

    // 一致データなし非表示
    private fun hideNoDataView() {
        no_data_text.visibility = View.INVISIBLE
    }

    // プログレスバー表示
    private fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    // プログレスバー非表示
    private fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }
}
