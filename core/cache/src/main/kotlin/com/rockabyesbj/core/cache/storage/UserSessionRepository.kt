package com.rockabyesbj.core.cache.storage

// This is the data persistence layer responsible for
// saving, retrieving, and clearing session data from Room (via UserSessionDao).
// It implements ISessionRepository, which is your interface for session storage abstraction.


import com.rockabyesbj.core.cache.dao.UserSessionDao
import com.rockabyesbj.core.cache.entity.UserSessionEntity
import com.rockabyesbj.core.auth.interfaces.ISessionRepository
import javax.inject.Inject

class UserSessionRepository @Inject constructor(
    private val userSessionDao: UserSessionDao
) : ISessionRepository {

    override suspend fun saveUserSession(session: com.rockabyesbj.core.common.session.UserSession) {
        val entity = UserSessionEntity(
            accessToken = session.accessToken,
            refreshToken = session.refreshToken,
            createdAt = session.createdAt
        )
        userSessionDao.saveSession(entity)
    }

    override suspend fun getUserSession(): com.rockabyesbj.core.common.session.UserSession? {
        return userSessionDao.getSession()?.let {
            com.rockabyesbj.core.common.session.UserSession(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken,
                createdAt = it.createdAt
            )
        }
    }

    override suspend fun clearUserSession() {
        userSessionDao.clearSession()
    }
}






