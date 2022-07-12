package ru.bscmsk.renttable.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(
    private val context: Context
) {
    @Provides
    fun provideContext(): Context = context.applicationContext
}