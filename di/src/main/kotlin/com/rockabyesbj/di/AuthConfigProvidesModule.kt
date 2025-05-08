package com.rockabyesbj.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AuthConfigProvidesModule {

    @Provides // TODO If your project needs to split environments (e.g. dev/staging/prod), eventually these should be sourced from BuildConfig or a runtime config system
    @Named("authScopes")
    fun provideAuthScopes(): Array<String> = arrayOf(
        "https://frequentb2c.onmicrosoft.com/frequent-dev/api.read"
    )

    @Provides // TODO If your project needs to split environments (e.g. dev/staging/prod), eventually these should be sourced from BuildConfig or a runtime config system
    @Named("authAuthority")
    fun provideAuthAuthority(): String =
        "https://frequentb2c.b2clogin.com/frequentb2c.onmicrosoft.com/B2C_1_signupsignin"

}
