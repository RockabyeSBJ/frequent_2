package com.rockabyesbj.app

import android.os.StrictMode
import android.util.Log
import com.rockabyesbj.app.BuildConfig

object AppLogging {
    private const val TAG = "AppLogging"

    fun initIfDebug() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Setting up StrictMode")

            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build()
            )
        }
    }
}
/*  Eventually, you can use BuildConfig to contain the below values
android {
    defaultConfig {
        buildConfigField("String", "AUTHORITY_URL", "\"https://frequentb2c.b2clogin.com/...\"")
        buildConfigField("String", "CLIENT_ID", "\"your-client-id-here\"")
    }
}
and use them like this: val authority = BuildConfig.AUTHORITY_URL, for example
 */