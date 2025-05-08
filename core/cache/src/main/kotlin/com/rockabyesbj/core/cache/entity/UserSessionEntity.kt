package com.rockabyesbj.core.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_session")
data class UserSessionEntity(
    @PrimaryKey val id: Int = 0, // only ever store 1 session
    val accessToken: String,
    val refreshToken: String,
    val createdAt: Long
)
