package ru.bscmsk.renttable.domain.repository

import android.content.Context
import ru.bscmsk.renttable.domain.models.UserDataModel

interface DBUserRepository {
    fun saveUserDB(context: Context, userDataModel: UserDataModel)

}