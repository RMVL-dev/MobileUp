package com.example.edu.mobileup.data

sealed interface ResponseState <T> {

    data class Success<T>(val data:T):ResponseState<T>
    class Error<T>():ResponseState<T>
    class Loading<T>():ResponseState<T>
    class None<T>():ResponseState<T>
}