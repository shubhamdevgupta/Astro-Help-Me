package com.androiddev.astrohelpme.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.data.response.LoginResponse
import com.androiddev.astrohelpme.databinding.FragmentLoginBinding
import com.androiddev.astrohelpme.ui.activity.DashboardActivity
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.LoginViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment :
    BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var appPreference: AppPreference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val registerText = binding.registerText

        registerText.setOnClickListener {
            findNavController(it).navigate(R.id.action_signInFragment_to_signupFragment)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                }

                is Resource.Success -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                    viewModel.isLoading.value = false
                    OnSuccessResponse(it.data)
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })

    }

    private fun OnSuccessResponse(data: LoginResponse) {
        if (data.status_code == 200) {
            appPreference.setLoggedIn(true)
            val intent = Intent(requireContext(), DashboardActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }
}