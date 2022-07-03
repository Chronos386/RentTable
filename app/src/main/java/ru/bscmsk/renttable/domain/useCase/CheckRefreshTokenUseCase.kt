package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.repository.UserRepository

class CheckRefreshTokenUseCase(private val userRepo: UserRepository) {
    suspend fun execute(token: RefreshTokenDataModel): TokensDataModel? {
        return userRepo.refreshTokens(token = token)
    }
}