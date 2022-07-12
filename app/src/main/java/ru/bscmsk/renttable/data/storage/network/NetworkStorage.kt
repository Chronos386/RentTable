package ru.bscmsk.renttable.data.storage.network

import ru.bscmsk.renttable.app.sealed.Returnable
import ru.bscmsk.renttable.data.storage.models.*

interface NetworkStorage {
    suspend fun enterToAccount(user: UserData): TokensData?
    suspend fun getCityList(accessToken: AccessTokenData): Returnable
    suspend fun checkRefreshToken(refreshToken: RefreshTokenData): Returnable
    suspend fun getRentTables(city: CityData, accessToken: AccessTokenData): Returnable
    suspend fun getMyRent(city: CityData, accessToken: AccessTokenData): Returnable
    suspend fun sendNewListRent(newBooking: NewBookingData, accessToken: AccessTokenData): Returnable
}