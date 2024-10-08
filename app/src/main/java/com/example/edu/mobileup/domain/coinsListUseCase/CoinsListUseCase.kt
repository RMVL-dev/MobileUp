package com.example.edu.mobileup.domain.coinsListUseCase

import com.example.edu.mobileup.data.coinListData.CoinsListItem
import retrofit2.Response

interface CoinsListUseCase {
    suspend fun getCoinsList(currency:String): Response<List<CoinsListItem>>
}