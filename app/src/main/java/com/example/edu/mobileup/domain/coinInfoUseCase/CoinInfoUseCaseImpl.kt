package com.example.edu.mobileup.domain.coinInfoUseCase

import com.example.edu.mobileup.data.coinInfoData.CoinInfoData
import com.example.edu.mobileup.data.repo.MainRepository
import retrofit2.Response
import javax.inject.Inject

class CoinInfoUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) :CoinInfoUseCase {
    override suspend fun getCoin(id:String):Response<CoinInfoData> {
        return repository.getCoin(id = id)
    }
}