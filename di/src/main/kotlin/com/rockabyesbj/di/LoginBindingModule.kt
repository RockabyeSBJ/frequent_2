/*
package com.rockabyesbj.logon.di

// TODO May 6 - do I need this BindingModule?


import android.app.Application
import android.util.Log
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import com.rockabyesbj.app.R
import com.rockabyesbj.core.common.interfaces.IAuthSessionManager
import com.rockabyesbj.auth.ISessionManager
import com.rockabyesbj.auth.IAuthSessionManager
import com.rockabyesbj.features.auth.repository.DefaultAuthRepository
import com.rockabyesbj.features.auth.repository.MsalTokenProvider
import com.rockabyesbj.auth.SessionManager
import com.rockabyesbj.core.auth.interfaces.IUserProfileRepository
import com.rockabyesbj.profile.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginBindingModule {
    @Binds
    abstract fun bindUserProfileRepository(
        impl: UserProfileRepository
    ): IUserProfileRepository
}


 */