package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentResultRecommendBinding
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.adapter.CustomBaseAdapter
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * おすすめアーティスト検索結果画面
 *
 */
class ResultRecommendFragment : Fragment() {

    private val viewModel: ResultRecommendViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultRecommendBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_recommend, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
        viewModel.getRecommend()
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
        list_view.adapter = CustomBaseAdapter(context, android.R.layout.simple_list_item_1, data)
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
