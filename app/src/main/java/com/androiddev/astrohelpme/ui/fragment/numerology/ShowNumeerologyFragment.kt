package com.androiddev.astrohelpme.ui.fragment.numerology

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.data.response.numerology.NumerologyResponse
import com.androiddev.astrohelpme.databinding.FragmentShowNumeerologyBinding
import com.androiddev.astrohelpme.ui.activity.DashboardActivity

class ShowNumeerologyFragment : Fragment() {

    private var _binding: FragmentShowNumeerologyBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numerlogyResponse: NumerologyResponse? =
            arguments?.getSerializable("numerlogyresponse") as? NumerologyResponse
        Log.d("MYTAG", "onViewCreated: kundliResponse-----" + numerlogyResponse)

        binding.nameTextView.text = numerlogyResponse?.name
        binding.dateTextView.text = numerlogyResponse?.date
        binding.destinyTextView.text = numerlogyResponse?.destiny_number.toString()
        binding.radicalTextView.text = numerlogyResponse?.radical_number.toString()
        binding.nameNumberTextView.text = numerlogyResponse?.name_number.toString()
        binding.evilNumberTextView.text = numerlogyResponse?.evil_num
        binding.favColorTextView.text = numerlogyResponse?.fav_color
        binding.favDayTextView.text = numerlogyResponse?.fav_day
        binding.favGodTextView.text = numerlogyResponse?.fav_god
        binding.favMantraTextView.text = numerlogyResponse?.fav_mantra
        binding.favMetalTextView.text = numerlogyResponse?.fav_metal
        binding.favStoneTextView.text = numerlogyResponse?.fav_stone.toString()
        binding.favSubStoneTextView.text = numerlogyResponse?.fav_substone
        binding.friendlyNumberTextView.text = numerlogyResponse?.friendly_num
        binding.neutralNumberTextView.text = numerlogyResponse?.neutral_num
        binding.radicalNumberTextView.text = numerlogyResponse?.radical_number.toString()
        binding.radicalRulerTextView.text = numerlogyResponse?.radical_ruler

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
        _binding = FragmentShowNumeerologyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}