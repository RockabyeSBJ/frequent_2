package com.rockabyesbj.core.common.model

data class UserProfile(
        val userId: String?,
        val email: String?,
        val displayName: String?,
        val avatarUrl: String?,
        val phoneNumber: String?,
        val loyaltyTier: String?,
        val dateJoined: Long?,
        val lastUpdated: Long?
)
