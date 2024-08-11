package com.example.edu.mobileup.domain.usecases

import com.example.edu.mobileup.data.coinListData.CoinsListItem
import com.example.edu.mobileup.data.repo.MainRepository
import retrofit2.Response
import javax.inject.Inject

class CoinsListUseCaseImpl @Inject constructor(
    private val repository: MainRepository
):CoinsListUseCase {
    override suspend fun getCoinsList(currency:String): Response<List<CoinsListItem>> {
        return repository.getCoinsList(currency)
    }

}