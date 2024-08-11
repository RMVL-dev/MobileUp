package com.example.edu.mobileup.ui.coinsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.edu.mobileup.databinding.FragmentCoinsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsList : Fragment() {

    private var _binding:FragmentCoinsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CoinsListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareClickListeners()
        viewModel.getCoinsList("usd")
    }

    private fun prepareClickListeners(){
        binding.btTest.setOnClickListener {
            findNavController().navigate(
                CoinsListDirections.actionCoinsListToCoinInfo()
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}