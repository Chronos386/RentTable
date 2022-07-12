package ru.bscmsk.renttable.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.bscmsk.renttable.data.storage.localDB.LocalDataBase
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class LocalDBModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        LocalDataBase::class.java,
        "database"
    ).build()

    @Provides
    @Singleton
    fun provideAccessTokenDao(database: LocalDataBase) = database.accessTokenDao()

    @Provides
    @Singleton
    fun provideRefreshTokenDao(database: LocalDataBase) = database.refreshTokenDao()

    @Provides
    @Singleton
    fun provideCityDao(database: LocalDataBase) = database.cityDao()

    @Provides
    @Singleton
    fun provideUserDao(database: LocalDataBase) = database.userDao()
}