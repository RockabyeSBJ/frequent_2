package com.rockabyesbj.core.auth.interfaces

//ISessionRepository — Persistent Storage Layer. Role: Handles data persistence of session info (e.g., Room DB or EncryptedSharedPrefs)
//Responsibilities:
//--Save session (saveSession(UserSession))
//--Load saved session on app start
//--Clear session when user logs out
//--Acts as the source of truth across app launches
//Think of this as: "How do I persist the session state between app restarts?"


import com.rockabyesbj.core.common.session.UserSession

interface ISessionRepository {
    suspend fun saveUserSession(session: UserSession)
    suspend fun getUserSession(): UserSession?
    suspend fun clearUserSession()
}
