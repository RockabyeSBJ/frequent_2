package com.rockabyesbj.core.auth.interfaces

import com.rockabyesbj.core.common.model.UserProfile

interface IUserProfileRepository {
    suspend fun getUserProfile(userId: String): UserProfile?
    suspend fun saveUserProfile(profile: UserProfile)
    suspend fun deleteUserProfile(userId: String)
}
