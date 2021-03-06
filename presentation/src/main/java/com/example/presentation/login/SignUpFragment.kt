package com.example.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignUpBinding
import com.example.presentation.util.MessageUtilImp
import com.example.presentation.util.Status
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * 新規登録画面
 */
class SignUpFragment : Fragment() {

    companion object {

        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    private val viewModel: SignUpViewModel by inject { parametersOf(MessageUtilImp(requireContext())) }
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.status.observe(viewLifecycleOwner, { onStateChanged(it) })

        // spinner 設定
        binding.areaSpinner.let { spinner ->
            val arrayAdapter = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.area_spinner_list)
            )
            spinner.setAdapter(arrayAdapter)
            spinner.keyListener = null
            spinner.setOnItemClickListener { _, _, position, _ ->
                spinner.showDropDown()
                viewModel.areaSelectedPosition.value = position
            }
        }
        binding.birthdaySpinner.let { spinner ->
            val arrayAdapter = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_dropdown_item_1line,
                resources.getStringArray(R.array.birthday_spinner_list)
            )
            spinner.setAdapter(arrayAdapter)
            spinner.keyListener = null
            spinner.setOnItemClickListener { _, _, position, _ ->
                spinner.showDropDown()
                viewModel.birthdaySelectedPosition.value = position
            }
        }
        // editTextフォーカス制御
        binding.emailEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                viewModel.focusChangeEmail()
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.passwordEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                viewModel.focusChangePassword1()
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.password2EditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                viewModel.focusChangePassword2()
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.nameEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                viewModel.focusChangeName()
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        binding.root.setOnTouchListener { v, event ->
            binding.root.requestFocus()
            v?.onTouchEvent(event) ?: true
        }
    }

    // ステータス監視
    private fun onStateChanged(state: Status<String?>) = when (state) {
        is Status.Loading -> {
            (activity as StartActivity).loading()
        }
        is Status.Success -> {
            (activity as StartActivity).endLoading()
            (activity as StartActivity).startApp()
        }
        is Status.Failure -> {
            (activity as StartActivity).endLoading()
            (activity as StartActivity).showErrorEmailPassword()
        }
        is Status.Non -> {
        }
    }
}
