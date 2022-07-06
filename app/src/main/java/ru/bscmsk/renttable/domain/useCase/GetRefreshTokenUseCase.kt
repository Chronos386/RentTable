package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel
import ru.bscmsk.renttable.domain.repository.UserRepository

class GetRefreshTokenUseCase(private val userRepo: UserRepository) {
    suspend fun execute(): RefreshTokenDataModel = userRepo.getRefreshToken()
}