package com.rockabyesbj.core.error

import retrofit2.HttpException
import java.io.IOException
import javax.net.ssl.SSLException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandler @Inject constructor() {

    fun handleException(throwable: Throwable): AppError {
        return when (throwable) {
            is IOException -> mapIOException(throwable)
            is HttpException -> mapHttpException(throwable)
            else -> AppError.Unknown(throwable.message)
        }
    }

    private fun mapIOException(exception: IOException): AppError {
        return when (exception) {
            is SocketTimeoutException -> AppError.Timeout
            is ConnectException,
            is UnknownHostException,
            is SSLException -> AppError.Network
            else -> AppError.Network
        }
    }

    private fun mapHttpException(exception: HttpException): AppError {
        return when (exception.code()) {
            401 -> AppError.Authentication
            403 -> AppError.Unauthorized
            404 -> AppError.NotFound
            429 -> AppError.TooManyRequests
            in 500..599 -> AppError.ServerError
            else -> AppError.Unknown(exception.message())
        }
    }

    fun isRetryable(throwable: Throwable): Boolean {
        return when (throwable) {
            is SocketTimeoutException,
            is ConnectException,
            is UnknownHostException,
            is SSLException -> true
            is HttpException -> throwable.code() in 500..599
            else -> false
        }
    }
} 