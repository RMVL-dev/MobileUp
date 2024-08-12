package com.example.edu.mobileup.data.repo

import com.example.edu.mobileup.data.coinInfoData.CoinInfoData
import com.example.edu.mobileup.network.Service.MobileUPService
import com.example.edu.mobileup.data.coinListData.CoinsListItem
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MobileUPService
): MainRepository {
    override suspend fun getCoinsList(currency: String): Response<List<CoinsListItem>> {
        return api.getCoinsList(currency)
    }

    override suspend fun getCoin(id: String): Response<CoinInfoData> {
        return api.getCoin(id = id)
    }
}