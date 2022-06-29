package ru.bscmsk.renttable.dataNetwork.implementations

import ru.bscmsk.renttable.dataNetwork.storage.NetworkStorage
import ru.bscmsk.renttable.dataNetwork.storage.models.UserModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.NetworkLoginRepository

class NetworkLoginRepositoryImpl(private val networkStorage: NetworkStorage):
    NetworkLoginRepository {

    override fun sendUserToServ(user: UserDataModel): TokensDataModel {
        val newUser = UserModel(user.login, user.password)
        val newTokens = networkStorage.enterToAccount(newUser)
        return TokensDataModel(newTokens.accessToken, newTokens.refreshToken)
    }
}