package com.example.musicdictionaryandroid.ui.login


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.musicdictionaryandroid.R
import com.example.musicdictionaryandroid.databinding.FragmentSignUpBinding
import com.example.musicdictionaryandroid.model.util.Status
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(SignUpViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.viewModel = viewModel

        // editTextフォーカス制御
        binding.root.email_edit_text.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.password_edit_text.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.name_edit_text.setOnFocusChangeListener { v, hasFocus ->
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner, Observer { onStateChanged(it) })
    }


    // ステータス監視
    private fun onStateChanged(state: Status<String?>) = when (state) {
        is Status.Loading -> {}
        is Status.Success -> {
            state.data?.let{ when(it) {
                    "error1", "error2", "error3", "error4", "error5", "error6" -> (activity as StartActivity).showErrorEmailPassword()
                    else -> (activity as StartActivity).startApp()
            } }?: run {
                (activity as StartActivity).showErrorEmailPassword()
            }
        }
        is Status.Failure -> { (activity as StartActivity).showErrorEmailPassword() }
    }

    companion object {

        @JvmStatic
        fun newInstance(): SignUpFragment {
            val fragment = SignUpFragment()
            return fragment
        }
    }

}
