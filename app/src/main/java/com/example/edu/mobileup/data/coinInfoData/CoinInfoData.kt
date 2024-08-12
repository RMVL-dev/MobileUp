package com.example.edu.mobileup.data.coinInfoData

import com.example.edu.mobileup.data.coinInfoData.additional.Description
import com.example.edu.mobileup.data.coinInfoData.additional.ImagesUrls
import com.google.gson.annotations.SerializedName

data class CoinInfoData (
    @SerializedName("id")
    val id:String? = null,
    @SerializedName("symbol")
    val symbol:String? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("image")
    val images:ImagesUrls? = null,
    @SerializedName("categories")
    val categories:List<String>? = null,
    @SerializedName("description")
    val description:Description? = null,
)