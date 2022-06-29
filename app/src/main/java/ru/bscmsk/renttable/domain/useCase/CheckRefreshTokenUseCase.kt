package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.RefreshTokensDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.repository.NetworkTokenRepository

class CheckRefreshTokenUseCase(private val networkTokenRepo: NetworkTokenRepository) {
    suspend fun execute(token: RefreshTokensDataModel): TokensDataModel {
        return networkTokenRepo.refreshTokens(token = token)
    }
}