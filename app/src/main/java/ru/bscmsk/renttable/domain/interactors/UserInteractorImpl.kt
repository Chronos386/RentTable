package ru.bscmsk.renttable.domain.interactors

import ru.bscmsk.renttable.app.sealed.UserAuthorized
import ru.bscmsk.renttable.app.toDomain
import ru.bscmsk.renttable.app.toPresentation
import ru.bscmsk.renttable.domain.repository.UserRepository
import ru.bscmsk.renttable.presentation.interactors.UserInteractor
import ru.bscmsk.renttable.presentation.models.UserPresentation
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val userRepository: UserRepository
): UserInteractor {
    override suspend fun enterAccount(user: UserPresentation): UserAuthorized =
        userRepository.sendUserToServ(user.toDomain())

    override suspend fun getUser(): UserPresentation =
        userRepository.getUser().toPresentation()

    override suspend fun exitAccount() =
        userRepository.exitAccount()
}