package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.repository.NetworkTokenRepository

class CheckAccessTokenUseCase(private val networkTokenRepo: NetworkTokenRepository) {
    suspend fun execute(token: AccessTokenDataModel): Boolean {
        return networkTokenRepo.checkAccessToken(token = token)
    }
}