package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.view.View
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentOtpVerifyBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OtpVerifyFragment : BaseFragment<FragmentOtpVerifyBinding>(R.layout.fragment_otp_verify) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}