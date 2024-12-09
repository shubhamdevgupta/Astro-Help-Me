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

                // Pass the selected date to ViewModel
                viewModel.day = selectedDay
                viewModel.month = selectedMonth + 1 // Adjust for zero-based month
                viewModel.year = selectedYear
            }, year, month, day).show()
        }
        binding.icBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.etTimeOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                // Update the EditText with the selected time
                binding.etTimeOfBirth.setText(String.format("%02d:%02d", selectedHour, selectedMinute))

                // Log the selected time
                Log.d("MYTAG", "onViewCreated: time after select $selectedHour:$selectedMinute")

                // Update ViewModel with the selected time
                viewModel.hour = selectedHour
                viewModel.min = selectedMinute
            }, hour, minute, true).show()
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
                    viewModel.isLoading.value = true
                }

                is Resource.Success -> {
                    viewModel.isLoading.value = false
                    showKundliFragment(it.data)
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
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