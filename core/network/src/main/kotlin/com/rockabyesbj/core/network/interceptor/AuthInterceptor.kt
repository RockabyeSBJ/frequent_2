package com.rockabyesbj.core.network.interceptor

import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authTokenProvider: IAuthSessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = authTokenProvider.getAccessTokenBlocking()

        val authenticatedRequest = token?.let {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $it")
                .build()
        } ?: originalRequest

        return chain.proceed(authenticatedRequest)
    }
}
