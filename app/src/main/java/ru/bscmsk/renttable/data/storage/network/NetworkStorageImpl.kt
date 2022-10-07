package ru.bscmsk.renttable.data.storage.network

import ru.bscmsk.renttable.app.sealed.*
import ru.bscmsk.renttable.app.toData
import ru.bscmsk.renttable.data.storage.models.*
import javax.inject.Inject

class NetworkStorageImpl @Inject constructor(
    private val api: NetworkRequests
) : NetworkStorage {

    override suspend fun enterToAccount(user: UserData): TokensData? =
        api.getTokens(user.login, user.password).body()

    override suspend fun getCityList(accessToken: AccessTokenData): Returnable =
        api.getCityList(TOKEN_PREFIX + accessToken.token).body().let {
            return when (it) {
                null -> Returnable.OldTokens
                else -> CitiesList.ListReceived(it)
            }
        }

    override suspend fun getRentTables(
        city: CityData,
        accessToken: AccessTokenData
    ): Returnable =
        api.getRentTables(city.name, TOKEN_PREFIX + accessToken.token).body().let {
            return when (it) {
                null -> Returnable.OldTokens
                else -> RentInform.RentReceived(it)
            }
        }

    override suspend fun getCityInform(city: CityData, accessToken: AccessTokenData): Returnable =
        api.getCityInform(city.name, TOKEN_PREFIX + accessToken.token).body().let {
            return when (it) {
                null -> Returnable.OldTokens
                else -> CityInform.InformReceived(it)
            }
        }

    override suspend fun getMyRent(
        city: CityData,
        accessToken: AccessTokenData
    ): Returnable =
        api.getMyRentTables(city.name, TOKEN_PREFIX + accessToken.token).body().let {
            return when (it) {
                null -> Returnable.OldTokens
                else -> RentInform.RentReceived(it)
            }
        }

    override suspend fun checkRefreshToken(refreshToken: RefreshTokenData): Returnable =
        api.getNewTokens(TOKEN_PREFIX + refreshToken.token).body().let {
            return when (it) {
                null -> Returnable.OldTokens
                else -> Returnable.NewTokens(it)
            }
        }


    override suspend fun sendNewRent(
        newBooking: NewBookingData,
        accessToken: AccessTokenData
    ): Returnable =
        api.sendNewBooking(newBooking, TOKEN_PREFIX + accessToken.token).code().let {
            when (it) {
                200 -> DataPosted.IsPosted
                409 -> DataPosted.NotPosted
                else -> Returnable.OldTokens
            }
        }

    override suspend fun clearMyRent(city: CityData, accessToken: AccessTokenData): Returnable {
        api.clearMyBooking(
            ClearBooking(region = city.name, dates = ArrayList(), places = ArrayList()).toData(),
            TOKEN_PREFIX + accessToken.token
        ).code().let {
            return when (it) {
                200 -> DataPosted.IsPosted
                409 -> DataPosted.NotPosted
                else -> Returnable.OldTokens
            }
        }
    }


    override suspend fun deleteRent(
        booking: NewBookingData,
        accessToken: AccessTokenData
    ): Returnable =
        api.deleteBooking(booking, TOKEN_PREFIX + accessToken.token).code().let {
            when (it) {
                200 -> DataPosted.IsPosted
                409 -> DataPosted.NotPosted
                else -> Returnable.OldTokens
            }
        }


    private companion object {
        private const val TOKEN_PREFIX = "Bearer "
    }
}