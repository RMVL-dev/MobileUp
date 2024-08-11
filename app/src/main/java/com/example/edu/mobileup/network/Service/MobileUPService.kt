package com.example.edu.mobileup.network.Service

import com.example.edu.mobileup.data.coinListData.CoinsListItem
import retrofit2.Response
import retrofit2.http.GET

interface MobileUPService {
    @GET("coins/markets?vs_currency=usd")
    suspend fun getCoinsList():Response<List<CoinsListItem>>
}