// DEPRECATED in favor of IAuthSessionManager
// TODO remove
// AuthTokenProvider gets injected into AuthInterceptor to get around AuthInterceptor having to depend on :features
//Note AuthTokenProvider is in :core:auth but occupies the same namespace as features:auth. Need to fix in future version.
package com.rockabyesbj.security.token

import android.app.Activity
import kotlinx.coroutines.flow.StateFlow

interface AuthTokenProvider {
    suspend fun getAccessToken(): String?
    fun getAccessTokenBlocking(): String?
    val authState: StateFlow<Boolean>

    //suspend fun signIn(activity: Activity): Boolean
    //suspend fun signOut(): Boolean
    // add any other methods you need (refreshToken, clearAuthState, etc.)
    // three of the above deprecated in Frequent_2 1.0 April 25, 25


/**
 * Interface for managing authentication tokens in the application.
 * Implementations should handle token acquisition, refresh, and validation.
 */

    /**
     * Initiates interactive sign-in via the provided Activity.
     *
     * @return true if sign-in succeeded.
     */
    suspend fun signIn(activity: Activity): Boolean

    /**
     * Signs the user out and clears all local state.
     *
     * @return true if sign-out succeeded.
     */
    suspend fun signOut(): Boolean

    /**
     * (Optional) Forces a silent token refresh.
     *
     * @return refreshed token, or null if refresh failed.
     */
    suspend fun refreshToken(): String? = getAccessToken()
}
/*
    /**
     * Returns the current authentication state flow.
     *
     * @return A StateFlow<Boolean> representing the authentication state.
     */
    // fun getAuthState(): StateFlow<Boolean> Deprecated in Frequent_2 1.0 April 25, 25

    /**
     * Clears the current authentication state and tokens.
     */
    // suspend fun clearAuthState()  Deprecated in Frequent_2 1.0 April 25, 25

    /**
     * Refreshes the current access token if possible.
     *
     * @return The new access token, or null if refresh failed.
     */
    // suspend fun refreshToken(): String? Deprecated in Frequent_2 1.0 April 25, 25
*/