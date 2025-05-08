package com.rockabyesbj.core.network.api

import com.rockabyesbj.core.network.dto.*
import retrofit2.http.*

/** Central Retrofit service interface for your backend API. */
interface ApiService {
    // === User Profile ===

    /**
     * Get user profile by ID
     * @param userId The user's unique identifier
     * @return UserProfileDto containing user profile data
     */
    @GET("user/profile/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): UserProfileDto

    /**
     * Update user profile
     * @param userId The user's unique identifier
     * @param profile The updated profile data
     * @return Updated UserProfileDto
     */
    @PUT("user/profile/{userId}")
    suspend fun updateUserProfile(
            @Path("userId") userId: String,
            @Body profile: UserProfileDto
    ): UserProfileDto

    /**
     * Delete user profile
     * @param userId The user's unique identifier
     */
    @DELETE("user/profile/{userId}") suspend fun deleteUserProfile(@Path("userId") userId: String)

    // === Business & Loyalty ===

    @GET("businesses") suspend fun getBusinesses(): List<BusinessDto>

    // Future: programs, deals, transactions
}