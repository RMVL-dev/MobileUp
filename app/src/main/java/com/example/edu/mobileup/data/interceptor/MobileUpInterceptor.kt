package com.example.edu.mobileup.data.interceptor

import com.example.edu.mobileup.BuildConfig
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.SocketTimeoutException

class MobileUpInterceptor:Interceptor {
    companion object{
        private const val ACCEPT = "accept"
        private const val ACCEPT_VALUE = "application/json"
        private const val API_KEY = "x-cg-demo-api-key"

        private const val TIMEOUT_EXCEPTION = "Response timeout exceeded\n Try again"
        private const val EXCEPTION = "An error occurred"
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val builder = origin.newBuilder()

        val request = builder
            .header(ACCEPT, ACCEPT_VALUE)
            .header(API_KEY, BuildConfig.API_KEY)
            .method(origin.method, origin.body)
            .build()

        return runCatching {
            chain.proceed(request = request)
        }.getOrElse { error ->
            return@getOrElse when (error) {
                is SocketTimeoutException ->
                    Response.Builder()
                        .protocol(Protocol.HTTP_1_1)
                        .request(request)
                        .code(499)
                        .message(TIMEOUT_EXCEPTION)
                        .body("{${error}}".toResponseBody(null))
                        .build()

                else ->
                    Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(499)
                        .message(error.message ?: EXCEPTION)
                        .body("{${error}}".toResponseBody(null))
                        .build()
            }
        }
    }
}