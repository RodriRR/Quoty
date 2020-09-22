package com.represa.quoty

import android.app.Application
import com.represa.quoty.util.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}