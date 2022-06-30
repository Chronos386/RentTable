package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorageImpl
import ru.bscmsk.renttable.domain.repository.DBTablesRepository

class DBTablesRepositoryImpl(private val dbStorageImpl: DBStorageImpl) :
    DBTablesRepository {
    override fun clearDB(context: Context){
        dbStorageImpl.initDataBase(context)
        dbStorageImpl.clearUserDB()
    }
    }