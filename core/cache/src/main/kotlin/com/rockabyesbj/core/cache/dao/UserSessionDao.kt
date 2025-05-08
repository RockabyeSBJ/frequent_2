package com.rockabyesbj.core.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rockabyesbj.core.cache.entity.UserSessionEntity

@Dao
interface UserSessionDao {

    @Query("SELECT * FROM user_session WHERE id = 0")
    suspend fun getSession(): UserSessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(session: UserSessionEntity)

    @Query("DELETE FROM user_session")
    suspend fun clearSession()
}