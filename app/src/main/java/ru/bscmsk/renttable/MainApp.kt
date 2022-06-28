package ru.bscmsk.renttable

import android.app.Application
import android.content.Context
import ru.bscmsk.renttable.AppComponent

class MainApp : Application() {
    lateinit var appComponent: AppComponent
        private set
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .mainActivityModule(MainActivityModule(this))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }