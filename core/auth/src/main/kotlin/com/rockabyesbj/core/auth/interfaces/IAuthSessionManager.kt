package com.rockabyesbj.core.auth.interfaces

import android.app.Activity
import com.rockabyesbj.core.common.session.UserSession
import kotlinx.coroutines.flow.StateFlow

interface IAuthSessionManager {

    val authState: StateFlow<Boolean>
    val isLoggedIn: Boolean

    suspend fun getAccessToken(): String?
    fun getAccessTokenBlocking(): String?
    suspend fun signIn(activity: Activity): Boolean
    suspend fun signOut()

    suspend fun restoreSession()
    fun getUserSession(): UserSession?

}
