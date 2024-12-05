package com.androiddev.astrohelpme.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentDashboardBinding
import com.androiddev.astrohelpme.ui.fragment.match_making.MatchMakingRequestFragment
import com.androiddev.astrohelpme.ui.fragment.numerology.NumerologyFragment
import com.androiddev.astrohelpme.ui.fragment.panchang.PanchangFragment
import com.androiddev.astrohelpme.ui.fragment.show_kundli.ShowKundliDataFragment


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icKundli.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ShowKundliDataFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.icMatchMaking.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, MatchMakingRequestFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.icNumerology.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, NumerologyFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.icSwastik.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, PanchangFragment())
                .addToBackStack(null)
                .commit()
        }


    }

}