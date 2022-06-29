package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel

interface NetworkLoginRepository {
    fun sendUserToServ(user: UserDataModel): TokensDataModel
}