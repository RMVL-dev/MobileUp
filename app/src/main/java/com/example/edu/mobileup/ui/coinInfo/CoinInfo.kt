package com.example.edu.mobileup.ui.coinInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.edu.mobileup.databinding.FragmentCoinInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinInfo : Fragment() {

    private var _binding:FragmentCoinInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel:CoinInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCoin("bitcoin")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}