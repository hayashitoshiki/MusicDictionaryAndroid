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
import com.example.musicdictionaryandroid.databinding.FragmentSignInBinding
import com.example.musicdictionaryandroid.model.util.Status
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * ログイン画面
 */
class SignInFragment : Fragment() {

    companion object {
        const val TAG = "SignInFragment"

        @JvmStatic
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSignInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
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
    private fun onStateChanged(state: Status<Boolean>) = when (state) {
        is Status.Loading -> {}
        is Status.Success -> {
            if (state.data) (activity as StartActivity).startApp()
            else (activity as StartActivity).showErrorEmailPassword() }
        is Status.Failure -> {
             Log.i(TAG, "Failure:${state.throwable}")
            (activity as StartActivity).showErrorEmailPassword() }
    }
}
