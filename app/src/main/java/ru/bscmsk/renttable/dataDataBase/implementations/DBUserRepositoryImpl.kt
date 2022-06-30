package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.DBStorageImpl
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.DBUserRepository

class DBUserRepositoryImpl(private val dbStorageImpl: DBStorageImpl) :
    DBUserRepository {
    override fun saveUserDB(context: Context, userDataModel: UserDataModel){
        dbStorageImpl.initDataBase(context)
        dbStorageImpl.saveUserToDB(userDataModel.login)
    }
}