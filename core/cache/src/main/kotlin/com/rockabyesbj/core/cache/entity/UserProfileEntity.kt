package com.rockabyesbj.core.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
        @PrimaryKey
        val userId:
                String, // Azure B2C 'sub' claim, Room will not accept a null-value (which String?
        // allows)
        val displayName: String?, // Friendly user name
        val email: String?, // Email address
        val avatarUrl: String?, // Profile picture URL
        val phoneNumber: String?, // Optional phone
        val loyaltyTier: String?, // e.g., "Silver", "Gold"
        // val preferredLanguage: String,      // e.g., "en", "he", "fr"
        val dateJoined: Long?, // ISO 8601 string: "2024-04-27T12:34:56Z"
        val lastUpdated: Long, // Timestamp of last update
// val isEmailVerified: Boolean?,      // Future extensibility (for marketing compliance)
// val isPhoneVerified: Boolean?       // Future extensibility
)
// TODO Need to update the profile here as we grow
