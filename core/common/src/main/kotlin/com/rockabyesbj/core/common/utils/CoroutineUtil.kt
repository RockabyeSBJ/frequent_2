package com.rockabyesbj.core.common.utils

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object CoroutineUtil {

    fun CoroutineScope.launchSafe(
        context: CoroutineContext = SupervisorJob(),
        onError: (Throwable) -> Unit = {},
        block: suspend CoroutineScope.() -> Unit
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onError(throwable)
        }

        launch(context + exceptionHandler) {
            try {
                block()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    suspend fun <T> withRetry(
        maxAttempts: Int = 3,
        initialDelayMillis: Long = 1000L,
        maxDelayMillis: Long = 10000L,
        backoffMultiplier: Double = 2.0,
        block: suspend () -> T
    ): Result<T> {
        var currentDelay = initialDelayMillis
        var attempt = 0

        while (attempt < maxAttempts) {
            try {
                return Result.success(block())
            } catch (e: Exception) {
                attempt++
                if (attempt >= maxAttempts) {
                    return Result.failure(e)
                }
                delay(currentDelay)
                currentDelay = (currentDelay * backoffMultiplier).toLong()
                    .coerceAtMost(maxDelayMillis)
            }
        }

        return Result.failure(IllegalStateException("Max retry attempts reached"))
    }
}