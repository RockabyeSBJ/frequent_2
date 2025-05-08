package com.rockabyesbj.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FrequentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppLogging.initIfDebug()
    }
}