package ru.bscmsk.renttable.dataDataBase.implementations

import ru.bscmsk.renttable.dataDataBase.storage.CityStorage
import ru.bscmsk.renttable.dataDataBase.storage.UserStorage
import ru.bscmsk.renttable.domain.repository.DBTablesRepository

class DBTablesRepositoryImpl(private val userStorage: UserStorage, private val cityStorage: CityStorage) :
    DBTablesRepository {
    override fun clearDB(){
        userStorage.clearUserTable()
        userStorage.clearAccessTokenTable()
        userStorage.clearRefreshTokenTable()
        cityStorage.clearCityTable()
    }
    }