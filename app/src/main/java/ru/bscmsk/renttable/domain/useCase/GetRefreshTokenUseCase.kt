package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.repository.UserRepository
import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel

class GetRefreshTokenUseCase(private val userRepo: UserRepository) {
    suspend fun execute(): RefreshTokenDataModel = userRepo.getRefreshToken()
}