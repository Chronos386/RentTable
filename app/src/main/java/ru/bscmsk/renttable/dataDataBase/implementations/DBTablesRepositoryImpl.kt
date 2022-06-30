package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorage
import ru.bscmsk.renttable.domain.repository.DBTablesRepository

class DBTablesRepositoryImpl(private val dbStorage: DBStorage) :
    DBTablesRepository {
    override fun clearDB(context: Context){
        dbStorage.initDataBase(context)
        dbStorage.clearUserDB()
    }
    }