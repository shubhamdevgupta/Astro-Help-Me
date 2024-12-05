package com.androiddev.astrohelpme.ui.fragment.numerology

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.response.numerology.NumerologyResponse
import com.androiddev.astrohelpme.databinding.FragmentNumerologyBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.numerlogy.NumerlogyViewModel
import com.androiddev.astrohelpme.utils.api.Resource
import com.androiddev.astrohelpme.utils.extns.handleNetworkFailure
import com.androiddev.astrohelpme.utils.extns.makeToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class NumerologyFragment : BaseFragment<FragmentNumerologyBinding>(R.layout.fragment_numerology) {
    private val viewModel: NumerlogyViewModel by viewModels()

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
                viewModel.day = selectedDay
                viewModel.month = selectedMonth
                viewModel.year = selectedYear
            }, year, month, day).show()


        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscriberObservers()
    }

    private fun subscriberObservers() {
        viewModel.numerlogyResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    viewModel.isLoading.value = true
                }

                is Resource.Success -> {
                    viewModel.isLoading.value = false
                    OnSuccessResponse(it.data)
                }

                is Resource.Failure -> {
                    viewModel.isLoading.value = false
                    activity?.handleNetworkFailure(it.exception)
                    activity?.makeToast(it.exception.message.toString())
                }
            }
        }
    }

    private fun OnSuccessResponse(data: NumerologyResponse) {
        val bundle = Bundle().apply {
            putSerializable("numerlogyresponse", data)
        }
        val showNumberFragment = ShowNumeerologyFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, showNumberFragment)
            .addToBackStack(null)
            .commit()
    }

}