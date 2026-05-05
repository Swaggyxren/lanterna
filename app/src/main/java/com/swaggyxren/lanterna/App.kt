package com.swaggyxren.lanterna

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG_LOGGING) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private object BuildConfig {
        // Compile-time const so we don't depend on the generated BuildConfig class
        const val DEBUG_LOGGING = true
    }
}
