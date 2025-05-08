package com.rockabyesbj.core.cache.di

import com.rockabyesbj.core.auth.interfaces.ISessionRepository
import com.rockabyesbj.core.cache.storage.UserSessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun bindUserSessionRepository(
        impl: UserSessionRepository
    ): ISessionRepository
}
