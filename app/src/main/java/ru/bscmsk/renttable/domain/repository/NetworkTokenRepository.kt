package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.RefreshTokensDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel

interface NetworkTokenRepository {
    fun checkAccessToken(token: AccessTokenDataModel): Boolean
    fun refreshTokens(token: RefreshTokensDataModel): TokensDataModel
}