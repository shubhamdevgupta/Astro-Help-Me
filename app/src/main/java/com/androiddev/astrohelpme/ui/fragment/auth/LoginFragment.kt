package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentLoginBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val registerText = binding.registerText

        registerText.setOnClickListener {
            findNavController(it).navigate(R.id.action_signInFragment_to_signupFragment)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}