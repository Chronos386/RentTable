package ru.bscmsk.renttable.dataNetwork.implementations

import ru.bscmsk.renttable.dataNetwork.storage.NetworkStorage
import ru.bscmsk.renttable.dataNetwork.storage.models.AccessTokenModel
import ru.bscmsk.renttable.dataNetwork.storage.models.RefreshTokenModel
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.RefreshTokensDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.repository.NetworkTokenRepository

class NetworkTokenRepositoryImpl(private val networkStorage: NetworkStorage):
    NetworkTokenRepository {

    override fun checkAccessToken(token: AccessTokenDataModel): Boolean {
        val newToken = AccessTokenModel(token.token)
        return networkStorage.checkAccessToken(newToken)
    }

    override fun refreshTokens(token: RefreshTokensDataModel): TokensDataModel {
        val newToken = RefreshTokenModel(token.token)
        val newTokens = networkStorage.checkRefreshToken(newToken)
        return TokensDataModel(newTokens.accessToken, newTokens.refreshToken)
    }
}