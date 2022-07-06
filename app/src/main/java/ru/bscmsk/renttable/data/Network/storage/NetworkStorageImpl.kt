package ru.bscmsk.renttable.data.Network.storage

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.bscmsk.renttable.app.UnauthorizedException
import ru.bscmsk.renttable.data.Network.storage.models.*

class NetworkStorageImpl: NetworkStorage {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkRequests::class.java)

    override suspend fun enterToAccount(user: UserModel): TokensModel? {
        retrofit.getTokens(user.login, user.password).let { result ->
            return when (result.code()) {
                200 -> result.body().let {
                    when (it) {
                        null -> null
                        else -> TokensModel(it.accessToken, it.refreshToken)
                    }
                }
                else -> throw UnauthorizedException()
            }
        }
    }

    override suspend fun getCityList(accessToken: AccessTokenModel): List<CityModel>? {
        retrofit.getCityList(TOKEN_PREFIX + accessToken.token).let { result ->
            return when(result.body()) {
                null -> null
                else -> result.body()
            }
        }
    }

    override suspend fun checkRefreshToken(refreshToken: RefreshTokenModel): TokensModel? {
        retrofit.getNewTokens(TOKEN_PREFIX + refreshToken.token).let { result ->
            return when (result.code()) {
                200 -> result.body().let {
                    when (it) {
                        null -> null
                        else -> TokensModel(it.accessToken, it.refreshToken)
                    }
                }
                403 -> throw UnauthorizedException()
                else -> null
            }
        }
    }

    override suspend fun getRentTables(city: CityModel,
                                       accessToken: AccessTokenModel): List<BookingModel>? {
        retrofit.getRentTables(city.name, TOKEN_PREFIX + accessToken.token).let { result ->
            return when(result.body()) {
                null -> null
                else -> result.body()
            }
        }
    }

    override suspend fun getMyRent(city: CityModel,
                                       accessToken: AccessTokenModel): List<BookingModel>? {
        retrofit.getMyRentTables(city.name, TOKEN_PREFIX + accessToken.token).let { result ->
            return when(result.body()) {
                null -> null
                else -> result.body()
            }
        }
    }

    private companion object {

        private const val BASE_URL = "http://25.54.65.89:8080/api/v1/"

        private const val TOKEN_PREFIX = "Bearer "

    }
}