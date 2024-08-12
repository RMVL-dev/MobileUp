package com.example.edu.mobileup.ui.coinInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.edu.mobileup.R
import com.example.edu.mobileup.data.ResponseState
import com.example.edu.mobileup.data.coinInfoData.CoinInfoData
import com.example.edu.mobileup.databinding.FragmentCoinInfoBinding
import com.example.edu.mobileup.ui.helpers.collectFlowWhenStarted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinInfo : Fragment() {

    private var _binding:FragmentCoinInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel:CoinInfoViewModel by viewModels()

    private val args:CoinInfoArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareClickListeners()
        args.id?.let {
            viewModel.getCoin(it)
        }

        collectFlowWhenStarted(viewModel.coin){state ->
            when(state){
                is ResponseState.Error -> {
                    binding.progressIndicator.visibility = View.GONE
                    binding.ilError.root.visibility = View.VISIBLE
                    binding.nsvCoinInfo.visibility = View.GONE
                }
                is ResponseState.Loading -> {
                    binding.progressIndicator.visibility = View.VISIBLE
                    binding.ilError.root.visibility = View.GONE
                    binding.nsvCoinInfo.visibility = View.GONE
                }
                is ResponseState.None -> {
                    binding.progressIndicator.visibility = View.GONE
                    binding.ilError.root.visibility = View.GONE
                    binding.nsvCoinInfo.visibility = View.GONE
                }
                is ResponseState.Success -> {
                    binding.progressIndicator.visibility = View.GONE
                    binding.ilError.root.visibility = View.GONE
                    binding.nsvCoinInfo.visibility = View.VISIBLE
                    prepareInfoScreen(coin = state.data)
                }
            }
        }
    }

    private fun prepareClickListeners() = with(binding){
        ibBack.setOnClickListener { findNavController().popBackStack() }
        ilError.btnRetry.setOnClickListener {
            args.id?.let {
                viewModel.getCoin(it)
            }
        }
    }

    private fun prepareInfoScreen(coin:CoinInfoData)=with(binding){
        Glide.with(ivCoinImage).load(coin.images?.large).into(ivCoinImage)
        tvCoinName.text = coin.name ?: getText(R.string.unknown_coin)
        tvCoinDescriptor.text = coin.description?.en ?: getText(R.string.unknown_descriptor)
        coin.categories?.let {categories ->
            var categoryText = ""
            for (category in categories)
                categoryText = if (categories.indexOf(category)==0)
                    category
                else
                    "$categoryText, $category"
            tvCoinCategory.text = categoryText
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}