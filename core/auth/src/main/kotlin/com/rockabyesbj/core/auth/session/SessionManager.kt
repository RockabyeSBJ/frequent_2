package com.rockabyesbj.core.auth.session

import android.util.Log
import com.rockabyesbj.core.auth.interfaces.ISessionManager
import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import com.rockabyesbj.core.auth.interfaces.ISessionRepository
import com.rockabyesbj.core.common.session.UserSession
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val sessionRepository: ISessionRepository,
    private val tokenProvider: IAuthSessionManager,
    @Named("ioScope") private val ioScope: CoroutineScope
) : ISessionManager {

    companion object {
        private const val TAG = "SessionManager"
        private const val REFRESH_MARGIN_MS = 60 * 1000L // 1 minute before expiry
    }

    private val _userSession = MutableStateFlow<UserSession?>(null)
    override val userSession: StateFlow<UserSession?> = _userSession.asStateFlow()

    private var refreshJob: Job? = null

    override val isLoggedIn: Boolean
        get() = _userSession.value?.isExpired == false

    override suspend fun setSession(session: UserSession) {
        _userSession.value = session
        persistSession(session)
        scheduleTokenRefresh(session)
    }

    override suspend fun clearSession() {
        refreshJob?.cancel()
        refreshJob = null
        _userSession.value = null
        clearPersistedSession()
    }

    override suspend fun restoreSession() {
        try {
            val session = sessionRepository.getUserSession()
            if (session != null && !session.isExpired) {
                _userSession.value = session
                scheduleTokenRefresh(session)
            } else {
                clearSession()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to restore session", e)
            clearSession()
        }
    }

    override fun getAccessToken(): String? = _userSession.value?.accessToken

    override fun getRefreshToken(): String? = _userSession.value?.refreshToken

    override fun getUserSession(): UserSession? = _userSession.value

    private fun persistSession(session: UserSession) {
        ioScope.launch {
            try {
                sessionRepository.saveUserSession(session)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to persist session", e)
            }
        }
    }

    private fun clearPersistedSession() {
        ioScope.launch {
            try {
                sessionRepository.clearUserSession()
            } catch (e: Exception) {
                Log.e(TAG, "Failed to clear session", e)
            }
        }
    }

    private fun scheduleTokenRefresh(session: UserSession) {
        refreshJob?.cancel()

        val expiresAt = session.expiresAt ?: return
        val timeUntilExpiry = expiresAt - System.currentTimeMillis()

        refreshJob = ioScope.launch {
            try {
                if (timeUntilExpiry > REFRESH_MARGIN_MS) {
                    delay(timeUntilExpiry - REFRESH_MARGIN_MS)
                }
                refreshToken()
            } catch (e: Exception) {
                Log.e(TAG, "Failed to refresh token", e)
                clearSession()
            }
        }
    }

    private suspend fun refreshToken() {
        try {
            val newToken = tokenProvider.getAccessToken()
            if (newToken != null) {
                _userSession.value?.let { current ->
                    val updated = current.copy(
                        accessToken = newToken,
                        createdAt = System.currentTimeMillis()
                    )
                    setSession(updated)
                }
            } else {
                Log.w(TAG, "No new token received during refresh")
                clearSession()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error refreshing token", e)
            clearSession()
        }
    }
}
