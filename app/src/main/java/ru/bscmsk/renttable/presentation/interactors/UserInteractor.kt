package ru.bscmsk.renttable.presentation.interactors

import ru.bscmsk.renttable.app.sealed.UserAuthorized
import ru.bscmsk.renttable.presentation.models.UserPresentation

interface UserInteractor {
    suspend fun enterAccount(user: UserPresentation): UserAuthorized
    suspend fun getUser(): UserPresentation
    suspend fun exitAccount()
}