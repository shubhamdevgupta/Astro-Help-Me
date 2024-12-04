package com.androiddev.astrohelpme.ui.fragment.show_kundli

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.KundliResponse
import com.androiddev.astrohelpme.databinding.FragmentShowKundliBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.show_kundli.ShowKundliViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class ShowKundliDataFragment :
    BaseFragment<FragmentShowKundliBinding>(R.layout.fragment_show_kundli) {
    private val viewModel: ShowKundliViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.etDateOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                binding.etDateOfBirth.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            }, year, month, day).show()

            viewModel.day = day
            viewModel.month = month
            viewModel.year = year
        }

        binding.etTimeOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                binding.etTimeOfBirth.setText("$selectedHour:$selectedMinute")
            }, hour, minute, true).show()

            viewModel.hour = hour
            viewModel.min = minute
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.kundliResponseObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Log.d("MYTAG", "subscriberObservers: progresssss")
                }

                is Resource.Success -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                    showKundliFragment(it.data)
                }

                is Resource.Failure -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.exception)
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })
    }

    private fun showKundliFragment(data: KundliResponse) {

        val bundle = Bundle().apply {
            putSerializable("kundliresponse", data)
        }
        val kundliDataFragment = KundliDataRequestFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, kundliDataFragment)
            .addToBackStack(null)
            .commit()

    }



}