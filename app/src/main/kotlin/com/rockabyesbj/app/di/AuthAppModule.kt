package com.rockabyesbj.app.di

import android.content.Context
import com.rockabyesbj.app.R
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthAppModule {

    @Provides
    @Named("authConfigResId")
    fun provideMsalConfigResId(): Int = R.raw.msal_config

    @Provides
    @Singleton
    fun provideMsalApplication(
        @ApplicationContext context: Context,
        @Named("authConfigResId") configResId: Int
    ): ISingleAccountPublicClientApplication {
        val latch = CountDownLatch(1)
        var result: ISingleAccountPublicClientApplication? = null

        PublicClientApplication.createSingleAccountPublicClientApplication(
            context,
            configResId,
            object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(app: ISingleAccountPublicClientApplication) {
                    result = app
                    latch.countDown()
                }

                override fun onError(exception: MsalException) {
                    latch.countDown()
                }
            }
        )

        latch.await(3, TimeUnit.SECONDS)

        return result ?: throw IllegalStateException("MSAL client creation failed")
    }
}