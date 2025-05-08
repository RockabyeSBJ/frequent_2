package com.rockabyesbj.features.auth.repository

import android.app.Activity
import android.util.Log
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import com.rockabyesbj.core.common.session.UserSession
import com.rockabyesbj.core.common.utils.JwtUtil
import com.rockabyesbj.core.auth.model.TokenResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class MsalTokenProvider @Inject constructor(
    private val msalClient: ISingleAccountPublicClientApplication,
    @Named("authScopes") private val scopes: Array<String>,
    @Named("authAuthority") private val authority: String
) : IAuthSessionManager {

    companion object {
        private const val TAG = "MsalTokenProvider"
    }

    private val _authState = MutableStateFlow(false)
    override val authState: StateFlow<Boolean> = _authState.asStateFlow()

    private var cachedToken: String? = null

    override val isLoggedIn: Boolean
        get() = cachedToken?.let { !JwtUtil.isTokenExpired(it) } == true

    override suspend fun getAccessToken(): String? {
        cachedToken?.let { token ->
            if (!JwtUtil.isTokenExpired(token)) return token
        }

        return when (val result = acquireTokenSilently()) {
            is TokenResult.Success -> {
                cachedToken = result.token
                result.token
            }
            else -> {
                Log.w(TAG, "Silent token acquisition failed or not implemented")
                null
            }
        }
    }

    override fun getAccessTokenBlocking(): String? = runBlocking {
        getAccessToken()
    }

    override suspend fun signIn(activity: Activity): Boolean = suspendCancellableCoroutine { cont ->
        val params = AcquireTokenParameters.Builder()
            .startAuthorizationFromActivity(activity)
            .withScopes(scopes.toList())
            .withCallback(object : AuthenticationCallback {
                override fun onSuccess(result: IAuthenticationResult) {
                    Log.d(TAG, "Sign-in successful")
                    cachedToken = result.accessToken
                    _authState.value = true
                    cont.resume(true)
                }

                override fun onError(exception: MsalException) {
                    Log.e(TAG, "Sign-in error", exception)
                    _authState.value = false
                    cont.resume(false)
                }

                override fun onCancel() {
                    Log.d(TAG, "Sign-in canceled by user")
                    _authState.value = false
                    cont.resume(false)
                }
            })
            .build()

        msalClient.acquireToken(params)
    }

    override suspend fun signOut() {
        suspendCancellableCoroutine<Unit> { cont ->
            msalClient.signOut(object : ISingleAccountPublicClientApplication.SignOutCallback {
                override fun onSignOut() {
                    Log.d(TAG, "User signed out")
                    cachedToken = null
                    _authState.value = false
                    cont.resume(Unit) // ✅ return Unit
                }

                override fun onError(exception: MsalException) {
                    Log.e(TAG, "Sign-out error", exception)
                    cont.resume(Unit) // ✅ return Unit even on error
                }
            })
        }
    }

    override suspend fun restoreSession() {
        // Optional: implement later or delegate to ISessionManager
        getAccessToken()
    }

    override fun getUserSession(): UserSession? {
        return cachedToken?.takeIf { !JwtUtil.isTokenExpired(it) }?.let {
            UserSession(accessToken = it, refreshToken = "", createdAt = System.currentTimeMillis())
        }
    }

    // Stub: for now always return UI required
    private suspend fun acquireTokenSilently(): TokenResult {
        // TODO: Reintroduce MSAL silent token logic using callbacks
        return TokenResult.UiRequired
    }
}
