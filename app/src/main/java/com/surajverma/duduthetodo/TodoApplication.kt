package com.surajverma.duduthetodo

import android.app.Application
import com.surajverma.duduthetodo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@TodoApplication)
            modules(appModule)
        }
    }
}