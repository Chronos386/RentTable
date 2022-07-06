package ru.bscmsk.renttable.dataDataBase.implementations

import android.content.Context
import ru.bscmsk.renttable.dataDataBase.storage.UserStorage
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.DBUserRepository

class DBUserRepositoryImpl(private val userStorage: UserStorage) :
    DBUserRepository {
    override fun saveUserDB(context: Context, userDataModel: UserDataModel){
        userStorage.saveUserToDB(userDataModel.login)
    }
}