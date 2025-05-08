package com.rockabyesbj.features.profile.repository

import android.util.Log
import com.rockabyesbj.core.auth.interfaces.IUserProfileRepository
import com.rockabyesbj.core.cache.dao.UserProfileDao
import com.rockabyesbj.core.cache.entity.UserProfileEntity
import com.rockabyesbj.core.common.model.UserProfile
import com.rockabyesbj.core.network.api.ApiService
import com.rockabyesbj.core.network.dto.UserProfileDto
import com.rockabyesbj.core.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepository @Inject constructor(
    private val userProfileDao: UserProfileDao,
    private val apiService: ApiService
) : IUserProfileRepository {

    companion object {
        private const val TAG = "UserProfileRepository"
        private const val CACHE_DURATION_MS = 5 * 60 * 1000 // 5 minutes
    }

    override suspend fun saveUserProfile(profile: UserProfile) {
        try {
            val userId = profile.userId
                ?: throw IllegalArgumentException("Cannot save UserProfile: userId is null")

            val entity = UserProfileEntity(
                userId = userId,
                email = profile.email,
                displayName = profile.displayName,
                avatarUrl = profile.avatarUrl,
                phoneNumber = profile.phoneNumber,
                loyaltyTier = profile.loyaltyTier,
                dateJoined = profile.dateJoined,
                lastUpdated = System.currentTimeMillis()
            )

            userProfileDao.saveUserProfile(entity)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to save user profile", e)
            throw e
        }
    }

    override suspend fun getUserProfile(userId: String): UserProfile? {
        return try {
            val cachedProfile = userProfileDao.getUserProfile(userId)
            val currentTime = System.currentTimeMillis()

            if (cachedProfile != null &&
                (currentTime - cachedProfile.lastUpdated) < CACHE_DURATION_MS
            ) {
                return cachedProfile.toUserProfile()
            }

            val remoteResult = safeApiCall { apiService.getUserProfile(userId) }

            return if (remoteResult.isSuccess) {
                val dto = remoteResult.getOrThrow()
                val profile = dto.toUserProfile()
                saveUserProfile(profile)
                profile
            } else {
                Log.w(TAG, "Failed to fetch profile from network: ${remoteResult.exceptionOrNull()}")
                cachedProfile?.toUserProfile() // fallback to stale cache
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get user profile", e)
            userProfileDao.getUserProfile(userId)?.toUserProfile()
        }
    }

    override suspend fun deleteUserProfile(userId: String) {
        try {
            userProfileDao.deleteUserProfile(userId)
            safeApiCall { apiService.deleteUserProfile(userId) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to delete user profile", e)
            throw e
        }
    }

    private fun UserProfileEntity.toUserProfile(): UserProfile {
        return UserProfile(
            userId = userId,
            email = email,
            displayName = displayName,
            avatarUrl = avatarUrl,
            phoneNumber = phoneNumber,
            loyaltyTier = loyaltyTier,
            dateJoined = dateJoined,
            lastUpdated = lastUpdated
        )
    }

    private fun UserProfileDto.toUserProfile(): UserProfile {
        return UserProfile(
            userId = userId,
            email = email,
            displayName = displayName,
            avatarUrl = avatarUrl,
            phoneNumber = phoneNumber,
            loyaltyTier = loyaltyTier,
            dateJoined = dateJoined,
            lastUpdated = System.currentTimeMillis()
        )
    }
}
