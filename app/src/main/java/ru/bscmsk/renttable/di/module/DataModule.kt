package ru.bscmsk.renttable.di.module

import dagger.Module

@Module(includes = [NetworkModule::class, LocalDBModule::class, DataBindsModule::class])
class DataModule