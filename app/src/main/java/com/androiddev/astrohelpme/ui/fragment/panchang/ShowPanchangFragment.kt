package com.androiddev.astrohelpme.ui.fragment.panchang

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.data.response.panchang.PanchangResponse
import com.androiddev.astrohelpme.databinding.FragmentShowPanchangBinding
import com.androiddev.astrohelpme.ui.activity.DashboardActivity


class ShowPanchangFragment : Fragment() {
    private var _binding: FragmentShowPanchangBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val panchangResponse: PanchangResponse? =
            arguments?.getSerializable("panchangresponse") as? PanchangResponse

        Log.d("MYTAG", "onViewCreated: panchang response---->"+panchangResponse)

        binding.dayTextView.text = panchangResponse?.day
        binding.tithiTextView.text = panchangResponse?.tithi
        binding.nakshatraTextView.text = panchangResponse?.nakshatra
        binding.yogTextView.text = panchangResponse?.yog

        binding.karanTextView.text = panchangResponse?.karan
        binding.sunriseTextView.text = panchangResponse?.sunrise
        binding.sunsetTextView.text = panchangResponse?.sunset
        binding.vedicSunriseTextView.text = panchangResponse?.vedic_sunrise
        binding.vedicSunsetTextView.text = panchangResponse?.vedic_sunset

        binding.icSqaa.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowPanchangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}