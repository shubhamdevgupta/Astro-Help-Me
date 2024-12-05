package com.androiddev.astrohelpme.ui.fragment.show_kundli

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.data.response.KundliResponse
import com.androiddev.astrohelpme.databinding.FragmentKundliDataBinding
import com.androiddev.astrohelpme.ui.activity.AuthActivity
import com.androiddev.astrohelpme.ui.activity.DashboardActivity


class KundliDataRequestFragment : Fragment() {

    private var _binding: FragmentKundliDataBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val kundliResponse: KundliResponse? =
            arguments?.getSerializable("kundliresponse") as? KundliResponse

        Log.d("MYTAG", "onViewCreated: kundliResponse-----" + kundliResponse)

        binding.tvAscendantValue.text = kundliResponse?.ascendant
        binding.tvAscendantLordValue.text = kundliResponse?.ascendant_lord
        binding.tvVarnaValue.text = kundliResponse?.Varna
        binding.tvVashyaValue.text = kundliResponse?.Vashya
        binding.tvYoniValue.text = kundliResponse?.Yoni
        binding.tvGanValue.text = kundliResponse?.Gan
        binding.tvNadiValue.text = kundliResponse?.Nadi
        binding.tvSignLordValue.text = kundliResponse?.SignLord
        binding.tvsignValue.text = kundliResponse?.sign
        binding.tvNaksahtraValue.text = kundliResponse?.Naksahtra
        binding.tvNaksahtraLordValue.text = kundliResponse?.NaksahtraLord
        binding.tvCharanValue.text = kundliResponse?.Charan.toString()
        binding.tvYogValue.text = kundliResponse?.Yog
        binding.tvKaranValue.text = kundliResponse?.Karan
        binding.tvTithiValue.text = kundliResponse?.Tithi
        binding.tvnameAlphabetValue.text = kundliResponse?.name_alphabet
        binding.tvpayaValue.text = kundliResponse?.paya

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
        _binding = FragmentKundliDataBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}