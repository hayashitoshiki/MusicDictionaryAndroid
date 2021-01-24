package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentResultRecommendBinding
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.adapter.ResultAdapter
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * おすすめアーティスト検索結果画面
 *
 */
class ResultRecommendFragment : Fragment() {

    companion object {
        private const val TAG = "ResultRecommendFragment"
    }

    private val viewModel: ResultRecommendViewModel by viewModel()
    private lateinit var binding: FragmentResultRecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_recommend, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        is Status.Failure -> {
            Log.i(TAG, "Failure:${state.throwable}")
            hideProgressbar()
        }
        is Status.Non -> { }
    }

    // データ反映
    private fun viewUpDate(data: ArrayList<ArtistsForm>) {
        val adapter = ResultAdapter(requireContext(), data)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
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
