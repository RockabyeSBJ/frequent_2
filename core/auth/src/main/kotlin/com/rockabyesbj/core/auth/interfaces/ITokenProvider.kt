package com.rockabyesbj.core.auth.interfaces

import android.app.Activity
import kotlinx.coroutines.flow.StateFlow

interface ITokenProvider {
    val authState: StateFlow<Boolean>
    suspend fun getAccessToken(): String?
    fun getAccessTokenBlocking(): String?
    suspend fun signIn(activity: Activity): Boolean
    suspend fun signOut(): Boolean
}