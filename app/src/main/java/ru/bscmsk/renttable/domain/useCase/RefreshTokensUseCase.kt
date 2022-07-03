package ru.bscmsk.renttable.domain.useCase

import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.repository.UserRepository

class RefreshTokensUseCase(private val userRepo: UserRepository) {
    suspend fun execute(tokens: TokensDataModel) = userRepo.refreshTokens(tokens)
}