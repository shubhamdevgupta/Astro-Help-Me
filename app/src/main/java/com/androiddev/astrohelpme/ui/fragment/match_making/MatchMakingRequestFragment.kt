package com.androiddev.astrohelpme.ui.fragment.match_making

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.matchmaking.MatchMakingResponse
import com.androiddev.astrohelpme.databinding.FragmentMatchMakingBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.matchmaking.MatchMakingiViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MatchMakingRequestFragment :
    BaseFragment<FragmentMatchMakingBinding>(R.layout.fragment_match_making) {
    private val viewModel: MatchMakingiViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnMale.setOnClickListener {
            binding.llFeMaleLayout.visibility = View.GONE
            binding.llMaleLayout.visibility = View.VISIBLE
        }
        binding.btnFemale.setOnClickListener {
            binding.llFeMaleLayout.visibility = View.VISIBLE
            binding.llMaleLayout.visibility = View.GONE
        }

        binding.icBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.etDateOfBirthMale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                binding.etDateOfBirthMale.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                viewModel.m_day = selectedDay
                viewModel.m_month = selectedMonth+1
                viewModel.m_year = selectedYear
            }, year, month, day).show()

        }

        binding.etDateOfBirthFemale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                binding.etDateOfBirthFemale.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                viewModel.f_day = selectedDay
                viewModel.f_month = selectedMonth
                viewModel.f_year = selectedYear
            }, year, month, day).show()

        }

        binding.etTimeOfBirthMale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                binding.etTimeOfBirthMale.setText(String.format("%02d:%02d", selectedHour, selectedMinute))

                Log.d("MYTAG", "onViewCreated: time after select $selectedHour:$selectedMinute")

                // Update ViewModel with the selected time
                viewModel.m_hour = selectedHour
                viewModel.m_min = selectedMinute
            }, hour, minute, true).show()
        }

        binding.etTimeOfBirthFemale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                binding.etTimeOfBirthFemale.setText(String.format("%02d:%02d", selectedHour, selectedMinute))
                Log.d("MYTAG", "onViewCreated: time after select $selectedHour:$selectedMinute")

                // Update ViewModel with the selected time
                viewModel.f_hour = selectedHour
                viewModel.f_min = selectedMinute
            }, hour, minute, true).show()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()

    }

    private fun subscriberObservers() {
        viewModel.matchMakingObserver.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                    Log.d("MYTAG", "subscriberObservers: progresssss")
                }

                is Resource.Success -> {
                    viewModel.isLoading.value = false
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                    showMatchMakingFragment(it.data)
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    Log.d("MYTAG", "subscriberObservers: " + it.exception)
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        })
    }

    private fun showMatchMakingFragment(data: MatchMakingResponse) {
        val bundle = Bundle().apply {
            putSerializable("matchingResponse", data)
        }
        val showMatchMakingFrament = ShowMatchMakingDataFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, showMatchMakingFrament)
            .addToBackStack(null)
            .commit()
    }


}