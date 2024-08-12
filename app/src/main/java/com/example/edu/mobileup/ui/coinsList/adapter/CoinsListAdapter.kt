package com.example.edu.mobileup.ui.coinsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edu.mobileup.data.coinListData.CoinsListItem
import com.example.edu.mobileup.databinding.CoinItemBinding

class CoinsListAdapter:RecyclerView.Adapter<CoinViewHolder>() {

    var coinsList:List<CoinsListItem> = emptyList()
    var isUSD:Boolean = true
    var clickListener: (String?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder =
        CoinViewHolder(
            binding = CoinItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            context = parent.context
        )

    override fun getItemCount(): Int =
        coinsList.size

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        // coin id into click listener
        holder.bind(coinsList[position], isUSD){ coinId -> clickListener(coinId) }
    }
}