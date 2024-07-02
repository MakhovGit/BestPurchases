package org.purchases.best

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.purchases.best.database.di.databaseKoinModule
import org.purchases.best.di.appKoinModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appKoinModule, databaseKoinModule)
        }
    }

}