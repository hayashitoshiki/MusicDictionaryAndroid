package com.example.musicdictionaryandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.model.util.Status
import com.example.musicdictionaryandroid.ui.adapter.DialogFragmentCallbackInterface
import com.example.musicdictionaryandroid.ui.adapter.ResultAdapter
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 検索結果画面
 *
 */
class ResultFragment : Fragment(), DialogFragmentCallbackInterface {

    private val args: ResultFragmentArgs by navArgs()
    private val viewModel: ResultViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })

        if (savedInstanceState != null) {
            val artistsFrom = savedInstanceState.getSerializable("artistSave") as ArtistsForm
            viewModel.getArtists(artistsFrom)
        } else {
            viewModel.getArtists(args.data)
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
        val adapter = ResultAdapter(requireContext(), data)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        recyclerView.layoutAnimation = controller
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        adapter.setOnItemClickListener(View.OnClickListener { view ->
            val dialogFragment = SearchDialogFragment()
            val bundle = Bundle()
            bundle.putSerializable("artist", args.data)
            dialogFragment.arguments = bundle
            dialogFragment.setCallbackListener(this)
            dialogFragment.show(requireActivity().supportFragmentManager, null)
        })
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("artistSave", args.data)
    }

    // ダイアログコールバック
    override fun callBackMethod(data: ArtistsForm) {
        viewModel.getArtists(data)
    }
}
