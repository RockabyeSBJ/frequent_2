package com.rockabyesbj.core.network.dto

data class UserProfileDto(
    val userId: String?,
    val email: String?,
    val displayName: String?,
    val avatarUrl: String?,
    val phoneNumber: String?,
    val loyaltyTier: String?,
    val dateJoined: Long?,
    val isEmailVerified: Boolean = false
)
