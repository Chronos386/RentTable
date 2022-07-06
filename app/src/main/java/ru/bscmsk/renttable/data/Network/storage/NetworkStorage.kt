package ru.bscmsk.renttable.data.Network.storage

import ru.bscmsk.renttable.data.Network.storage.models.*

interface NetworkStorage {
    suspend fun enterToAccount(user: UserModel): TokensModel?
    suspend fun getCityList(accessToken: AccessTokenModel): List<CityModel>?
    suspend fun checkRefreshToken(refreshToken: RefreshTokenModel): TokensModel?
    suspend fun getRentTables(city: CityModel, accessToken: AccessTokenModel): List<BookingModel>?
    suspend fun getMyRent(city: CityModel, accessToken: AccessTokenModel): List<BookingModel>?
}