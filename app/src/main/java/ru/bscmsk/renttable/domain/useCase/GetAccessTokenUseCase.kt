package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.repository.UserRepository

class GetAccessTokenUseCase(private val userRepo: UserRepository) {
    suspend fun execute(): AccessTokenDataModel = userRepo.getAccessToken()
}