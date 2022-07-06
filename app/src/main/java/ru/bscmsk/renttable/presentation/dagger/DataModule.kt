/*package ru.bscmsk.renttable.presentation.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.bscmsk.renttable.data.DataBase.implementations.*

import ru.bscmsk.renttable.data.DataBase.storage.*
import ru.bscmsk.renttable.data.Network.implementations.*
import ru.bscmsk.renttable.data.Network.storage.*
import ru.bscmsk.renttable.domain.repository.*


@Module
class DataModule {
    @Provides
    fun provideDBStorage(context: Context): DBStorage {
        return DBStorageImpl(cont = context)
    }

    @Provides
    fun provideDBCityRepository(dbStorage: DBStorage): CityRepository {
        return CityRepositoryImpl(dbStorage = dbStorage)
    }

    @Provides
    fun provideDBTablesRepository(dbStorage: DBStorage): DBTablesRepository {
        return DBTablesRepositoryImpl(dbStorage = dbStorage)
    }

    @Provides
    fun provideDBTokensRepository(dbStorage: DBStorage): DBTokensRepository {
        return DBTokensRepositoryImpl(dbStorage = dbStorage)
    }

    @Provides
    fun provideDBUserRepository(dbStorage: DBStorage): UserRepository {
        return DBUserRepositoryImpl(dbStorage = dbStorage)
    }




    @Provides
    fun provideNetworkStorage(): NetworkStorage {
        return NetworkStorageImpl()
    }

    @Provides
    fun provideNetworkLoginRepository(networkStorage: NetworkStorage): NetworkLoginRepository {
        return NetworkLoginRepositoryImpl(networkStorage = networkStorage)
    }

    @Provides
    fun provideNetworkCityListRepository(networkStorage: NetworkStorage): NetworkCityListRepository {
        return NetworkCityListRepositoryImpl(networkStorage = networkStorage)
    }

    @Provides
    fun provideNetworkTokenRepository(networkStorage: NetworkStorage): NetworkTokenRepository {
        return NetworkTokenRepositoryImpl(networkStorage = networkStorage)
    }
}*/