package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.app.sealed.UserAuthorized
import ru.bscmsk.renttable.domain.models.UserDomain

interface UserRepository {
    suspend fun sendUserToServ(user: UserDomain): UserAuthorized
    suspend fun getUser(): UserDomain
    suspend fun exitAccount()
}