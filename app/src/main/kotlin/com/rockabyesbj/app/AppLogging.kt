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