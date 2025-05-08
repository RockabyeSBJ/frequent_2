package com.rockabyesbj.core.error

sealed class AppError {
    object Network : AppError()
    object Authentication : AppError()
    object Unauthorized : AppError()
    object NotFound : AppError()
    object Timeout : AppError()
    object TooManyRequests : AppError()
    object ServerError : AppError()
    data class Unknown(val message: String?) : AppError()
} 