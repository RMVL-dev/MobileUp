package com.example.edu.mobileup.data.coinListData

import com.example.edu.mobileup.data.coinListData.additional.Roi
import com.google.gson.annotations.SerializedName

data class CoinsListItem (
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("symbol")
    val symbol:String? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("image")
    val imageUrl:String? = null,
    @SerializedName("current_price")
    val currentPrice:Double? = null,
    @SerializedName("market_cap")
    val marketCap:Long? = null,
    @SerializedName("market_cap_rank")
    val marketCapRank:Int? = null,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation:Long? = null,
    @SerializedName("total_volume")
    val totalVolume:Long? = null,
    @SerializedName("high_24h")
    val high24h:Double? = null,
    @SerializedName("low_24h")
    val low24h:Double? = null,
    @SerializedName("price_change_24h")
    val priceChange24h:Double? = null,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h:Double? = null,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h:Double? = null,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h:Double? = null,
    @SerializedName("circulating_supply")
    val circulatingSupply:Double? = null,
    @SerializedName("total_supply")
    val totalSupply:Double? = null,
    @SerializedName("max_supply")
    val maxSupply:Double? = null,
    @SerializedName("ath")
    val ath:Double? = null,
    @SerializedName("ath_change_percentage")
    val athChangePercentage:Double? = null,
    @SerializedName("ath_date")
    val athDate:String? = null,
    @SerializedName("atl")
    val atl:Double? = null,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage:Double? = null,
    @SerializedName("atl_date")
    val atlDate:String? = null,
    @SerializedName("roi")
    val roi:Roi? = null,
    @SerializedName("last_updated")
    val lastUpdated:String? = null,
)
