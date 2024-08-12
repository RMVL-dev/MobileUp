package com.example.edu.mobileup.ui.coinsList.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edu.mobileup.R
import com.example.edu.mobileup.data.coinListData.CoinsListItem
import com.example.edu.mobileup.databinding.CoinItemBinding
import java.text.DecimalFormat
import kotlin.math.abs

class CoinViewHolder(
    private val binding:CoinItemBinding,
    private val context: Context
): RecyclerView.ViewHolder(binding.root) {

    fun bind(coin:CoinsListItem, isUsd:Boolean) = with(binding){
        tvCoinName.text = coin.name ?: context.getString(R.string.unknown_coin)
        tvCoinSymbol.text = coin.symbol ?: context.getString(R.string.unknown_symbol)
        val formattedPrice = DecimalFormat("#,###.##").format(coin.currentPrice)
        tvCoinPrice.text = if (isUsd) "$ $formattedPrice" else "\u20BD $formattedPrice"

        coin.priceChangePercentage24h?.let {priceChange ->
            tvCoinPriceChangePercentage.setTextColor(
                if (priceChange > 0)
                    context.getColor(R.color.coin_change_price_positive)
                else
                    context.getColor(R.color.coin_change_price_negative)
            )
            val formattedPercentage = DecimalFormat("#0.00").format(abs(priceChange))
            tvCoinPriceChangePercentage.text = if (priceChange > 0)
                "+ ${formattedPercentage}%"
            else
                "- $formattedPercentage%"
        }

        Glide.with(ivCoinImage)
            .load(coin.imageUrl)
            .circleCrop()
            .into(ivCoinImage)
    }


}