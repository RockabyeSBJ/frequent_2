package com.rockabyesbj.di

import com.rockabyesbj.core.auth.interfaces.IUserProfileRepository
import com.rockabyesbj.features.profile.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileBindingModule {
    @Binds
    abstract fun bindUserProfileRepository(
        impl: UserProfileRepository
    ): IUserProfileRepository
}
