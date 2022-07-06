package ru.bscmsk.renttable.domain.repository

import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel

interface UserRepository {
    suspend fun sendUserToServ(user: UserDataModel): TokensDataModel?
    suspend fun refreshTokens(token: RefreshTokenDataModel): TokensDataModel?
    suspend fun getAccessToken(): AccessTokenDataModel
    suspend fun getRefreshToken(): RefreshTokenDataModel
    suspend fun refreshTokens(tokens: TokensDataModel)
    suspend fun saveTokens(tokens: TokensDataModel)
}