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


        binding.etDateOfBirthMale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                binding.etDateOfBirthMale.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            }, year, month, day).show()

            viewModel.m_day = day
            viewModel.m_month = month
            viewModel.m_year = year
        }

        binding.etDateOfBirthFemale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                binding.etDateOfBirthFemale.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            }, year, month, day).show()

            viewModel.f_day = day
            viewModel.f_month = month
            viewModel.f_year = year
        }

        binding.etTimeOfBirthMale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                binding.etTimeOfBirthMale.setText("$selectedHour:$selectedMinute")
            }, hour, minute, true).show()

            viewModel.m_hour = hour
            viewModel.m_hour = minute
        }

        binding.etTimeOfBirthFemale.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                binding.etTimeOfBirthFemale.setText("$selectedHour:$selectedMinute")
            }, hour, minute, true).show()

            viewModel.f_hour = hour
            viewModel.f_hour = minute
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
                    Log.d("MYTAG", "subscriberObservers: progresssss")
                }

                is Resource.Success -> {
                    Log.d("MYTAG", "subscriberObservers: " + it.data)
                    showMatchMakingFragment(it.data)
                }

                is Resource.Failure -> {
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