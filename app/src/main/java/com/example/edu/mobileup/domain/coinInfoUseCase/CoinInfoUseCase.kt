package com.example.edu.mobileup.domain.coinInfoUseCase

import com.example.edu.mobileup.data.coinInfoData.CoinInfo
import retrofit2.Response

interface CoinInfoUseCase {
    suspend fun getCoin(id:String):Response<CoinInfo>
}