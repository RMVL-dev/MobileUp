package com.example.edu.mobileup.data.coinInfoData.additional

import com.google.gson.annotations.SerializedName

data class ImagesUrls(
    @SerializedName("thumb")
    val thumb:String,
    @SerializedName("small")
    val small:String,
    @SerializedName("large")
    val large:String
)