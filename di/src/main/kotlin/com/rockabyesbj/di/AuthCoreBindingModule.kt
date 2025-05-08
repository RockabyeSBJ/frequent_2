package com.rockabyesbj.di

import com.rockabyesbj.core.auth.interfaces.ISessionManager
import com.rockabyesbj.core.auth.session.SessionManager
import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import com.rockabyesbj.features.auth.repository.MsalTokenProvider
import com.rockabyesbj.features.profile.repository.UserProfileRepository
import com.rockabyesbj.core.auth.interfaces.IUserProfileRepository
import com.rockabyesbj.core.auth.interfaces.ISessionRepository
import com.rockabyesbj.core.cache.storage.UserSessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthCoreBindingModule {

    @Binds
    @Singleton
    abstract fun bindSessionManager(
        impl: SessionManager
    ): ISessionManager

    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(
        impl: UserProfileRepository
    ): IUserProfileRepository

    @Binds
    @Singleton
    abstract fun bindUserSessionRepository(
        impl: UserSessionRepository
    ): ISessionRepository

    @Binds
    @Singleton
    abstract fun bindMsalTokenProvider(
        impl: MsalTokenProvider
    ): IAuthSessionManager

    @Module
    @InstallIn(ViewModelComponent::class)
    object HomeModule {
        // TODO: Add dependencies as needed
    }

}

    /*  <-- TODO Should implement
    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        impl: EncryptedSessionRepository
    ): ISessionRepository
}

     */