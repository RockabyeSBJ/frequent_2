package com.rockabyesbj.app.di

import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import com.rockabyesbj.app.R
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
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
        return result ?: throw IllegalStateException("MSAL init failed")
    }
}