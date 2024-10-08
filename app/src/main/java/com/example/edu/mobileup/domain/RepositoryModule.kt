package com.example.edu.mobileup.domain

import com.example.edu.mobileup.data.repo.MainRepository
import com.example.edu.mobileup.data.repo.MainRepositoryImpl
import com.example.edu.mobileup.domain.coinInfoUseCase.CoinInfoUseCase
import com.example.edu.mobileup.domain.coinInfoUseCase.CoinInfoUseCaseImpl
import com.example.edu.mobileup.domain.coinsListUseCase.CoinsListUseCase
import com.example.edu.mobileup.domain.coinsListUseCase.CoinsListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRepository(impl:MainRepositoryImpl):MainRepository

    @Binds
    abstract fun provideCoinsListUseCase(impl:CoinsListUseCaseImpl):CoinsListUseCase

    @Binds
    abstract fun provideCoinInfoUseCase(impl:CoinInfoUseCaseImpl):CoinInfoUseCase
}