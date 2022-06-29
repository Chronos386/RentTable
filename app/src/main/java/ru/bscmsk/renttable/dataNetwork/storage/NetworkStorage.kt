package ru.bscmsk.renttable.dataNetwork.storage

import ru.bscmsk.renttable.dataNetwork.storage.models.*

interface NetworkStorage {
    fun enterToAccount(user: UserModel): TokensModel
    fun getCityList(token: AccessTokenModel): CityListModel
    fun checkAccessToken(accessToken: AccessTokenModel): Boolean
    fun checkRefreshToken(refreshToken: RefreshTokenModel): TokensModel
}