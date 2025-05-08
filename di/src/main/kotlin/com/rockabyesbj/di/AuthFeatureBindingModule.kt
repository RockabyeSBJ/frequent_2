package com.rockabyesbj.core.auth.di

import android.app.Application
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import com.rockabyesbj.app.R
import com.rockabyesbj.core.auth.interfaces.IAuthSessionManager
import com.rockabyesbj.features.auth.repository.MsalTokenProvider
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
abstract class AuthFeatureBindingModule {

    @Binds
    @Singleton
    abstract fun bindAuthSessionManager(
        impl: MsalTokenProvider
    ): IAuthSessionManager
}

@Module
@InstallIn(SingletonComponent::class)
object AuthFeatureProvidesModule {

    @Provides
    @Named("msalConfigResId")
    fun provideMsalConfigResId(): Int = R.raw.msal_config

    @Provides
    @Named("authScopes")
    fun provideAuthScopes(): Array<String> = arrayOf(
        "https://frequentb2c.onmicrosoft.com/frequent-dev/api.read"
    )

    @Provides
    @Named("authAuthority")
    fun provideAuthAuthority(): String =
        "https://frequentb2c.b2clogin.com/frequentb2c.onmicrosoft.com/B2C_1_signupsignin"

    @Provides
    @Singleton
    fun provideMsalClient(
        app: Application,
        @Named("msalConfigResId") configResId: Int
    ): ISingleAccountPublicClientApplication {
        val latch = CountDownLatch(1)
        var client: ISingleAccountPublicClientApplication? = null
        var error: MsalException? = null

        PublicClientApplication.createSingleAccountPublicClientApplication(
            app,
            configResId,
            object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(c: ISingleAccountPublicClientApplication) {
                    client = c
                    latch.countDown()
                }

                override fun onError(e: MsalException) {
                    error = e
                    latch.countDown()
                }
            }
        )

        if (!latch.await(2, TimeUnit.SECONDS)) {
            throw RuntimeException("MSAL client initialization timed out")
        }

        error?.let { throw it }
        return requireNotNull(client) { "MSAL client failed to initialize" }
    }
}
