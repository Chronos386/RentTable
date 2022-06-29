package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.NetworkLoginRepository

class SendUserDataUseCase(private val networkLoginRepo: NetworkLoginRepository) {
    suspend fun execute(user: UserDataModel): TokensDataModel {
        return networkLoginRepo.sendUserToServ(user = user)
    }
}