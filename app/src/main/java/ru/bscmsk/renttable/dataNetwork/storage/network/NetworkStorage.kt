package ru.bscmsk.renttable.dataNetwork.storage.network

import ru.bscmsk.renttable.dataNetwork.storage.models.*

interface NetworkStorage {
    suspend fun enterToAccount(user: UserModel): TokensModel?
    suspend fun getCityList(accessToken: AccessTokenModel): List<CityModel>?
    suspend fun checkRefreshToken(refreshToken: RefreshTokenModel): TokensModel?
    suspend fun getRentTables(city: CityModel, accessToken: AccessTokenModel): List<BookingModel>?
    suspend fun getMyRent(city: CityModel, accessToken: AccessTokenModel): List<BookingModel>?
}