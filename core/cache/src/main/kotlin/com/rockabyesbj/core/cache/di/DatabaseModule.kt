package com.rockabyesbj.core.cache.di

import android.content.Context
import androidx.room.Room
import com.rockabyesbj.core.cache.database.AppDatabase
import com.rockabyesbj.core.cache.dao.UserSessionDao
import com.rockabyesbj.core.cache.dao.UserProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "Frequent_2_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserSessionDao(appDatabase: AppDatabase): UserSessionDao {
        return appDatabase.userSessionDao()
    }

    @Provides
    @Singleton
    fun provideUserProfileDao(appDatabase: AppDatabase): UserProfileDao {
        return appDatabase.userProfileDao()
    }
}