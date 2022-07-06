package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.UserRepository

class SendUserDataUseCase(private val networkLoginRepo: UserRepository) {
    suspend fun execute(user: UserDataModel): TokensDataModel? =
        networkLoginRepo.sendUserToServ(user = user)
}