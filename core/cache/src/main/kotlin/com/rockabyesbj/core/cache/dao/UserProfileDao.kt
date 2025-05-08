package com.rockabyesbj.core.cache.dao

import androidx.room.*
import com.rockabyesbj.core.cache.entity.UserProfileEntity

@Dao
interface UserProfileDao {

    @Query("SELECT * FROM user_profile WHERE userId = :userId LIMIT 1")
    suspend fun getUserProfile(userId: String): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)

    @Query("DELETE FROM user_profile WHERE userId = :userId")
    suspend fun deleteUserProfile(userId: String)

    @Query("DELETE FROM user_profile")
    suspend fun clearAllProfiles() // ✅ Bonus: wipe all profiles (optional) Not sure I want that, but ok.
}
