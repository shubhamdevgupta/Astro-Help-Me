package com.androiddev.astrohelpme.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentDashboardBinding
import com.androiddev.astrohelpme.ui.activity.DashboardActivity
import com.androiddev.astrohelpme.ui.fragment.match_making.MatchMakingRequestFragment
import com.androiddev.astrohelpme.ui.fragment.numerology.NumerologyFragment
import com.androiddev.astrohelpme.ui.fragment.panchang.PanchangFragment
import com.androiddev.astrohelpme.ui.fragment.show_kundli.ShowKundliDataFragment
import com.androiddev.astrohelpme.utils.helper.LocaleHelper.setLocale


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

        binding.icHoroscop.setOnClickListener {
            comingSoon()
        }
        binding.icTalktoAstrologer.setOnClickListener {

        }
        binding.icSwastik.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, PanchangFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.icAskquestion.setOnClickListener {
            comingSoon()
        }
        binding.icDhruv.setOnClickListener {
            comingSoon()
        }
        binding.icBhirat.setOnClickListener {
            comingSoon()
        }

        binding.icNumerology.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, NumerologyFragment())
                .addToBackStack(null)
                .commit()
        }


        binding.icAstroshop.setOnClickListener {
          comingSoon()
        }
        binding.icMagazine.setOnClickListener {
            comingSoon()
        }
        binding.icKpsystem.setOnClickListener {
            comingSoon()
        }
        binding.icLalKitab.setOnClickListener {
            comingSoon()
        }
        binding.icVarshapal.setOnClickListener {
            comingSoon()
        }
        binding.icLearnastrology.setOnClickListener {
            comingSoon()
        }
        binding.icPorutham.setOnClickListener {
            comingSoon()
        }
        binding.icFestival.setOnClickListener {
            comingSoon()
        }
        binding.icMiscellions.setOnClickListener {
            comingSoon()
        }
        binding.icDasha.setOnClickListener {
            comingSoon()
        }
        binding.icCalendar.setOnClickListener {
            comingSoon()
        }

        val newContext = setLocale(requireContext(), "hi")
        val resources = newContext?.resources

        if (resources != null) {
            binding.tvFooter.text = resources.getString(R.string.share_app_with_friends)
        }


        binding.icDrawer.setOnClickListener {
            (activity as DashboardActivity).openNavigationDrawer()
        }
    }
    fun comingSoon(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ComingSoonFragment())
            .addToBackStack(null)
            .commit()
    }

}