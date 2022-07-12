package ru.bscmsk.renttable.di.module

import dagger.Binds
import dagger.Module
import ru.bscmsk.renttable.data.repository.CityRepositoryImpl
import ru.bscmsk.renttable.data.repository.RentRepositoryImpl
import ru.bscmsk.renttable.data.repository.UserRepositoryImpl
import ru.bscmsk.renttable.data.storage.localDB.DataBaseStorage
import ru.bscmsk.renttable.data.storage.localDB.DataBaseStorageImpl
import ru.bscmsk.renttable.data.storage.network.NetworkStorage
import ru.bscmsk.renttable.data.storage.network.NetworkStorageImpl
import ru.bscmsk.renttable.domain.interactors.CityInteractorImpl
import ru.bscmsk.renttable.domain.interactors.RentInteractorImpl
import ru.bscmsk.renttable.domain.interactors.UserInteractorImpl
import ru.bscmsk.renttable.domain.repository.CityRepository
import ru.bscmsk.renttable.domain.repository.RentRepository
import ru.bscmsk.renttable.domain.repository.UserRepository
import ru.bscmsk.renttable.presentation.interactors.CityInteractor
import ru.bscmsk.renttable.presentation.interactors.RentInteractor
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import javax.inject.Singleton

@Module
interface  DataBindsModule {

    @Binds
    @Singleton
    fun bindNetworkStorage(
        networkStorageImpl: NetworkStorageImpl
    ): NetworkStorage

    @Binds
    @Singleton
    fun bindDataBaseStorage(
        dataBaseStorageImpl: DataBaseStorageImpl
    ): DataBaseStorage

    @Binds
    @Singleton
    fun bindCityRepository(
        cityRepositoryImpl: CityRepositoryImpl
    ): CityRepository

    @Binds
    @Singleton
    fun bindRentRepository(
        rentRepositoryImpl: RentRepositoryImpl
    ): RentRepository

    @Binds
    @Singleton
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    fun bindCityInteractor(
        cityInteractorImpl: CityInteractorImpl
    ): CityInteractor

    @Binds
    @Singleton
    fun bindUserInteractor(
        userInteractorImpl: UserInteractorImpl
    ): UserInteractor

    @Binds
    @Singleton
    fun bindRentInteractor(
        rentInteractorImpl: RentInteractorImpl
    ): RentInteractor
}