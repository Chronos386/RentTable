package ru.bscmsk.renttable.data.storage.localDB

import ru.bscmsk.renttable.data.storage.models.*

interface DataBaseStorage {
    suspend fun saveUser(user: UserData)
    suspend fun getUser(): UserData

    suspend fun saveTokens(tokens: TokensData)
    suspend fun getAccessToken(): AccessTokenData
    suspend fun getRefreshToken(): RefreshTokenData
    suspend fun refreshAccessToken(newToken: AccessTokenData)
    suspend fun refreshRefreshToken(newToken: RefreshTokenData)

    suspend fun saveCity(city: CityData)
    suspend fun getCity(): CityData
    suspend fun refreshCity(city: CityData)

    suspend fun clearDB()
}