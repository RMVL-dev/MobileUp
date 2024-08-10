package com.example.edu.mobileup.data.coinListData.additional

import com.google.gson.annotations.SerializedName

data class Roi(
    @SerializedName("times")
    val times:Double? = null,
    @SerializedName("currency")
    val currency:String? = null,
    @SerializedName("percentage")
    val percentage:Double? = null,
)
