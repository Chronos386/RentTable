package ru.bscmsk.renttable.app

import android.app.Application
import android.content.Context
import ru.bscmsk.renttable.BuildConfig
import ru.bscmsk.renttable.di.AppComponent
import ru.bscmsk.renttable.di.DaggerAppComponent
import ru.bscmsk.renttable.di.module.ContextModule
import timber.log.Timber

class MainApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(applicationContext))
            .build()

    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }