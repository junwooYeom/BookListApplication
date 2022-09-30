package com.junwooyeom.booklistapplication.domain.model

sealed class NetworkResult<out T> {
    data class Loading(val isLoading: Boolean): NetworkResult<Nothing>()
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Failure(val error: Throwable): NetworkResult<Nothing>()
}
