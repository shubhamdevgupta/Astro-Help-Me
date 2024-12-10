package com.androiddev.astrohelpme.ui.fragment.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.databinding.FragmentChatBinding
import com.androiddev.astrohelpme.ui.fragment.BaseFragment
import com.androiddev.astrohelpme.ui.viewmodel.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {
    private val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}