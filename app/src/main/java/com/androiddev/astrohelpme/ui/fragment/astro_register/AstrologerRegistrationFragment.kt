package com.androiddev.astrohelpme.ui.fragment.astro_register

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentAstrologerRegistrationBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import java.util.Calendar

class AstrologerRegistrationFragment :
    BaseFragment<FragmentAstrologerRegistrationBinding>(R.layout.fragment_astrologer_registration) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etDateOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                binding.etDateOfBirth.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            }, year, month, day).show()
        }

        binding.etTimeOfBirth.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                binding.etTimeOfBirth.setText("$selectedHour:$selectedMinute")
            }, hour, minute, true).show()
        }
    }

}