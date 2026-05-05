package com.swaggyxren.lanterna

import android.app.Application
import android.content.pm.ApplicationInfo
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Plant Timber's DebugTree only when the APK is signed as debuggable,
        // so release builds never emit verbose logs. We avoid the generated
        // BuildConfig because `android.defaults.buildfeatures.buildconfig`
        // is off project-wide.
        if ((applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
