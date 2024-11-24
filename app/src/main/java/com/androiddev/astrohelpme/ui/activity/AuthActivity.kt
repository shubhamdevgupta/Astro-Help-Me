package com.androiddev.astrohelpme.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.ActivityAuthBinding
import com.androiddev.astrohelpme.ui.viewmodel.AuthViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private  lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.loginResponse.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    Log.d("MYTAG", "onCreate: on loading..")
                }

                is Resource.Success -> {
                    Log.d("MYTAG", "response" + it.data.toString())
                }

                is Resource.Failure -> {
                    Log.d("MYTAG", "response" + it.exception.message.toString())
                }
            }
        }
    }
}