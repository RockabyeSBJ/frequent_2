package com.rockabyesbj.app.msal

import android.content.Context
import com.microsoft.identity.client.IPublicClientApplication
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.exception.MsalException
import com.rockabyesbj.core.auth.interfaces.IMsalClientProvider
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * MsalClientManager is a lifecycle-safe, deferred MSAL client provider.
 *
 * MSAL (Microsoft Authentication Library) client creation is asynchronous and cannot be safely
 * constructed using a synchronous @Provides method in Hilt.
 *
 * This class uses a CompletableDeferred to encapsulate the result of async initialization.
 * It allows other parts of the app to safely call `getClient()` and await the fully initialized
 * ISingleAccountPublicClientApplication instance without blocking or risking a crash.
 *
 * It implements IMsalClientProvider, which is defined in :core:auth, allowing :features:auth
 * to use this class via dependency inversion without tightly coupling to :app.
 */
@Singleton
class MsalClientManager @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named("authConfigResId") private val configResId: Int
) : IMsalClientProvider {

    private val clientDeferred = CompletableDeferred<ISingleAccountPublicClientApplication>()

    init {
        PublicClientApplication.createSingleAccountPublicClientApplication(
            context,
            configResId,
            object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(app: ISingleAccountPublicClientApplication) {
                    clientDeferred.complete(app)
                }

                override fun onError(exception: MsalException) {
                    clientDeferred.completeExceptionally(exception)
                }
            }
        )
    }

    override suspend fun getClient(): ISingleAccountPublicClientApplication {
        return clientDeferred.await()
    }
}
