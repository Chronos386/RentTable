package ru.bscmsk.renttable.data.repository

import ru.bscmsk.renttable.app.sealed.UserAuthorized
import ru.bscmsk.renttable.app.toData
import ru.bscmsk.renttable.app.toDomain
import ru.bscmsk.renttable.data.storage.localDB.DataBaseStorage
import ru.bscmsk.renttable.data.storage.network.NetworkStorage
import ru.bscmsk.renttable.domain.models.UserDomain
import ru.bscmsk.renttable.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkStorage: NetworkStorage,
    private val dataBaseStorage: DataBaseStorage
) : UserRepository {
    override suspend fun sendUserToServ(user: UserDomain): UserAuthorized =
        networkStorage.enterToAccount(user.toData()).let { newTokens ->
            when (newTokens) {
                null -> UserAuthorized.NotAuthorized
                else -> UserAuthorized.IsAuthorized.also {
                    dataBaseStorage.clearDB()
                    dataBaseStorage.saveUser(user.toData())
                    dataBaseStorage.saveTokens(newTokens)
                }
            }
        }

    override suspend fun getUser(): UserDomain =
        dataBaseStorage.getUser().toDomain()

    override suspend fun exitAccount() {
        dataBaseStorage.clearDB()
    }
}