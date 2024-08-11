package com.example.edu.mobileup.network.Service

import com.example.edu.mobileup.data.coinInfoData.CoinInfo
import com.example.edu.mobileup.data.coinListData.CoinsListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MobileUPService {
    @GET("coins/markets")
    suspend fun getCoinsList(
        @Query("vs_currency") currency:String,
        @Query("per_page") perPage:Int = 20,
        @Query("page") page:Int = 1
    ):Response<List<CoinsListItem>>


    @GET("coins/{id}")
    suspend fun getCoin(
        @Path("id") id:String
    ):Response<CoinInfo>
}