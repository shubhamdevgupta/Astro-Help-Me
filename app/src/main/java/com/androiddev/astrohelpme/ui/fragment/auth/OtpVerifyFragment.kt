package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.OtpVerifyResponse
import com.androiddev.astrohelpme.databinding.FragmentOtpVerifyBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.OtpVerifyViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OtpVerifyFragment : BaseFragment<FragmentOtpVerifyBinding>(R.layout.fragment_otp_verify) {
    private val viewModel: OtpVerifyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userID = arguments?.getString("user_id")
        val mobileNumber = arguments?.getString("mobile")

        viewModel.mobileNumber = mobileNumber.toString()
        viewModel.userId = userID.toString()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()

    }

    private fun subscriberObservers() {
        viewModel.otpVerifyResponseObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Log.d("MYTAG", "subscriberObservers: progresssss")
                }

                is Resource.Success -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.data)

                    //  setVisibilityWithAlpha(true)
                    onValidateRespone(it.data)
                }

                is Resource.Failure -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.exception)

                }
            }
        })

    }

    private fun onValidateRespone(data: OtpVerifyResponse) {


    }
}