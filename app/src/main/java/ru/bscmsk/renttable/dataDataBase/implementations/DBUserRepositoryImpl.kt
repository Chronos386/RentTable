package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorage
import ru.bscmsk.renttable.dataDataBase.storage.DBStorageImpl
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.DBUserRepository

class DBUserRepositoryImpl(private val dbStorage: DBStorage) :
    DBUserRepository {
    override fun saveUserDB(context: Context, userDataModel: UserDataModel){
        dbStorage.initDataBase(context)
        dbStorage.saveUserToDB(userDataModel.login)
    }
}