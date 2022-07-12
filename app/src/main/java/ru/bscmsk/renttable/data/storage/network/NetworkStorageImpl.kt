package ru.bscmsk.renttable.data.storage.network

import ru.bscmsk.renttable.app.sealed.CitiesList
import ru.bscmsk.renttable.app.sealed.DataPosted
import ru.bscmsk.renttable.app.sealed.RentInform
import ru.bscmsk.renttable.app.sealed.Returnable
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


    override suspend fun sendNewListRent(
        newBooking: NewBookingData,
        accessToken: AccessTokenData
    ): Returnable =
        api.sendNewListBooking(newBooking, TOKEN_PREFIX + accessToken.token).code().let {
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