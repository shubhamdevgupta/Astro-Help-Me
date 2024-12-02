package com.androiddev.astrohelpme.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androiddev.astrohelpme.R

class GetStartedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_get_started, container, false)

        val btnGoToDashboard: Button = view.findViewById(R.id.btnStart)
        btnGoToDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_getStartedFragment_to_signInFragment)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}