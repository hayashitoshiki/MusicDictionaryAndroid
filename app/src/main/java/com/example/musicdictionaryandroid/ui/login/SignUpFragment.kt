package com.example.musicdictionaryandroid.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentSignUpBinding
import com.example.musicdictionaryandroid.model.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * 新規登録画面
 */
class SignUpFragment : Fragment() {

    companion object {
        const val TAG = "SignUpFragment"

        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // editTextフォーカス制御
        binding.emailEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.passwordEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.nameEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.setOnTouchListener { v, event ->
            binding.root.requestFocus()
            v?.onTouchEvent(event) ?: true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
    }

    // ステータス監視
    private fun onStateChanged(state: Status<String?>) = when (state) {
        is Status.Loading -> {}
        is Status.Success -> { (activity as StartActivity).startApp() }
        is Status.Failure -> {
            Log.i(TAG, "Failure:${state.throwable}")
            (activity as StartActivity).showErrorEmailPassword()
        }
    }
}
