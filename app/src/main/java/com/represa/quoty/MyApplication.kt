package com.represa.quoty

import android.app.Application
import com.represa.quoty.util.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Start Koin
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            //androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}