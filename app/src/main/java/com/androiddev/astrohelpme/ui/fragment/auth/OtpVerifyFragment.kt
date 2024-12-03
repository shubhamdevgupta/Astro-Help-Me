package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.OtpVerifyResponse
import com.androiddev.astrohelpme.databinding.FragmentOtpVerifyBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.auth.OtpVerifyViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OtpVerifyFragment : BaseFragment<FragmentOtpVerifyBinding>(R.layout.fragment_otp_verify) {
    private val viewModel: OtpVerifyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val mobileNumber = arguments?.getString("mobile")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()

    }

    private fun subscriberObservers() {
        viewModel.otpVerifyResponseObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                    Log.d("MYTAG", "subscriberObservers: progresssss")
                }

                is Resource.Success -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                    viewModel.isLoading.value = false
                    onValidateRespone(it.data)
                    Toast.makeText(context, it.data.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })

    }

    private fun onValidateRespone(data: OtpVerifyResponse) {
        if (data.status_code == 200) {
            findNavController().navigate(R.id.action_otpVerifyFragment_to_createPasswordFragment)
        }
    }
}