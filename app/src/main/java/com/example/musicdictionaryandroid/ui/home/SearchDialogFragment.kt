package com.example.musicdictionaryandroid.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.DialogSearchBinding
import com.example.musicdictionaryandroid.model.entity.ArtistsForm
import com.example.musicdictionaryandroid.ui.adapter.DialogFragmentCallbackInterface
import com.example.musicdictionaryandroid.ui.adapter.setSafeClickListener

/**
 * 検索条件ダイアログ
 *
 */
class SearchDialogFragment : DialogFragment() {

    private var mListener: DialogFragmentCallbackInterface? = null

    private lateinit var binding: DialogSearchBinding
    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init(
            resources.getStringArray(R.array.genre1_spinner_list),
            resources.getStringArray(R.array.genre12_spinner_list),
            resources.getStringArray(R.array.genre22_spinner_list),
            resources.getStringArray(R.array.genre32_spinner_list),
            resources.getStringArray(R.array.genre42_spinner_list),
            resources.getStringArray(R.array.genre52_spinner_list),
            resources.getStringArray(R.array.genre62_spinner_list)
        )
        arguments?.let {
            val artist = it.getSerializable("artist") as ArtistsForm
            viewModel.setArtist(artist)
        }

        viewModel.genre1ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre1(it) })
        viewModel.genre2ValueInt.observe(viewLifecycleOwner, Observer { viewModel.changeGenre2(it) })
        dialog!!.window!!.setBackgroundDrawableResource(R.color.transparent)

        // editTextフォーカス制御
        binding.artistNameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.setOnTouchListener { v, event ->
            binding.root.requestFocus()
            v?.onTouchEvent(event) ?: true
        }
        // 検索ボタン
        binding.submit.setSafeClickListener {
            mListener!!.callBackMethod(viewModel.artistForm)
            dismiss()
        }
    }

    fun setCallbackListener(listener: DialogFragmentCallbackInterface) {
        mListener = listener
    }
}
