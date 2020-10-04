package com.childhealthcare.vaccinator

import android.app.Application
import com.childhealthcare.vaccinator.di.repositoryModule
import com.childhealthcare.vaccinator.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Vaccinator : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@Vaccinator)
            // modules
            modules(listOf(viewModelModule, repositoryModule))
        }
    }
}