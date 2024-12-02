package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.SetPassResponse
import com.androiddev.astrohelpme.databinding.FragmentSetPassBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.SetPassViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetPasswordFragment : BaseFragment<FragmentSetPassBinding>(R.layout.fragment_set_pass) {
    private val viewModel: SetPassViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()

    }

    private fun subscriberObservers() {
        viewModel.setPassResponseObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                    Log.d("MYTAG", "subscriberObservers: progresssss")
                }

                is Resource.Success -> {
                    viewModel.isLoading.value = false
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                    Toast.makeText(context, it.data.message, Toast.LENGTH_SHORT).show()
                    onSetPassRespone(it.data)
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })

    }

    private fun onSetPassRespone(data: SetPassResponse) {
        Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_createPasswordFragment_to_getStartedFragment)
    }
}