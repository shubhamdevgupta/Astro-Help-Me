package com.androiddev.astrohelpme.ui.fragment.register_astrologer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.registerResponse.RegisterAstroResponse
import com.androiddev.astrohelpme.databinding.FragmentRegisterAstrologerBinding
import com.androiddev.astrohelpme.ui.dialog.AppDialog
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.register_astro.RegisterAstroViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterAstrologerFragment :
    BaseFragment<FragmentRegisterAstrologerBinding>(R.layout.fragment_register_astrologer) {
    private val viewModel: RegisterAstroViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnRadio.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMale -> viewModel.gender = "male"
                R.id.rbFemale -> viewModel.gender = "female"
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.registerAstroResponseObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                }

                is Resource.Success -> {
                    viewModel.isLoading.value = false
                    OnSuccessResponse(it.data)
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })
    }

    private fun OnSuccessResponse(data: RegisterAstroResponse) {
        if (data.success == true) {
            AppDialog.success(requireActivity(), data.message, true)
            viewModel.clearFields()
        } else {
            AppDialog.failed(requireActivity(), data.message, true)
        }

    }

}