package com.ilhmdhn.gamecatalog

import android.app.Application
import com.ilhmdhn.gamecatalog.core.di.databaseModule
import com.ilhmdhn.gamecatalog.core.di.networkModule
import com.ilhmdhn.gamecatalog.core.di.repositoryModule
import com.ilhmdhn.gamecatalog.di.useCaseModule
import com.ilhmdhn.gamecatalog.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}