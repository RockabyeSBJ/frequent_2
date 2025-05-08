package com.rockabyesbj.core.common.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.success(apiCall())
        } catch (throwable: Throwable) {
            val wrapped = when (throwable) {
                is IOException -> Exception("Network error: ${throwable.message}", throwable)
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.response()?.errorBody()?.string()
                    Exception("HTTP $code: $errorResponse", throwable)
                }
                else -> Exception("Unknown error: ${throwable.message}", throwable)
            }
            Result.failure(wrapped)
        }
    }
}
