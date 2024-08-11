package com.example.edu.mobileup.di

import com.example.edu.mobileup.BuildConfig
import com.example.edu.mobileup.network.Service.MobileUPService
import com.example.edu.mobileup.network.interceptor.MobileUpInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Provides
    fun provideOkHTTP() = OkHttpClient.Builder().apply {
        addInterceptor(MobileUpInterceptor())
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        addInterceptor(loggingInterceptor)
    }
        .connectTimeout(20000L, TimeUnit.MILLISECONDS)
        .writeTimeout(20000L, TimeUnit.MILLISECONDS)
        .readTimeout(20000L, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson: Gson
    ) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    @Provides
    fun provideService(retrofit: Retrofit): MobileUPService = retrofit.create(MobileUPService::class.java)

}