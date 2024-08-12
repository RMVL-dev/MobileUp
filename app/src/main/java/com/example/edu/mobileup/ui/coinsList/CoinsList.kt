package com.example.edu.mobileup.ui.coinsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.edu.mobileup.data.ResponseState
import com.example.edu.mobileup.databinding.FragmentCoinsListBinding
import com.example.edu.mobileup.ui.coinsList.adapter.CoinsListAdapter
import com.example.edu.mobileup.ui.helpers.collectFlowWhenStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsList : Fragment() {

    private var _binding:FragmentCoinsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CoinsListViewModel>()
    private val coinsAdapter = CoinsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (binding.chipRub.isChecked) getRUB() else getUSD()
        prepareClickListeners()
        binding.rvCoins.adapter = coinsAdapter

        collectFlowWhenStarted(viewModel.coinsLIst){state ->
            when(state){
                is ResponseState.Error -> {
                    binding.progressIndicator.visibility = View.GONE
                    binding.rvCoins.visibility = View.GONE
                    binding.ilError.root.visibility = View.VISIBLE
                }
                is ResponseState.Loading -> {
                    binding.progressIndicator.visibility = View.VISIBLE
                    binding.rvCoins.visibility = View.GONE
                    binding.ilError.root.visibility = View.GONE
                }
                is ResponseState.None -> {
                    binding.progressIndicator.visibility = View.GONE
                    binding.rvCoins.visibility = View.GONE
                    binding.ilError.root.visibility = View.GONE
                }
                is ResponseState.Success -> {
                    binding.progressIndicator.visibility = View.GONE
                    coinsAdapter.coinsList = state.data
                    binding.rvCoins.adapter?.notifyDataSetChanged()
                    binding.rvCoins.visibility = View.VISIBLE
                    binding.ilError.root.visibility = View.GONE
                }
            }
        }
    }

    private fun prepareClickListeners() = with(binding){
        cgCurrencyCoice.setOnCheckedStateChangeListener { _, checkedIds ->
            when(checkedIds.first()) {
                chipRub.id -> { getRUB() }
                chipUsd.id -> { getUSD() }
            }
        }
        ilError.btnRetry.setOnClickListener {
            if (binding.chipRub.isChecked) getRUB() else getUSD()
        }
        coinsAdapter.clickListener = {coinId ->
            findNavController().navigate(
                CoinsListDirections.actionCoinsListToCoinInfo(coinId)
            )
        }
    }

    private fun getRUB(){
        viewModel.getCoinsList("rub")
        coinsAdapter.isUSD = false
    }

    private fun getUSD(){
        viewModel.getCoinsList("usd")
        coinsAdapter.isUSD = true
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}