package com.example.edu.mobileup.data.repo

import com.example.edu.mobileup.data.coinListData.CoinsListItem
import retrofit2.Response

interface MainRepository {
    suspend fun getCoinsList(currency:String):Response<List<CoinsListItem>>
}