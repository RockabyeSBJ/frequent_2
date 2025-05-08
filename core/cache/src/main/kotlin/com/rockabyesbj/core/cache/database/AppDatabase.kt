package com.rockabyesbj.core.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rockabyesbj.core.cache.entity.UserSessionEntity
import com.rockabyesbj.core.cache.entity.UserProfileEntity
import com.rockabyesbj.core.cache.dao.UserProfileDao
import com.rockabyesbj.core.cache.dao.UserSessionDao


@Database(
    entities = [
        UserSessionEntity::class,
        UserProfileEntity::class
    ],
    version = 1, // ✅ Fine for now
    exportSchema = true // ✅ Important for schema file generation
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userSessionDao(): UserSessionDao
    abstract fun userProfileDao(): UserProfileDao
}

