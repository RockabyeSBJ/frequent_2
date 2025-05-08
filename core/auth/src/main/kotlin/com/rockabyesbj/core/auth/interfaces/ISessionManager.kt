package com.rockabyesbj.core.auth.interfaces

import com.rockabyesbj.core.common.session.UserSession
import kotlinx.coroutines.flow.StateFlow

interface ISessionManager {

    val userSession: StateFlow<UserSession?>
    val isLoggedIn: Boolean

    suspend fun setSession(session: UserSession)
    suspend fun clearSession()
    suspend fun restoreSession()

    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun getUserSession(): UserSession?
}
