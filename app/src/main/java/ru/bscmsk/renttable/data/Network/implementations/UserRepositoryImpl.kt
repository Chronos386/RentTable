package ru.bscmsk.renttable.data.Network.implementations

import ru.bscmsk.renttable.data.Network.storage.models.RefreshTokenModel
import ru.bscmsk.renttable.data.Network.storage.NetworkStorage
import ru.bscmsk.renttable.data.Network.storage.models.UserModel
import ru.bscmsk.renttable.domain.models.AccessTokenDataModel
import ru.bscmsk.renttable.domain.models.RefreshTokenDataModel
import ru.bscmsk.renttable.domain.models.TokensDataModel
import ru.bscmsk.renttable.domain.models.UserDataModel
import ru.bscmsk.renttable.domain.repository.UserRepository

class UserRepositoryImpl(private val networkStorage: NetworkStorage):
    UserRepository {
    var token1: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJsb2dpbiI6IkxPR0lOIiwidHlwZSI6ImFjY2Vzc1Rva2VuIiwiZXhwIjoxNjU2ODg2OTk3LCJpYXQiOjE2NTY4ODUxOTd9.Qh7W2AQ83h1kBL_e2nK1XC4vU4DGw_eb4DPEtwHM3Zg"
    var token2: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJsb2dpbiI6IkxPR0lOIiwidHlwZSI6InJlZnJlc2hUb2tlbiIsImV4cCI6MTY1Njg4ODc5NywiaWF0IjoxNjU2ODg1MTk3fQ.0RAGCMt6h2sj1B-gVNHf5adt09LewqtyBNIsf43SxN4"

    override suspend fun sendUserToServ(user: UserDataModel): TokensDataModel? =
        networkStorage.enterToAccount(UserModel(user.login, user.password)).let { newTokens ->
            when (newTokens) {
                null -> null
                else -> TokensDataModel(newTokens.accessToken, newTokens.refreshToken)
            }
        }

    override suspend fun refreshTokens(token: RefreshTokenDataModel): TokensDataModel? =
        networkStorage.checkRefreshToken(RefreshTokenModel(token.token)).let { newTokens ->
            when (newTokens) {
                null -> null
                else -> TokensDataModel(newTokens.accessToken, newTokens.refreshToken)
            }
        }

    override suspend fun getAccessToken(): AccessTokenDataModel =
        AccessTokenDataModel(token1)

    override suspend fun getRefreshToken(): RefreshTokenDataModel =
        RefreshTokenDataModel(token2)

    override suspend fun refreshTokens(tokens: TokensDataModel) {
        token1 = tokens.accessToken
        token2 = tokens.refreshToken
    }

    override suspend fun saveTokens(tokens: TokensDataModel) {
        println(tokens)
    }
}