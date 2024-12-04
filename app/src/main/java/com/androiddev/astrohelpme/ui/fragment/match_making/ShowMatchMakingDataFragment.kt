package com.androiddev.astrohelpme.ui.fragment.match_making

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.data.response.matchmaking.MatchMakingResponse
import com.androiddev.astrohelpme.databinding.FragmentShowMatchMakingFramentBinding


class ShowMatchMakingDataFragment : Fragment() {

    private var _binding: FragmentShowMatchMakingFramentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchingResponse: MatchMakingResponse? =
            arguments?.getSerializable("matchingResponse") as? MatchMakingResponse

        binding.tvMaleManglikStatus.text = matchingResponse?.male?.manglik_status
        binding.tvMalePresentRuleAspect.text =
            matchingResponse?.male?.manglik_present_rule?.based_on_aspect.toString()
        binding.tvMalePresentRuleHouse.text =
            matchingResponse?.male?.manglik_present_rule?.based_on_house.toString()
        binding.tvMaleManglikReport.text = matchingResponse?.male?.manglik_report



        binding.tvFemaleManglikStatus.text = matchingResponse?.female?.manglik_status
        binding.tvFemalePresentRuleAspect.text =
            matchingResponse?.female?.manglik_present_rule?.based_on_aspect.toString()
        binding.tvFemalePresentRuleHouse.text =
            matchingResponse?.female?.manglik_present_rule?.based_on_house.toString()
        binding.tvFemaleManglikReport.text = matchingResponse?.female?.manglik_report

        binding.tvConclusionMatch.text = matchingResponse?.conclusion?.match.toString()
        binding.tvConclusionReport.text = matchingResponse?.conclusion?.report.toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowMatchMakingFramentBinding.inflate(inflater, container, false)
        return binding.root
    }


}