package ru.bscmsk.renttable.data.storage.localDB

import ru.bscmsk.renttable.app.toAccess
import ru.bscmsk.renttable.app.toData
import ru.bscmsk.renttable.app.toRefresh
import ru.bscmsk.renttable.app.toTable
import ru.bscmsk.renttable.data.storage.localDB.dao.AccessTokenDao
import ru.bscmsk.renttable.data.storage.localDB.dao.CityDao
import ru.bscmsk.renttable.data.storage.localDB.dao.RefreshTokenDao
import ru.bscmsk.renttable.data.storage.localDB.dao.UserDao
import ru.bscmsk.renttable.data.storage.models.*
import javax.inject.Inject

class DataBaseStorageImpl @Inject constructor(
    private val userDao: UserDao,
    private val cityDao: CityDao,
    private val accessTokenDao: AccessTokenDao,
    private val refreshTokenDao: RefreshTokenDao
) : DataBaseStorage {
    override suspend fun saveUser(user: UserData) =
        userDao.insert(user.toTable())

    override suspend fun getUser(): UserData =
        userDao.get().let {
            return when (it) {
                null -> UserData("", "")
                else -> it.toData()
            }
        }

    private suspend fun clearUser() =
        userDao.delete()


    override suspend fun saveTokens(tokens: TokensData) {
        accessTokenDao.insert(tokens.toAccess().toTable())
        refreshTokenDao.insert(tokens.toRefresh().toTable())
    }

    override suspend fun getAccessToken(): AccessTokenData? =
        accessTokenDao.get()?.toData()

    override suspend fun getRefreshToken(): RefreshTokenData? =
        refreshTokenDao.get()?.toData()

    override suspend fun refreshAccessToken(newToken: AccessTokenData) =
        accessTokenDao.update(newToken.toTable())

    override suspend fun refreshRefreshToken(newToken: RefreshTokenData) =
        refreshTokenDao.update(newToken.toTable())

    private suspend fun clearAccessToken() =
        accessTokenDao.delete()

    private suspend fun clearRefreshToken() =
        refreshTokenDao.delete()


    override suspend fun saveCity(city: CityData) =
        cityDao.insert(city.toTable())

    override suspend fun getCity(): CityData =
        cityDao.get().let {
            return when (it) {
                null -> CityData("")
                else -> it.toData()
            }
        }

    override suspend fun refreshCity(city: CityData) =
        cityDao.update(city.toTable())

    private suspend fun clearCity() =
        cityDao.delete()


    override suspend fun clearDB() {
        clearAccessToken()
        clearRefreshToken()
        clearUser()
        clearCity()
    }
}