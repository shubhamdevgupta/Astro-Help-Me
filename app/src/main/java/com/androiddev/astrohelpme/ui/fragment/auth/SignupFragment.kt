package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.SignupResponse
import com.androiddev.astrohelpme.databinding.FragmentSignupBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.SignUpViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {
    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val loginText = binding.tvLogin
        loginText.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_signInFragment)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.signupResponseObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                }

                is Resource.Success -> {
                    viewModel.isLoading.value = false
                    onSignUpResponse(it.data)
                    Toast.makeText(context, "Your otp is " + it.data.otp, Toast.LENGTH_LONG).show()
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })

    }

    private fun onSignUpResponse(data: SignupResponse) {
        if (data.status_code == 200) {
            val userId = data.user_id.toString()

            val bundle = Bundle().apply {
                putString("user_id", userId)
                putString("mobile", viewModel.mobileNumber)
            }
            findNavController().navigate(R.id.otpVerifyFragment, bundle)

        }
    }

}