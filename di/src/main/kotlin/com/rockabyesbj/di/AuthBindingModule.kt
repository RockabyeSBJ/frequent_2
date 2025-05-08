package com.rockabyesbj.di

import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import com.rockabyesbj.features.auth.repository.MsalTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthBindingModule {

    @Binds
    @Singleton
    abstract fun bindAuthSessionManager(
        impl: MsalTokenProvider
    ): IAuthSessionManager
}