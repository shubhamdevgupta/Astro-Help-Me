package com.androiddev.astrohelpme.ui.fragment.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentCameraBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraFragment : BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }


}