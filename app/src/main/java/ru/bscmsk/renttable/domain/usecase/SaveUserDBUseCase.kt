package ru.bscmsk.renttable.domain.usecase

import android.content.Context
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.DBUserRepository

class SaveUserDBUseCase(private val dbUserRepository: DBUserRepository) {
    fun execute(context: Context,userDataModel: UserDataModel) {
        dbUserRepository.saveUserDB(context=context,userDataModel=userDataModel)
    }
}