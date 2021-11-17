package com.saber.flashlightsmarket.utils

import android.app.Application
import com.saber.flashlightsmarket.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarketApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MarketApplication)
            modules(appModule)
        }
    }
}